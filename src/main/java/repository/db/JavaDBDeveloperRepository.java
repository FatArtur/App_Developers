package repository.db;

import controller.AccountController;
import controller.SkillController;
import model.Account;
import model.Developer;
import model.Skill;
import repository.DeveloperRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JavaDBDeveloperRepository  implements DeveloperRepository {
    private final static String DB_TABLE_1 = "developer";
    private final static String DB_TABLE_2 = "SkillList";
    private static String SQL;

    public Developer save(Developer val) throws Exception {
        SQL = String.format("INSERT INTO %s (name, account_id) VALUES ('%s', '%s')", DB_TABLE_1,
                val.getName(), val.getAccount().getId());
        DatabaseHandler.getStatement(SQL).execute();
        SQL = String.format("SELECT idDeveloper from %s where name='%s'", DB_TABLE_1,
                val.getName());
        ResultSet resultSet = DatabaseHandler.getStatement(SQL).executeQuery();
        while (resultSet.next()){
            try {
                val.setId(resultSet.getLong(1));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return saveToSkillList(val);
    }

    public void deleteById(Long id) throws Exception {
        SQL = String.format("delete from %s where idDeveloper=%s", DB_TABLE_1, id);
        DatabaseHandler.getStatement(SQL).execute();
    }

    public Developer getByID(Long id) throws Exception {
        SQL = String.format("select* from %s where idDeveloper=%s order by idDeveloper", DB_TABLE_1, id);
        return sqlToDeveloper(DatabaseHandler.getStatement(SQL).executeQuery());
    }

    public List<Developer> getAll() throws Exception {
        SQL = String.format("select* from %s order by idDeveloper", DB_TABLE_1);
        return sqlToDevelopers(DatabaseHandler.getStatement(SQL).executeQuery());
    }

    public Developer update(Developer val) throws Exception {
        SQL = String.format("UPDATE %s SET name = '%s', account_id = '%s' where idDeveloper =%s", DB_TABLE_1,
                val.getName(), val.getAccount() ,val.getId());
        DatabaseHandler.getStatement(SQL).executeUpdate();
        SQL = String.format("delete from %s where developer_id=%s", DB_TABLE_2, val.getId());
        DatabaseHandler.getStatement(SQL).execute();
        return saveToSkillList(val);
    }

    private Developer sqlToDeveloper(ResultSet resultSet) throws SQLException {
        Developer developer = new Developer();
        while (resultSet.next()){
            developer.setId((long)resultSet.getInt(1));
            developer.setName(resultSet.getString(2));
            developer.setAccount(convertToAccount(resultSet.getString(3)));
            developer.setSkill(convertToSkill((long)resultSet.getInt(1)));
        }
        return developer;
    }

    private List<Developer> sqlToDevelopers(ResultSet resultSet) throws SQLException{
        Developer developer;
        List<Developer> list = new ArrayList<Developer>();
        while (resultSet.next()){
            developer = new Developer();
            developer.setId((long)resultSet.getInt(1));
            developer.setName(resultSet.getString(2));
            developer.setAccount(convertToAccount(resultSet.getString(3)));
            developer.setSkill(convertToSkill((long)resultSet.getInt(1)));
            list.add(developer);
        }
        return list;
    }

    private Account convertToAccount(String val) {
        AccountController account = new AccountController();
        try {
            return account.getByID(val);
        } catch (Exception e){
            return null;
        }
    }

    private List<Skill> convertToSkill(Long val) throws SQLException {
        List<Skill> list = new ArrayList<>();
        SkillController skillController = new SkillController();
        SQL = String.format("select skill_id from %s where developer_id=%s", DB_TABLE_2, val);
        ResultSet resultSet = DatabaseHandler.getStatement(SQL).executeQuery();
        while (resultSet.next()){
            try {
                list.add(skillController.getByID(resultSet.getString(1)));
            } catch (Exception e){
                System.out.println("Error getByID");;
            }
        }
        return list;
    }

    private Developer saveToSkillList(Developer val) throws SQLException{
        List<Skill> list = val.getSkill();
        list.forEach(s -> {SQL =String.format("INSERT INTO %s (developer_id, skill_id) VALUES ('%s', '%s')",
                DB_TABLE_2, val.getId(), s.getId());
            try {
                DatabaseHandler.getStatement(SQL).execute();
            } catch (SQLException r) {
                r.printStackTrace();
            }});
        return val;
    }
}

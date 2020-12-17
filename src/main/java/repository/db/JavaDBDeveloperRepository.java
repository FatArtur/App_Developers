package repository.db;

import model.Account;
import model.AccountStatus;
import model.Developer;
import model.Skill;
import repository.DeveloperRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JavaDBDeveloperRepository  implements DeveloperRepository {
    private final static String DB_TABLE_DEVELOPER = "developer";
    private final static String DB_TABLE_SKILL_LIST = "SkillList";
    private final static String DB_TABLE_ACCOUNT = "Account";
    private final static String DB_TABLE_SKILL = "Skill";
    private static String SQL;

    public Developer save(Developer val) throws Exception {
        SQL = String.format("INSERT INTO %s (name, account_id) VALUES ('%s', '%s')", DB_TABLE_DEVELOPER,
                val.getName(), val.getAccount().getId());
        DatabaseHandler.getStatement(SQL).execute();
        SQL = String.format("SELECT idDeveloper from %s where name='%s'", DB_TABLE_DEVELOPER,
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
        SQL = String.format("delete from %s where idDeveloper=%s", DB_TABLE_DEVELOPER, id);
        DatabaseHandler.getStatement(SQL).execute();
    }

    public Developer getByID(Long id) throws Exception {
        SQL = String.format("select idDeveloper, Developer.name, Account.idAccount, Account.name, " +
                        "Account.AccountStatus from %s INNER JOIN %s ON Developer.account_id = Account.idAccount " +
                        "where idDeveloper=%s", DB_TABLE_DEVELOPER, DB_TABLE_ACCOUNT, id);
        return sqlToDeveloper(DatabaseHandler.getStatement(SQL).executeQuery());
    }

    public List<Developer> getAll() throws Exception {
        SQL = String.format("select idDeveloper, Developer.name, Account.idAccount, Account.name, " +
                "Account.AccountStatus from %s INNER JOIN %s ON Developer.account_id = Account.idAccount",
                DB_TABLE_DEVELOPER, DB_TABLE_ACCOUNT);
        return sqlToDevelopers(DatabaseHandler.getStatement(SQL).executeQuery());
    }

    public Developer update(Developer val) throws Exception {
        SQL = String.format("UPDATE %s SET name = '%s', account_id = %s where idDeveloper =%s", DB_TABLE_DEVELOPER,
                val.getName(), val.getAccount().getId() ,val.getId());
        DatabaseHandler.getStatement(SQL).executeUpdate();
        SQL = String.format("delete from %s where developer_id=%s", DB_TABLE_SKILL_LIST, val.getId());
        DatabaseHandler.getStatement(SQL).execute();
        return saveToSkillList(val);
    }

    private Developer sqlToDeveloper(ResultSet resultSet) throws SQLException {
        Developer developer = new Developer();
        Account account = new Account();
        while (resultSet.next()){
            developer.setId((long)resultSet.getInt(1));
            developer.setName(resultSet.getString(2));
            account.setId((resultSet.getLong(3)));
            account.setName((resultSet.getString(4)));
            account.setAccountStatus(AccountStatus.valueOf(resultSet.getString(5)));
            developer.setAccount(account);
            developer.setSkill(convertToSkill((long)resultSet.getInt(1)));
        }
        return developer;
    }

    private List<Developer> sqlToDevelopers(ResultSet resultSet) throws SQLException{
        Developer developer;
        Account account;
        List<Developer> list = new ArrayList<Developer>();
        while (resultSet.next()){
            developer = new Developer();
            account = new Account();
            developer.setId((long)resultSet.getInt(1));
            developer.setName(resultSet.getString(2));
            account.setId((resultSet.getLong(3)));
            account.setName((resultSet.getString(4)));
            account.setAccountStatus(AccountStatus.valueOf(resultSet.getString(5)));
            developer.setAccount(account);
            developer.setSkill(convertToSkill((long)resultSet.getInt(1)));
            list.add(developer);
        }
        return list;
    }

    private List<Skill> convertToSkill(Long val) throws SQLException {
        List<Skill> list = new ArrayList<>();
        Skill skill;
        SQL = String.format("select Skill.idSkill, Skill.name " +
                "from %s INNER JOIN %s ON Skill.idSkill = SkillList.skill_id " +
                "where SkillList.developer_id = %s", DB_TABLE_SKILL, DB_TABLE_SKILL_LIST, val);
        ResultSet resultSet = DatabaseHandler.getStatement(SQL).executeQuery();
        while (resultSet.next()){
            try {
                skill = new Skill();
                skill.setId(resultSet.getLong(1));
                skill.setName(resultSet.getString(2));
                list.add(skill);
            } catch (Exception e){
                System.out.println("Error getByID");;
            }
        }
        return list;
    }

    private Developer saveToSkillList(Developer val) throws SQLException{
        List<Skill> list = val.getSkill();
        list.forEach(s -> {SQL =String.format("INSERT INTO %s (developer_id, skill_id) VALUES ('%s', '%s')",
                DB_TABLE_SKILL_LIST, val.getId(), s.getId());
            try {
                DatabaseHandler.getStatement(SQL).execute();
            } catch (SQLException r) {
                r.printStackTrace();
            }});
        return val;
    }
}

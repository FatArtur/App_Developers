package repository.db;

import model.Skill;
import repository.SkillRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JavaDBSkillRepository implements SkillRepository {
    private final static String DB_TABLE = "skill";
    private static String SQL;

    public Skill save(Skill val) throws Exception {
        SQL = String.format("INSERT INTO %s (name) VALUES ('%s')", DB_TABLE, val.getName());
        DatabaseHandler.getStatement(SQL).execute();
        SQL = String.format("select* from %s where name='%s' order by idskill", DB_TABLE, val.getName());
        return sqlToSkill(DatabaseHandler.getStatement(SQL).executeQuery());
    }

    public void deleteById(Long id) throws Exception {
        SQL = String.format("delete from %s where idskill=%s", DB_TABLE, id);
        DatabaseHandler.getStatement(SQL).execute();
    }

    public Skill getByID(Long id) throws Exception {
        SQL = String.format("select* from %s where idskill=%s order by idskill", DB_TABLE, id);
        return sqlToSkill(DatabaseHandler.getStatement(SQL).executeQuery());
    }

    public List<Skill> getAll() throws Exception {
        SQL = String.format("select* from %s order by idskill", DB_TABLE);
        return sqlToSkills(DatabaseHandler.getStatement(SQL).executeQuery());
    }

    public Skill update(Skill val) throws Exception {
        SQL = String.format("UPDATE %s SET name = '%s' where idskill =%s", DB_TABLE, val.getName(), val.getId());
        DatabaseHandler.getStatement(SQL).executeUpdate();
        SQL = String.format("select* from %s where name='%s' order by idskill", DB_TABLE, val.getName());
        return sqlToSkill(DatabaseHandler.getStatement(SQL).executeQuery());
    }


    private List<Skill> sqlToSkills (ResultSet resultSet) throws SQLException {
        Skill skills;
        List<Skill> list = new ArrayList<Skill>();
        while (resultSet.next()){
            skills = new Skill();
            skills.setId((long)resultSet.getInt(1));
            skills.setName(resultSet.getString(2));
            list.add(skills);
        }
        return list;
    }

    private Skill sqlToSkill (ResultSet resultSet) throws SQLException{
        Skill skill = new Skill();
        while (resultSet.next()){
            skill.setId((long)resultSet.getInt(1));
            skill.setName(resultSet.getString(2));
        }
        return skill;
    }
}

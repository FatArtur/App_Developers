package repository.db;

import model.Skill;
import repository.SkillRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JavaDBSkillRepository implements SkillRepository {
    private final static String DB_TABLE = "skill";
    private static String SQL;
    PreparedStatement statement;

    public Skill save(Skill val) throws Exception {
        SQL = "INSERT INTO " + DB_TABLE + " (name) VALUES ('" + val.getName() + "')";
        DatabaseHandler.getDbConnection().prepareStatement(SQL).execute();
        SQL = "select* from " + DB_TABLE + " where name='"+ val.getName() +"' order by idskill";
        return sqlToSkill(DatabaseHandler.getDbConnection().prepareStatement(SQL).executeQuery());
    }

    public void deleteById(Long id) throws Exception {
        SQL = "delete from " + DB_TABLE + " where idskill="+ id;
        DatabaseHandler.getDbConnection().prepareStatement(SQL).execute();
    }

    public Skill getByID(Long id) throws Exception {
        SQL = "select* from " + DB_TABLE + " where idskill="+ id +" order by idskill";
        statement = DatabaseHandler.getDbConnection().prepareStatement(SQL);
        return sqlToSkill(statement.executeQuery());
    }

    public List<Skill> getAll() throws Exception {
        SQL = "select* from " + DB_TABLE + " order by idskill";
        statement = DatabaseHandler.getDbConnection().prepareStatement(SQL);
        return sqlToSkills(statement.executeQuery());
    }

    public Skill update(Skill val) throws Exception {
        SQL = "UPDATE " + DB_TABLE + " SET name = '" + val.getName() + "' where idskill = " + val.getId();
        DatabaseHandler.getDbConnection().prepareStatement(SQL).executeUpdate();
        SQL = "select* from " + DB_TABLE + " where name='"+ val.getName() +"' order by idskill";
        return sqlToSkill(DatabaseHandler.getDbConnection().prepareStatement(SQL).executeQuery());
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

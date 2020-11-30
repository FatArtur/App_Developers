import model.Skill;
import repository.db.JavaDBSkillRepository;



public class Test {
    public static void main(String[] args) throws Exception {
        JavaDBSkillRepository  javaDBSkillRepository = new JavaDBSkillRepository();
        System.out.println(javaDBSkillRepository.getAll().toString());
        Skill skill = new Skill();
        skill.setName("JDBC+");
        skill.setId((long)10);
        javaDBSkillRepository.update(skill);
        System.out.println(javaDBSkillRepository.getAll().toString());
    }
}

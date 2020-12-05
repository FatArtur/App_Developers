import model.Skill;
import repository.db.JavaDBSkillRepository;



public class Test {
    public static void main(String[] args) throws Exception {
        JavaDBSkillRepository  javaDBSkillRepository = new JavaDBSkillRepository();
        System.out.println(javaDBSkillRepository.getAll().toString());
        Thread.sleep(500);
        System.out.println(javaDBSkillRepository.getByID((long)4).toString());
        Thread.sleep(500);
        System.out.println(javaDBSkillRepository.getAll().toString());

    }
}

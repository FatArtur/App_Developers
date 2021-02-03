import controller.AccountController;
import view.AccountView;
import view.DeveloperView;
import view.SkillView;



public class Test {
    public static void main(String[] args) throws Exception {
//        SkillView skillView = new SkillView();
//        AccountView accountView = new AccountView();
//        DeveloperView developerView = new DeveloperView();
//        developerView.run();

        AccountController accountController = new AccountController();
        accountController.getByID("2");


    }
}

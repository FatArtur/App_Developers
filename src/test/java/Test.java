import controller.AccountController;
import model.Account;
import model.AccountStatus;
import view.AccountView;
import view.DeveloperView;
import view.SkillView;

import java.util.List;


public class Test {
    public static void main(String[] args) throws Exception {
//        SkillView skillView = new SkillView();
//        AccountView accountView = new AccountView();
        DeveloperView developerView = new DeveloperView();
        developerView.run();
//        accountView.run();
//        skillView.run();
    }
}

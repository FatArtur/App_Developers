package view;

import controller.DeveloperController;
import model.Account;
import model.Developer;
import model.Skill;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DeveloperView {
    private String viewMenu = "\nВыберите действие для Developer: \n" +
            "1.Создать\n" +
            "2.Удалить\n" +
            "3.Получить по ID\n" +
            "4.Получить все\n" +
            "5.Изменить\n" +
            "6.Выход\n";

    private Scanner scan;
    private DeveloperController controller = new DeveloperController();

    public void run() {
        boolean istrue = false;
        while (!istrue) {
            System.out.print(viewMenu);
            System.out.println("*******************");
            scan = new Scanner(System.in);
            String txt = scan.next();
            try {
                switch (txt) {
                    case "1":
                        System.out.println(Message.NEW);
                        txt = scan.next();
                        controller.create(txt, setAccount(), setSkill());
                        break;
                    case "2":
                        System.out.println(Message.ID);
                        txt = scan.next();
                        controller.delete(txt);
                        break;
                    case "3":
                        System.out.println(Message.ID);
                        txt = scan.next();
                        print(controller.getByID(txt));
                        break;
                    case "4":
                        System.out.println(Message.ALL);
                        controller.getAll().stream().forEach(s-> print(s));
                        break;
                    case "5":
                        System.out.println(Message.ID);
                        txt = scan.next();
                        System.out.println(Message.CHANGE);
                        String txt2 = scan.next();
                        controller.update(txt, txt2, setAccount(), setSkill());
                        break;
                    case "6":
                        istrue = true;
                        break;
                    default:
                        System.out.println(Message.NOVALUE);
                }
            } catch (Exception e) {
                System.out.println("Message -> " + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    private void print(Developer developer) {
        System.out.println("ID: " + developer.getId() + " | NAME: " + developer.getName() +
                " | ACCOUNT: " + developer.getAccount().getName() + " | ACCOUNT STATUS: " +
                developer.getAccount().getAccountStatus() + " | SKILLS: " + convertSkillToString(developer.getSkill()));
    }

    private String convertSkillToString(List<Skill> val){
        List<String> list = val.stream().map(s-> s.getId() + "." + s.getName()).collect(Collectors.toList());
        return list.stream().reduce((s1,s2) -> s1 + ", " + s2).orElse(null);
    }

    private Account setAccount() throws Exception{
        System.out.println(Message.CHAISE);
        controller.getAccounts().stream().forEach(s -> System.out.println(s.getId() + "." + s.getName() + " " +
                s.getAccountStatus()));
        scan = new Scanner(System.in);
        return controller.giveAccount(scan.next());
    }

    private List<Skill> setSkill() throws Exception{
        List<Skill> skills = new ArrayList<>();
        scan = new Scanner(System.in);
        System.out.println(Message.CHAISE);
        controller.getSkills().stream().forEach(s -> System.out.println("ID = " + s.getId() + " Skill = "
                + s.getName()));
        String txt;
        do {
            System.out.println(Message.ADDVALUE);
            txt = scan.next();
            if (txt.equals("-")) break;
            skills.add(controller.giveSkill(txt));

        } while (true);
        return skills;
    }
}

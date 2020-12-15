package view;

import controller.SkillController;
import model.Skill;

import java.util.Scanner;

public class SkillView {

    private String viewMenu = "\nВыберите действие для Skill: \n" +
            "1.Создать\n" +
            "2.Удалить\n" +
            "3.Получить по ID\n" +
            "4.Получить все\n" +
            "5.Изменить\n" +
            "6.Выход\n";

    private Scanner scan;
    private SkillController controller = new SkillController();

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
                        controller.create(txt);
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
                        controller.getAll();
                        break;
                    case "5":
                        System.out.println(Message.ID);
                        txt = scan.next();
                        System.out.println(Message.CHANGE);
                        String txt2 = scan.next();
                        controller.update(txt,txt2);
                        break;
                    case "6":
                        istrue = true;
                        break;
                    default:
                        System.out.println(Message.NOVALUE);
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }

    private void print(Skill skill) {
        System.out.println("ID = " + skill.getId() + " Skill = " + skill.getName());
    }
}

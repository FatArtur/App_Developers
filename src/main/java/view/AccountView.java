package view;

import controller.AccountController;
import model.Account;
import model.AccountStatus;

import java.util.Scanner;

public class AccountView {
    private String viewMenu = "\nВыберите действие для Account: \n" +
            "1.Создать\n" +
            "2.Удалить\n" +
            "3.Получить по ID\n" +
            "4.Получить все\n" +
            "5.Изменить\n" +
            "6.Выход\n";

    private Scanner scan;
    private AccountController controller = new AccountController();

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
                        controller.update(txt, txt2, status());
                        break;
                    case "6":
                        istrue = true;
                        break;
                    default:
                        System.out.println(Message.NOVALUE);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    private AccountStatus status() {
        while (true) {
            System.out.print("Выберите STATUS:\n" +
                    "1.ACTIVE\n" +
                    "2.BANNED\n" +
                    "3.DELETED\n");
            String txt = scan.next();
            switch (txt) {
                case "1":
                    return AccountStatus.ACTIVE;
                case "2":
                    return AccountStatus.BANNED;
                case "3":
                    return AccountStatus.DELETED;
                default:
                    System.out.println(Message.NOVALUE);
            }
        }
    }

    private void print(Account account) {
        System.out.println("ID = " + account.getId() + " Skill = " + account.getName() +
                " STATUS: " + account.getAccountStatus());
    }
}

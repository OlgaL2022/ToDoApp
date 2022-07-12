package menu;

import config.Auth;

import java.util.Scanner;

public class LoginMenu {

    public static void menu() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the ToDo App. Please select an option:");
            System.out.println("1  Login: ");
            System.out.println("2. Sign up: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    Auth.login();
                    TaskMenu.menu();
                    break;
                case 2:
                    Auth.signUp();
                    TaskMenu.menu();
                    break;
                default:
                    System.out.println("Invalid option! Try again!");
                    break;
            }
            break;
        }
    }
}





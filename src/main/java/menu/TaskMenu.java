package menu;

import controller.TaskController;
import static controller.TaskController.*;
import java.util.Scanner;

public class TaskMenu {

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("What do you want to do?: ");
            System.out.println("1. Add new task");
            System.out.println("2. Edit task");
            System.out.println("3. Delete task");
            System.out.println("4. Get task by id");
            System.out.println("5. Mark task as complete");
            System.out.println("6. Show pending tasks for all users");
            System.out.println("7. Show all tasks for a single user ");
            System.out.println("8. Log off");

            System.out.print("Please select an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println(TaskController.addTask() ? "Successfully added new task" : "Task not added");
                    break;
                case 2:
                    editTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    System.out.println(getTask());
                    break;
                case 5:
                    markTaskAsCompleted();
                    break;
                case 6:
                    printPendingTasks(getPendingTasks());
                    break;
                case 7:
                    System.out.println(getSingleUserTasks());
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid option. Try again");
                    TaskMenu.menu();
            }
            System.out.println("Do you want to do anything else?");
            String answer = scanner.next();
            if (!answer.equalsIgnoreCase("yes")) {
                break;
            }
        }
    }
}

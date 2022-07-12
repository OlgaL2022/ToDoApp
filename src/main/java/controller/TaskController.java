package controller;

import model.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static config.DbConnection.getConnection;

public class TaskController {
    private static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;


    public static boolean addTask() {

        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();

        System.out.println("Please enter your task: ");
        String task = scanner.nextLine();

        System.out.println("Please enter your deadline (yyyy-mm-dd): ");
        String time = scanner.next();
        LocalDate deadline = LocalDate.parse(time);

        System.out.println("Is it completed (yes or no): ");
        String answer = scanner.next();
        Boolean yn = (answer.equalsIgnoreCase("YES") ? true : false);

        try {
            ps = getConnection().prepareStatement("INSERT INTO todolist(username, task, deadline, completion)"
                    + " VALUES('" + username + "','" + task + "',  '" + deadline + "' , '" + yn + "')");
            ps.execute();
            System.out.println("Added successfully");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void editTask() {
        System.out.print("Enter the task's id: ");
        int id = scanner.nextInt();

        System.out.print("What do you want to edit it to?: ");
        String update = scanner.next();

        try {
            ps = getConnection().prepareStatement("UPDATE todolist SET task ='" + update + "' WHERE id=" + id + ";");
            ps.execute();
            System.out.println("Successfully updated data");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void markTaskAsCompleted() {
        System.out.println("Enter the id of the task that you want to edit: ");
        int id = scanner.nextInt();

        System.out.print("Enter the task completion status (yes/no): ");
        String taskStatus = scanner.next();
        try {
            ps = getConnection().prepareStatement("UPDATE todolist SET completion='" + taskStatus + "' WHERE id=" + id);
            ps.execute();
            System.out.println("Status updated");
        } catch (SQLException e) {
            System.out.println("Task status not updated.");
        }
    }


    public static boolean deleteTask() {
        System.out.print("Enter task's id: ");
        int id = scanner.nextInt();

        try {
            ps = getConnection().prepareStatement("DELETE FROM todolist WHERE id =" + id);
            ps.execute();
            System.out.println("Deleted successfully");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Task getTask() {
        System.out.print("Enter task's id: ");
        int id = scanner.nextInt();

        try {
            ps = getConnection().prepareStatement("SELECT * FROM todolist WHERE id =" + id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Task(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("task"),
                        rs.getDate("deadline"),
                        rs.getBoolean("completion"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Task> getPendingTasks() {

        try {
            ps = getConnection().prepareStatement("SELECT * FROM todolist WHERE deadline > NOW() AND completion is false;");
            rs = ps.executeQuery();

            List<Task> pendingTasks = new ArrayList<>();

            while (rs.next()) {
                pendingTasks.add(new Task(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("task"),
                        rs.getDate("deadline"),
                        rs.getBoolean("completion")
                ));

            }
            return pendingTasks;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    }

    public static void printPendingTasks(List<Task> tasks) {
        System.out.println("Pending tasks: ");
        for (Task task : tasks) {
            System.out.printf("Task ID = %s, username = %s, taskname = %s, deadline = %s, completion = %s\n",
                    task.getId(), task.getUsername(), task.getTask(), task.getDeadline(), task.isCompletion());
        }
    }

    public static List<Task> getSingleUserTasks() {
        System.out.print("Please enter your username: ");
        String username = scanner.nextLine();

        try {
            ps = getConnection().prepareStatement("SELECT * FROM todolist WHERE username='" + username + "'");
            rs = ps.executeQuery();

            List<Task> tasks = new ArrayList<>();

            while (rs.next()) {
                tasks.add(new Task(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("task"),
                        rs.getDate("deadline"),
                        rs.getBoolean("completion")));
            }
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}



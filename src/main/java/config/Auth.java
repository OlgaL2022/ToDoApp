package config;

import menu.TaskMenu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static config.DbConnection.getConnection;

public class Auth {

    static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;


    public static boolean signUp() {

        System.out.print("Enter a username to sign up: ");
        String username = scanner.nextLine();

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
        try {
            ps = getConnection().prepareStatement("INSERT INTO users(username, password) VALUES('" + username + "', '" + password + "')");
            ps.execute();
            System.out.println("Sign up was successful");
            TaskMenu.menu();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

        public static boolean login() {
        System.out.print("Enter the username to login: ");
        String name = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        try {
            ps = getConnection().
                    prepareStatement("SELECT * FROM users WHERE username='" + name + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("username").equals(name) && rs.getString("password").equals(password)) {
                    System.out.println("Login successful");
                    return true;
                } else {
                    System.out.println("Username or password is not correct. Try again");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
            System.out.println("Invalid login credentials. Try again!");
            return false;
        }
}






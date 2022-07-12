package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

        public static Connection getConnection() {
            Connection connection = null;

            try {
                connection = DriverManager.getConnection("jdbc:postgresql://ec2-52-49-120-150.eu-west-1.compute.amazonaws.com:5432/d5g0milab0ivam?user=hspghwfojmvguw&password=a8a679aa11d2a893bbb177d7a5284d13813d2d66b1895dd602f9dd4c08e05331");
                if (connection != null)
                    System.out.println("Connection Successful");
            } catch (SQLException e) {
                System.out.println("There was a problem connecting to the database");
                e.printStackTrace();
            }

            return connection;
        }

}



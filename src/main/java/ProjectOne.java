import java.sql.*;
import java.util.Scanner;

public class ProjectOne {

    private  String url = "jdbc:mysql://127.0.0.1:3306/project_one";
    String username = "root";
    String password = "Aba@9090";

    void createTable() {


        try {
            Connection connection = DriverManager.getConnection(url,username,password);

            Statement statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS users(name Text, email Text, age Integer, location Text,designation Text)");

        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
    }

    public int populateTable() throws SQLException {

        int count = 0;

        try (Connection connection = DriverManager.getConnection(url,username,password);
             Scanner scanner = new Scanner(System.in)) {

            String insertSQL = "INSERT INTO users (Name, Email, Age, Location, Designation) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Enter Name:");
                    String name = scanner.nextLine();

                    System.out.println("Enter Email:");
                    String email = scanner.nextLine();

                    System.out.println("Enter Age:");
                    int age = Integer.parseInt(scanner.nextLine());

                    System.out.println("Enter Location:");
                    String location = scanner.nextLine();

                    System.out.println("Enter Designation:");
                    String designation = scanner.nextLine();

                    // Set values for the prepared statement
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, email);
                    preparedStatement.setInt(3, age);
                    preparedStatement.setString(4, location);
                    preparedStatement.setString(5, designation);

                    // Execute the update and increment the row count
                     preparedStatement.execute();
                     count++;
                }

                System.out.println("Data populated successfully!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return count;

    }

    public static void main(String[] args) throws SQLException {
        ProjectOne projectOne = new ProjectOne();
        projectOne.createTable();

        int count = projectOne.populateTable();
        System.out.println(count);

    }
}

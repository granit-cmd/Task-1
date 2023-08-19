package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.ConnectionManager.open;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(255)," +
                "lastName VARCHAR(255)," +
                "age INT)";
        try( Connection connection = open()) {

            Statement statement = null;

            statement = connection.createStatement();
            statement.execute(createTableSQL);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String dropTableSQL = "DROP TABLE IF EXISTS users";
        try (Connection connection = open()){

            Statement statement = null;

            statement = connection.createStatement();
            statement.execute(dropTableSQL);
            System.out.println("Table dropped successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String  insertSQL = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = open();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println(name + " " + lastName + " " + age);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String deleteSQL = "DELETE FROM users WHERE id = ?";

        try (Connection connection = open();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            preparedStatement.setLong(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User with ID " + id + " removed successfully.");
            } else {
                System.out.println("No user found with ID " + id + ".");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        String selectSQL = "SELECT * FROM users";

        try (Connection connection = open();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");

                User user = new User(name, lastName, age);
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public void cleanUsersTable() {
        String deleteSQL = "DELETE FROM users";

        try (Connection connection = open();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(deleteSQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

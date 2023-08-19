package jm.task.core.jdbc;

import jm.task.core.jdbc.util.ConnectionManager;
import java.sql.*;

import jm.task.core.jdbc.service.UserServiceImpl;
import org.postgresql.Driver;

import static jm.task.core.jdbc.service.UserServiceImpl.*;

public class Main {


    public static void main(String[] args) throws SQLException {
        try (Connection connection = ConnectionManager.open()) {
UserServiceImpl userService = new UserServiceImpl();
//userService.dropUsersTable();
//userService.createUsersTable();
//userService.saveUser("Mike", "Tyson", (byte) 46);
//userService.saveUser("Jonny", "Depp", (byte) 56);
//userService.saveUser("Antjeline", "Joly", (byte) 47);
//userService.saveUser("Johny", "Braun", (byte) 54);
//            System.out.println("Next command");
userService.getAllUsers();

        }

    }
}

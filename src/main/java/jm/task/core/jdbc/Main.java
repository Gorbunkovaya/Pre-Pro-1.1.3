package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.*;
import java.util.List;


public class Main {

    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();

        User user1 = new User("Pam", "Beesly", (byte) 26);
        User user2 = new User("Jim", "Halpert", (byte) 27);
        User user3 = new User("Dwight", "Schrute", (byte) 30);
        User user4 = new User("Angela", "Martin", (byte) 31);

        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        List<User> users = userService.getAllUsers();
        System.out.println(users);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}

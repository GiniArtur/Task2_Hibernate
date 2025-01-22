package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    private static final Logger LOG = getLogger(Main.class.getName());
    private static final User user1 = new User("Art1", "Gin1", (byte) 42);
    private static final User user2 = new User("Art2", "Gin2", (byte) 25);
    private static final User user3 = new User("Art3", "Gin3", (byte) 18);
    private static final User user4 = new User("Art4", "Gin4", (byte) 60);

    public static void main(String[] args) throws SQLException {
        userService.createUsersTable();
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        List<User> strings = userService.getAllUsers();
        for (User s : strings)
            System.out.println(s);
        userService.removeUserById(3);
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeConnection();
    }
}

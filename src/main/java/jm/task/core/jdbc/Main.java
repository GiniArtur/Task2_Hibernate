package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import java.sql.*;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;


public class Main {
    private static final Logger LOG = getLogger(Main.class.getName());


    public static void main(String[] args) throws SQLException {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        UserService userService = new UserServiceImpl();
        System.out.println("Create table");
        userService.createUsersTable();
        System.out.println("Add users --->");
        userService.saveUser("Art", "Gin", (byte) 35);
        userService.saveUser("Art1", "Gin1", (byte) 35);
        userService.saveUser("Art2", "Gin2", (byte) 35);
        userService.saveUser("Art3", "Gin3", (byte) 35);
        userService.saveUser("Art4", "Gin4", (byte) 35);
        userService.saveUser("Art5", "Gin5", (byte) 38);

        System.out.println("All users created and added to the database 'mydb_jdbc' to the Table 'user' ");

        for (User body : userService.getAllUsers()) {
            System.out.println(body.toString());
        }
        System.out.println("Remove user by id = 3");
        userService.removeUserById(3);
        for (User body : userService.getAllUsers()) {
            System.out.println(body.toString());
        }
        System.out.println("Clean table");
        userService.cleanUsersTable();
        System.out.println("Drop table");
        userService.dropUsersTable();
        session.getTransaction().commit();
        session.close();
    }
}

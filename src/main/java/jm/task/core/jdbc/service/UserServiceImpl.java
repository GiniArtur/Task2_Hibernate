package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDaoHibernate = new UserDaoHibernateImpl();

    @Override

    public void createUsersTable() throws SQLException {
        userDaoHibernate.createUsersTable();
    }

    @Override
    public void dropUsersTable() throws SQLException {
        userDaoHibernate.dropUsersTable();
    }


    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        System.out.println("Save new user - " + name + " " + lastName + " " + age);
        userDaoHibernate.saveUser(name, lastName, age);
    }


    @Override
    public void removeUserById(long id) throws SQLException {
        System.out.println("Remove user with id " + id);
        userDaoHibernate.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = List.of();
        users = userDaoHibernate.getAllUsers();
        return users;
    }

    @Override
    public void cleanUsersTable() throws SQLException {
        userDaoHibernate.cleanUsersTable();
    }
}



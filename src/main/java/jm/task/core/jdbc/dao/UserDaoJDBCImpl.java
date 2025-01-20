package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = " CREATE TABLE user (\n" +
                "    id BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    name VARCHAR(255) NOT NULL,\n" +
                "    lastname VARCHAR(255) NOT NULL,\n" +
                "    age TINYINT NOT NULL)";
        try (Connection connection = Util.getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void dropUsersTable() {

        PreparedStatement preparedStatement = null;
        String sql = "DROP TABLE user;";
        try (Connection connection = Util.getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        String sql = "insert into user (name, lastname, age) values (?, ?, ?)";
        try (Connection connection = Util.getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM user WHERE id = ?";
        try (Connection connection = Util.getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String sql = "select * from user";
        Statement statement = null;
        try (Connection connection = Util.getConnection()) {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM user";
        try (Connection connection = Util.getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}






package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connect = Util.getConnect();
             Statement statement = connect.createStatement()) {
            String SQL = "CREATE TABLE IF NOT EXISTS Users " +
                    "(id INT AUTO_INCREMENT, " +
                    "name VARCHAR (45) NOT NULL, " +
                    "lastname VARCHAR (45) NOT NULL, " +
                    "age INT NOT NULL, " +
                    "PRIMARY KEY (id))";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connect = Util.getConnect();
             Statement statement = connect.createStatement()) {

            statement.executeUpdate("DROP TABLE IF EXISTS Users");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connect = Util.getConnect();
             PreparedStatement preparedStatement = connect
                     .prepareStatement("INSERT INTO Users VALUES (?, ?, ?, ?)")) {

            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setByte(4, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connect = Util.getConnect();
             PreparedStatement preparedStatement = connect
                     .prepareStatement("DELETE FROM Users WHERE id=?")) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = null;

        try (Connection connect = Util.getConnect();
             Statement statement = connect
                     .createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            list = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                list.add(user);
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection connect = Util.getConnect();
             Statement statement = connect.createStatement()) {
            statement.execute("TRUNCATE TABLE Users");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

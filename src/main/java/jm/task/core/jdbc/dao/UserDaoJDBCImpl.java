package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = Util.getMySQLConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {
        String sqlTable =
                "CREATE TABLE IF NOT EXISTS `user_schema`.`useruser` (" +
                        "`id` INT NOT NULL AUTO_INCREMENT," +
                        "`name` VARCHAR(45) NOT NULL," +
                        "`lastName` VARCHAR(45) NOT NULL," +
                        "`age` INT(3) NOT NULL," +
                        "PRIMARY KEY (`id`))" +
                        "DEFAULT CHARACTER SET = utf8;";
        try (PreparedStatement prepStatement = connection.prepareStatement(sqlTable)) {
            prepStatement.execute(sqlTable);
        }
    }

    public void dropUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS useruser");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try (PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO useruser (name, lastName, age) VALUES (?, ?, ?)")) {
            prepStatement.setString(1, name);
            prepStatement.setString(2, lastName);
            prepStatement.setByte(3, age);
            prepStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        }
    }

    public void removeUserById(long id) throws SQLException {
        try (PreparedStatement prepStatement = connection.prepareStatement("DELETE FROM useruser WHERE id")) {
            prepStatement.execute("DELETE FROM useruser WHERE id");
        }
    }

    public List<User> getAllUsers() throws SQLException {

        List<User> list = new ArrayList<User>();
        try (PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM useruser")) {
            ResultSet resultSet = prepStatement.executeQuery("SELECT * FROM useruser");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        try (PreparedStatement prepStatement = connection.prepareStatement("DELETE FROM useruser")) {
            prepStatement.execute("DELETE FROM useruser");
        }
    }
}

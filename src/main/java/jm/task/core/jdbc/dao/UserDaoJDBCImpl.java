package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    private Connection connection = getConnection();

    @Override
    public void createUsersTable() {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement =
                    connection.prepareStatement(
                            "CREATE TABLE `mydb`.`users` (" +
                            "  `id` INT NOT NULL AUTO_INCREMENT," +
                            "  `name` VARCHAR(45) NOT NULL," +
                            "  `lastName` VARCHAR(45) NULL," +
                            "  `age` TINYINT(3) NOT NULL," +
                            "  PRIMARY KEY (`id`)" +
                            ") ENGINE=InnoDB DEFAULT CHARACTER SET = utf8");

            preparedStatement.execute();
        } catch (SQLException e){
            System.err.println("Table is not created");
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public void dropUsersTable() {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS users");
            preparedStatement.execute();
        } catch (SQLException e){
            System.err.println("Table is not dropped");
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO users (name, lastName, age)  VALUES(?, ?, ?)";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM users WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            close(statement);
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        dropUsersTable();
        createUsersTable();
    }

    private void close(Statement preparedStatement){
        if(preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
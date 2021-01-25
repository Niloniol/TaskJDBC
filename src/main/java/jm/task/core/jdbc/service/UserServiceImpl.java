package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();

    public void createUsersTable() {
        try{
            daoJDBC.createUsersTable();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try{
            daoJDBC.dropUsersTable();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try{
            daoJDBC.saveUser(name, lastName, age);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try{
            daoJDBC.removeUserById(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = null;
        try{
            userList = daoJDBC.getAllUsers();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try{
            daoJDBC.cleanUsersTable();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

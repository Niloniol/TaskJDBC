package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Igor", "lastname", (byte) 20);
        userService.saveUser("Ivan", "lastname", (byte) 40);
        userService.saveUser("Mihail", "lastname", (byte) 55);
        userService.saveUser("Oksana", "lastname", (byte) 100);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

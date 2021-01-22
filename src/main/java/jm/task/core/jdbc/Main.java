package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Util util = new Util();
        try {
            util.getConnection().close();
        } catch (SQLException e){

        }
    }
}

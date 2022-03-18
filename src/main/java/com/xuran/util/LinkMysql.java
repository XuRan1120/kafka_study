package com.xuran.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author XuRan
 * @Date 2022/3/18 16:29
 * @Version 1.0
 * @Description
 */
public class LinkMysql {
    public static void main(String[] args) throws SQLException {
        Connection connection = JdbcUtil.getConnection();

        String sql = "insert into tmp_X_20220318(order_id) values(?)";

        PreparedStatement statement = connection.prepareCall(sql);
        statement.setString(1,"34567");

        int i = statement.executeUpdate();
        System.out.println(i);

        statement.close();
        connection.close();


    }
}

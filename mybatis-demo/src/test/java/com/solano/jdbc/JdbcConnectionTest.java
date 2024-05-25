package com.solano.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

/**
 * @author github.com/solano33
 * @date 2024/5/24 22:31
 */
@Slf4j
public class JdbcConnectionTest {

    private static final String url;
    private static final String user;
    private static final String password;

    private Connection connection;

    static {
        try {
            url = "jdbc:mysql://localhost:3306/solano";
            user = "root";
            password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void before() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        log.info("before");
    }

    @After
    public void after() throws SQLException {
        connection.close();
        log.info("after");
    }

    @Test
    public void prepareBatchTest() throws SQLException {
        log.info("prepareBatchTest");
        String sql = "SELECT * FROM user";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println("ID: " + id + ", Name: " + name);
        }
        resultSet.close();
        preparedStatement.close();
    }
}

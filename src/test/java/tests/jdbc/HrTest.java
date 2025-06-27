package tests.jdbc;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.sql.*;

public class HrTest {
    ConfigReader configReader = new ConfigReader();
    String jdbcUrl = configReader.getProperty("JDBCURL")+"testdb";
    String userName = configReader.getProperty("JdbcUserName");
    String password = configReader.getProperty("JdbcPassword");

    @Test
    public void checkTableExists(){

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl,userName,password);
            Statement statement = connection.createStatement();
            String checkTableExistQuery = "SELECT TABLE_NAME FROM information_schema.tables " +
                    "WHERE TABLE_SCHEMA = 'hr' AND TABLE_NAME = 'employees' ";
            ResultSet resultSet = statement.executeQuery(checkTableExistQuery);
            Assert.assertTrue(resultSet.next());
            String tableName = resultSet.getString("TABLE_NAME");
            Assert.assertEquals(tableName, "employees", "Table not exists");
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (SQLException err) {
            System.out.println("Error creating table:" + err.getMessage());
        }
    }
}

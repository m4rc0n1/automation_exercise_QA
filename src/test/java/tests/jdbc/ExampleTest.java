package tests.jdbc;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;

import java.sql.*;

public class ExampleTest {
    ConfigReader configReader = new ConfigReader();
    String jdbcUrl = configReader.getProperty("JDBCURL")+"testdb";
    String userName = configReader.getProperty("JdbcUserName");
    String password = configReader.getProperty("JdbcPassword");

    @Test
    public  void testConnection(){
        String createTableQuery = "CREATE TABLE IF NOT EXISTS users (" +
                "id int AUTO_INCREMENT PRIMARY KEY," +
                " name VARCHAR(100) NOT NULL," +
                "email VARCHAR(100) NOT NULL," +
                "age int )";
        try{
            // db connecting
            Connection connection = DriverManager.getConnection(jdbcUrl,userName,password);
            //prepaering statementquery
            Statement statement = connection.createStatement();
            //sending query
            statement.execute(createTableQuery);
            System.out.println("Users table created successfully, or exists");
            statement.close();
            connection.close();
        }catch(SQLException err) {
            System.out.println("Error creating table:"+ err.getMessage());
        }
    }
    @Test
    public  void  insertDataPreparedStatement(){
        Faker faker = new Faker();
        SoftAssert softAssert = new SoftAssert();
        String insertDataQuery = "INSERT INTO users (name, email, age) VALUES (?,?,?)";
        String selectQuery = "SELECT * FROM users WHERE email=?";
        String name = faker.name().username();
        String email = faker.internet().emailAddress();
        String age = "23";

        try{
            Connection connection = DriverManager.getConnection(jdbcUrl,userName,password);
            PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,age);
            int rowsInserted = preparedStatement.executeUpdate();
            Assert.assertEquals(rowsInserted, 1, "Not inserted");
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1,email);
            ResultSet resultSet = selectStatement.executeQuery();
            Assert.assertTrue(resultSet.next());
            String retrievedName = resultSet.getString("name");
            String retrievedEmail = resultSet.getString("email");
            String retrievedAge = resultSet.getString("age");
            softAssert.assertEquals(retrievedName, name, "Names should match");
            softAssert.assertEquals(retrievedEmail + "t", email, "Emails should match");
            softAssert.assertEquals(retrievedAge, age, "Ages should match");
            softAssert.assertAll();
            preparedStatement.close();
            selectStatement.close();
            connection.close();

        }catch (SQLException err){
            System.out.println("Error creating table:"+ err.getMessage());
        }

    }



}

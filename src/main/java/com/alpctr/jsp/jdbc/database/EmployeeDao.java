package com.alpctr.jsp.jdbc.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.alpctr.jsp.jdbc.bean.Employee;

public class EmployeeDao {
	
	public int registerEmployee(Employee employee) throws ClassNotFoundException {
		String INSERT_USERS_SQL = "INSERT INTO employee" 
	            + "  (id, first_name, last_name, username, password, address, contact) VALUES "
				+ " (default, ?, ?, ?, ?, ?, ?);";

		int result = 0;
		
		/*ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("/application-default.properties");
		Properties properties = new Properties();
		try {
			properties.load(input);
		} catch (IOException e1) {
			e1.printStackTrace();
		}   */  
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//System.out.println("DB Connection: " + properties.getProperty("spring.datasource.url"));
		
		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://mysql:3306/mysql_database?useSSL=false", "root", "password");

			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getUsername());
			preparedStatement.setString(4, employee.getPassword());
			preparedStatement.setString(5, employee.getAddress());
			preparedStatement.setString(6, employee.getContact());
			
			System.out.println(preparedStatement);

			result = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}
	
	private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}

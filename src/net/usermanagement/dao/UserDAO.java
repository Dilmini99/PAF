package net.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.usermanagement.model.User;

public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/unit_management?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";

	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (username, password, employeeID) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select userid,username, password, employeeID from users where userid =?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where userid = ?;";
	private static final String UPDATE_USERS_SQL = "update users set username = ?,password= ?, employeeID =? where userid = ?;";
	private static final String SEARCH_USER_BY_ID = "select userid,username, password, employeeID from users where username =? or employeeID=?";
	
	public UserDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		return connection;
	}

	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getEmployeeID());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public User selectUser(int userid) {
		User user = null;
		
		try (Connection connection = getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, userid);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String employeeID = rs.getString("employeeID");
				user = new User(userid, username, password, employeeID);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public List<User> selectAllUsers() {

		
		List<User> users = new ArrayList<>();
		
		try (Connection connection = getConnection();

				
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

		
			while (rs.next()) {
				int userid = rs.getInt("userid");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String employeeID = rs.getString("employeeID");
				users.add(new User(userid, username, password, employeeID));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

	public boolean deleteUser(int userid) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, userid);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmployeeID());
			statement.setInt(4, user.getUserid());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
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
	
	public List < User > searchUsers(String username, String employeeID) {
    	List < User > users = new ArrayList < > ();
       
        try (Connection connection = getConnection();
           
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_USER_BY_ID);) {
        	preparedStatement.setString(1, username);
            preparedStatement.setString(2, employeeID);
            System.out.println(preparedStatement);
           
            ResultSet rs = preparedStatement.executeQuery();

            
            while (rs.next()) {     
            	int userid = rs.getInt("userid");
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                String employeeID1 = rs.getString("employeeID");
                users.add(new User(userid, username1, password1, employeeID1));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return users;
    }

}


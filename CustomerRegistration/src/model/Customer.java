package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/power?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public String insertCustomer(String cName, String cAddress, String cEmail, String cDate, String pno) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into customer1(`cID`,`cName`,`cAddress`,`cEmail`,`cDate`,`pno`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cName);
			preparedStmt.setString(3, cAddress);
			preparedStmt.setString(4, cEmail);
			preparedStmt.setString(5, cDate);
			preparedStmt.setString(6, pno);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String updateCustomer(String cID, String cName, String cAddress, String cEmail, String cDate, String pno) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE customer1 SET cName=?,cAddress=?,cEmail=?,cDate=?,pno=?" + "WHERE cID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, cName);
			preparedStmt.setString(2, cAddress);
			preparedStmt.setString(3, cEmail);
			preparedStmt.setString(4, cDate);
			preparedStmt.setString(5, pno);
			preparedStmt.setInt(6, Integer.parseInt(cID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the customer.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	public String deleteCustomer(String cID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from customer1 where cID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(cID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the customer.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
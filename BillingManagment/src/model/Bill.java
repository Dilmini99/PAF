package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {
	
	public Bill() {
		
	}
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String billInsert(String name, String phone, float units, float bill) {
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			java.sql.PreparedStatement stmt = con.prepareStatement("insert into bills (name, phone, units, bill) values (?, ?, ?, ?)");
            
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setFloat(3, units);
            stmt.setFloat(4, bill);
            
            int i = stmt.executeUpdate();
            
            stmt.close();
            con.close();
            
            output = "Success";
		}
		catch (Exception e) {
			output = "Error while inserting the bill.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String billUpdate(String id, String name, String phone, float units, float bill) {
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			java.sql.PreparedStatement stmt = con.prepareStatement("update bills set name = ?, phone = ?, units = ?, bill = ? where bid = ?");
            
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setFloat(3, units);
            stmt.setFloat(4, bill);
            stmt.setInt(5, Integer.parseInt(id));
            
            int i = stmt.executeUpdate();
            
            stmt.close();
            con.close();
            
            output = "Success";
		}
		catch (Exception e) {
			output = "Error while updating the bill.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readBill() {
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			output = "<table border=\"1\"><tr><th>Bill ID</th><th>Name</th><th>Phone</th><th>Units</th><th>Bill</th></tr>";
			String query = "select * from bills";
			
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			
			while (rs.next()) {
				// Add into the html table
				output += "<tr><td>" + Integer.toString(rs.getInt("bid")) + "</td>";
				output += "<td>" + rs.getString("name") + "</td>";
				output += "<td>" + rs.getString("phone") + "</td>";
				output += "<td>" + Float.toString(rs.getFloat("units")) + "</td>";
				output += "<td>" + Float.toString(rs.getFloat("bill")) + "</td> </tr>";
			}
			
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch(Exception e) {
			output = "Error while reading the bill.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteBill(String id) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from bills where bid=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the bill.";
			System.err.println(e.getMessage());
		}

		return output;
	}
}

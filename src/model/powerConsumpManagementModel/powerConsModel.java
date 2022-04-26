package model.powerConsumpManagementModel;

import java.sql.*;

public class powerConsModel {
	
	//Create database connection
	private Connection conn() {
		
		Connection conn = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/powercons", "root","");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//read data
	public String readData() {
		
		String output = "";
		
		try {
			
			Connection conn = conn();
			
			if(conn==null) {
				return "Error while connect to the powercons database for reading";
			}
			
			//display data in html table
			output = "<table border='1'><tr><th>user id</th>" 
					+"<th>username</th>" 
					+"<th>email</th>" 
					+"<th>month</th>" 
					+"<th>units</th><th>actions</th></tr>";
			
			String qry = "select * from user";
			Statement stmt = conn.createStatement();
			ResultSet rst = stmt.executeQuery(qry);
			
			while(rst.next()) {
				String uId = Integer.toString(rst.getInt("user_id"));
				String uName = rst.getString("user_name");
				String uEmail = rst.getString("email");
				String uMonth = rst.getString("month");
				String uUnit = Integer.toString(rst.getInt("unit"));
				
				//adding data to table
				output += "<tr><td>" + uId + "</td>";
				output += "<td>" + uName + "</td>";
				output += "<td>" + uEmail + "</td>";
				output += "<td>" + uMonth + "</td>";
				output += "<td>" + uUnit + "</td>";
				
				//actions -> update
				output += "<td><input name='btnUpdate' type='button' value='Update'></td></tr>";
			}
			
			conn.close();
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading data";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//insert data
	public String insertData(String userName, String userEmail, String month, int unit) {
		
		String output = "";
		
		try {
			
			Connection conn = conn();
			
			if(conn==null) {
				return "Error while connect to the powercons database for inserting data ";
			}
			
			String insertQuery = "insert into user" + "(`user_name`,`email`,`month`,`unit`)" + 
					 				"values (?, ?, ?, ?)";
			PreparedStatement preps = conn.prepareStatement(insertQuery);
			
			//binding values
			preps.setString(1, userName);
			preps.setString(2, userEmail);
			preps.setString(3, month);
			preps.setInt(4, unit);
			
			//execute the statement
			preps.execute();
			
			conn.close();
			
			output = "Users power consumption details inserted successfully.";

		} catch (Exception e) {
			output = "Error while inserting data";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//update data
	public String updateData(String uID, String userName, String userEmail, String month, String unit) {
		
		String output = "";
		
		try {
			
			Connection conn = conn();
			
			if(conn==null) {
				return "Error while connect to the powercons database for updating";
			}
			
			String updateqry = "UPDATE user SET user_name=?, email=?, month=?, unit=? where user_id=?";
			PreparedStatement pstmt = conn.prepareStatement(updateqry);
			
			
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			pstmt.setString(3, month);
			pstmt.setString(4, unit);
			pstmt.setInt(5, Integer.parseInt(uID));
			
			pstmt.execute();
			
			conn.close();
			
			output = "Updated Successfull";
			
		} catch (Exception e) {
			output = "Error while updating data";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//delete data
	public String deleteData(String uId) {
		String output = "";
		
		try {
			
			Connection conn = conn();
			
			if(conn==null) {
				return "Error while connect to the powercons database for deleting";
			}
			
			String deleteqry = "delete from user where user_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(deleteqry);
			
			//binding values
			ps.setInt(1, Integer.parseInt(uId));
			
			//execute the statement
			ps.execute();
			
			conn.close(); 
			
			output = "Entry deleted";
		} catch (Exception e) {
			output = "Error while deleting data";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//search units by month and user id
	public String getDataByMonthAndId(int user_id) {
		
		String output = "";
		
		try {
			
			Connection conn = conn();
			
			if(conn==null) {
				return "Error while connect to the powercons database for deleting";
			}
			
			//display data in html table
			output = "<table border='1'><tr>"
					+"<th>month</th>" 
					+"<th>units</th><th>actions</th></tr>";
			
			String viewqry = "select month, unit from user where user_id = " + user_id;
			Statement stmt = conn.createStatement();
			ResultSet rst = stmt.executeQuery(viewqry);
			
			while(rst.next()) {
				
				String uMonth = rst.getString("month");
				String uUnit = Integer.toString(rst.getInt("unit"));
				
				//adding data to table
				output += "<td>" + uMonth + "</td>";
				output += "<td>" + uUnit + "</td>";
				
				//actions -> update
				output += "<td><input name='btnUpdate' type='button' value='Update'></td></tr>";
			}
			
			conn.close(); 
			
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading user";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}

}

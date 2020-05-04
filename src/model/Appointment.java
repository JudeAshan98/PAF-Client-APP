package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public class Appointment {

	// A common method to connect to the DB
	private static Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pafdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	// Inserting Appointment
	public String insertAppoinment(String date, String time, String hosId, String patId, String doctorID, String payId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			String query = "INSERT INTO appointment (`date`, `time`, `hospitalID`, `patientID`, `doctorID`,`paymentID`)  VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, date);
			preparedStmt.setString(2, time);
			preparedStmt.setInt(3,Integer.parseInt( hosId));
			preparedStmt.setInt(4, Integer.parseInt(patId));
			preparedStmt.setInt(5, Integer.parseInt(doctorID));
			preparedStmt.setInt(6, Integer.parseInt(payId));

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";

		} catch (Exception e) {
			output = "Error while inserting the Appoinment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Get All Appointment details
	public String GetAllAppoinments() {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for GetAll Appointments.";
			}

			output = "<table border=\"1\"><tr><th>Appointment ID</th><th>Date</th><th>Time</th><th>hospitalID</th>"
					+ "<th>patientID</th><th>doctorID</th><th>paymentID </th><th>Status</th></tr>";
			String query = "select * from appointment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// PreparedStatement preparedStmt = con.prepareStatement(query);

			// preparedStmt.execute();

			while (rs.next()) {
				String AppID = Integer.toString(rs.getInt("appointmentID"));
				String date = rs.getString("date");
				String time = rs.getString("time");
				String hospitalID = Integer.toString(rs.getInt("hospitalID"));
				String patientID = rs.getString("patientID");
				String doctorID = rs.getString("doctorID");
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String Status = rs.getString("appointmentStatus");

				output += "<tr><td>" + AppID + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + time + "</td>";
				output += "<td>" + hospitalID + "</td>";
				output += "<td>" + patientID + "</td>";
				output += "<td>" + doctorID + "</td>";
				output += "<td>" + paymentID + "</td>";
				output += "<td>" + Status + "</td>";
			}

			con.close();
			output += "</table>";
			return output;
		} catch (Exception e) {
			output = "Error while GetAll Appointments.";
			// return output;
			System.err.println(e.getMessage());
		}
		return output;
	}
	// Get Appointment details
		public String GetAppointment(String appointmentID) {
			String output = "";

			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for Get Appointments.";
				}

				output = "<table border=\"1\"><tr><th>Appointment ID</th><th>Date</th><th>Time</th><th>hospitalID</th>"
						+ "<th>patientID</th><th>doctorID</th><th>paymentID </th><th>Status</th></tr>";
				String query = "SELECT * from `appointment` where appointmentID="+appointmentID;
				
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);


				if (rs.next()) {
					String AppID = Integer.toString(rs.getInt("appointmentID"));
					String date = rs.getString("date");
					String time = rs.getString("time");
					String hospitalID = Integer.toString(rs.getInt("hospitalID"));
					String patientID = rs.getString("patientID");
					String doctorID = rs.getString("doctorID");
					String paymentID = Integer.toString(rs.getInt("paymentID"));
					String Status = rs.getString("appointmentStatus");

					output += "<tr><td>" + AppID + "</td>";
					output += "<td>" + date + "</td>";
					output += "<td>" + time + "</td>";
					output += "<td>" + hospitalID + "</td>";
					output += "<td>" + patientID + "</td>";
					output += "<td>" + doctorID + "</td>";
					output += "<td>" + paymentID + "</td>";
					output += "<td>" + Status + "</td>";
				}

				con.close();
				output += "</table>";
				return output;
			} catch (Exception e) {
				output = "Error while  Appointment.";
				System.err.println(e.getMessage());
			}
			return output;
		}

	
	
	// update Appointment
	public String updateAppoinment(String appointmentID, String date, String time, String hospitalID, String patientID, String doctorID, String paymentID, String appointmentStatus, String refundID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			String query = "UPDATE appointment SET date = ? ,time=?,hospitalID=?,patientID=?,doctorID=?,paymentID=?,refundID=?,appointmentStatus=? WHERE appointmentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, date);
			preparedStmt.setString(2, time);
			preparedStmt.setInt(3, Integer.parseInt(hospitalID));
			preparedStmt.setInt(4, Integer.parseInt(patientID));
			preparedStmt.setInt(5, Integer.parseInt(doctorID));
			preparedStmt.setInt(6, Integer.parseInt(paymentID));
			preparedStmt.setInt(7, Integer.parseInt(refundID));
			preparedStmt.setString(8,appointmentStatus);
			preparedStmt.setInt(9, Integer.parseInt(appointmentID));
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while Updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Delete Appointment
	public String DeleteAppoinment(String appointmentID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for Deleting.";
			}
			String query = "delete from appointment where appointmentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(appointmentID)); 
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Appointment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}

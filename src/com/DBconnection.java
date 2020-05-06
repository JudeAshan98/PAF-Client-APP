package com;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {

	public Connection connect() {
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pafdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		}catch (Exception e) {
			System.out.println("Error :" + e);
			e.printStackTrace();
		}
		return con;
		
	}
	
	
}

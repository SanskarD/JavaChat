package org.sanskar.chatapp.dao;

import static org.sanskar.chatapp.utils.ConfigReader.getValue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public interface CommonDao {
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		//Step 1 Load Driver
		Class.forName(getValue("DRIVER"));
		//Step 2 Make Connection
		final String CONNECTION_STRING = getValue("CONNECTION_URL");
		final String USER_ID = getValue("USERID");
		final String PASSWORD = getValue("PASSWORD");
		Connection con = DriverManager.getConnection(CONNECTION_STRING,USER_ID,PASSWORD);
		if(con != null) {
			System.out.println("Connection Created");
		}
		return con;
	}
}

package org.sanskar.chatapp.dao;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.sanskar.chatapp.dto.UserDTO;
import org.sanskar.chatapp.utils.Encryption;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

//User CRUD
public class UserDao {
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		final String query = "select userid from users where userid=? and password=?"; 
		try {
			con = CommonDao.createConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userDTO.getUserID());
			pstmt.setString(2,Encryption.passwordEncrypt(new String(userDTO.getPassword())));
			rSet =  pstmt.executeQuery();
			return rSet.next();
		}finally {
			if(rSet != null) rSet.close();
			if(pstmt != null) pstmt.close();
			if( con != null) con.close();
		}
	}
	public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Connection connection = null;
		 Statement stmt = null;
		 int record;
		 try {
			 connection = CommonDao.createConnection(); // create connection
			 stmt = connection.createStatement();
			 record = stmt.executeUpdate("insert into users (userid,password) values('"+userDTO.getUserID()+"','"+Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"')"); //insert,delete,update			 
			 return record;
		 }
		 finally {
			 if(stmt != null)
			 stmt.close();
			 if(connection != null)
			 connection.close();
		 }
	}
}

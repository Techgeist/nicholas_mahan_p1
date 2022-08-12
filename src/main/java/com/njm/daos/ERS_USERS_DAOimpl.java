package com.njm.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.njm.models.ERS_USERS;
import com.njm.util.ERS_JDBC_CONNECTION_UTIL;
import com.njm.daos.*;

public class ERS_USERS_DAOimpl implements ERS_USERS_DAO {

	Connection conn;

	public ERS_USERS_DAOimpl() {
		conn = ERS_JDBC_CONNECTION_UTIL.establishComms();
	}

	@Override
	public Integer create(ERS_USERS user) {
		// TODO Auto-generated method stub
		try {

			// we are using ? wild card as a placeholder for the values we will set in out
			// prepared statement
			String sql = "INSERT INTO ers_users (ers_users_id, ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) VALUES (default,?,?,?,?,?,?)";

			// note: prepareStatement() throws an SQLException so we must wrap the code wea
			// re trying to execute in a try catch
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getERS_USERNAME());
			pstmt.setString(2, user.getERS_PASSWORD());
			pstmt.setString(3, user.getUSER_FIRST_NAME());
			pstmt.setString(4, user.getUSER_LAST_NAME());
			pstmt.setString(5, user.getUSER_EMAIL());
			pstmt.setInt(6, user.getUSER_ROLE_ID());

			// note that when we are inserting, or deleting we will be using Executeupdate()
			// when we are querying the database we will be using Executequery()
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();

			// the cursor
			rs.next();

			System.out.println(rs.getInt(1) + "I'm in the ERS_USERS DAO create method" + 1);
			return rs.getInt("id");

		} catch (SQLException sqlEx) {
			System.out.println("This is the" + sqlEx.getMessage());
		}
		return null;

	}

	@Override
	public ERS_USERS read(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(int userId, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int userId, String email) {
		// TODO Auto-generated method stub
		return false;
	}

}

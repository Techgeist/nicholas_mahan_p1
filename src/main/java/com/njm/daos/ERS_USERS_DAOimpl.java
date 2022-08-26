package com.njm.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.njm.models.ERS_USERS;
import com.njm.util.ERS_JDBC_CONNECTION_UTIL;

public class ERS_USERS_DAOimpl implements ERS_USERS_DAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(ERS_USERS_DAOimpl.class);

	// now we need to connect JDBC to servlets
	// because of this, we must use a initializer block to connect our DriverManager
	// to Postgres

	// this block is commonly used to declare & initialize the components of an
	// object
	// when it is created
	static {
		try {
			Class.forName("org.postgresql.Driver"); //here we are telling Tomcat that we are using Postgres
		} catch (ClassNotFoundException e) {
			LOGGER.warn("Static initializer block failed: unable to get DB connection -- " + e);
		}
	}

	@Override
	public int create(ERS_USERS ERS_USERS) {
		LOGGER.info("In UserDAOImpl - adding user: " + ERS_USERS);
		int targetId = 0;

		try (Connection conn = ERS_JDBC_CONNECTION_UTIL.establishComms()) {
			String sql = "insert into ers_users (ers_user_id, ers_username,ers_password,user_first_name,user_last_name,user_email,user_role_id ) VALUES (default,?,?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			ps.setInt(0, ERS_USERS.getERS_USERS_ID());
			ps.setString(1, ERS_USERS.getERS_USERNAME());
			ps.setString(2, ERS_USERS.getERS_PASSWORD());
			ps.setString(3, ERS_USERS.getUSER_FIRST_NAME());
			ps.setString(4, ERS_USERS.getUSER_LAST_NAME());
			ps.setString(5, ERS_USERS.getUSER_EMAIL());
			ps.setInt(6, ERS_USERS.getUSER_ROLE_ID());

			int isSuccessfulInsert = ps.executeUpdate();
			LOGGER.info("Successful registration [1 for yes/ 0 for no]: " + isSuccessfulInsert);

			// this will return the new ID number that was created by the DB
			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				targetId = rs.getInt("ers_user_id");
			}
		} catch (SQLException e) {
			LOGGER.warn("Unable to execute SQL query: " + e);
		}

		LOGGER.debug("In UserDAOImpl - create was successfully. New user ID: " + targetId);
		return targetId;
	}

	@Override
	public ERS_USERS selectUserByUsername(String ERS_USERNAME) {
		LOGGER.info("In UserDaoImpl - selectUserByUsername() started. Searching username: " + ERS_USERNAME);
		ERS_USERS target = new ERS_USERS();
		
		//1. open my JDBC connection
		try(Connection conn = ERS_JDBC_CONNECTION_UTIL.establishComms()){
			//2. Prepare our SQL statement
			String sql = "select * from ers_users where ers_username = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ERS_USERNAME);
			
			//3. Execute that statement
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				//here, we will be setting the resultset data to our User object called 'target'
				target.setERS_USERS_ID(rs.getInt("ers_user_id"));
				target.setERS_USERNAME(ERS_USERNAME);
				target.setERS_PASSWORD(rs.getString("ers_password"));
				target.setUSER_FIRST_NAME(rs.getString("user_first_name"));
				target.setUSER_LAST_NAME(rs.getString("user_last_name"));
				target.setUSER_EMAIL(rs.getString("user_email"));
				target.setUSER_ROLE_ID(rs.getInt("user_role_id"));
			}
			
		}catch(SQLException e) {
			LOGGER.warn("Unable to find user: " + e);
		}
		
		//4. return the newly created ID number of the user
		LOGGER.info("In UserDaoImpl - selectUserByUsername() ended. Found user: " + target);
		return target;
	}

	@Override
	public void updateUser(ERS_USERS ERS_USERS) {
		LOGGER.info("In ERS_USERS_DAOimpl - updateUser() started. Updated user infor: " + ERS_USERS);
		
		//open JDBC connection
		try (Connection conn = ERS_JDBC_CONNECTION_UTIL.establishComms()) {
			//prepare our SQL statement
			String sql = "update ers_users set ers_username=? ,ers_password= ?,user_first_name=?,user_last_name=?,user_email=?,user_role_id=? where ers_user_id =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ERS_USERS.getERS_USERNAME());
			ps.setString(2, ERS_USERS.getERS_PASSWORD());
			ps.setString(3, ERS_USERS.getUSER_FIRST_NAME());
			ps.setString(4, ERS_USERS.getUSER_LAST_NAME());
			ps.setString(5, ERS_USERS.getUSER_EMAIL());
			ps.setInt(6, ERS_USERS.getUSER_ROLE_ID());
			
			//3. Execute statement
			int isSuccessfulUpdate = ps.executeUpdate();
			LOGGER.info("Successful update to DB: 1 for yes/0 for no" + isSuccessfulUpdate);
		} catch (SQLException e) {
			LOGGER.warn("unable to update user:" + e);
			
		}
		
		//return newly created ID number of the user
		LOGGER.info("In UsersDAOimpl - updateUser() ended.");
		
	}

	
}



// -------------- OLD CODE I WANT TO SAVE FOR POSSIBLE FUTURE REF	
//	Connection conn;
//	public ERS_USERS_DAOimpl() {
//		conn = ERS_JDBC_CONNECTION_UTIL.establishComms();
//	}
//
//	@Override
//	public int create(ERS_USERS user) {
//		// TODO Auto-generated method stub
//		try {
//
//			
//			
//			
//			// we are using ? wild card as a placeholder for the values we will set in out
//			// prepared statement
//			String sql = "INSERT INTO ers_users (ers_users_id, ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) VALUES (default,?,?,?,?,?,?)";
//
//			// note: prepareStatement() throws an SQLException so we must wrap the code wea
//			// re trying to execute in a try catch
//			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			pstmt.setString(1, user.getERS_USERNAME());
//			pstmt.setString(2, user.getERS_PASSWORD());
//			pstmt.setString(3, user.getUSER_FIRST_NAME());
//			pstmt.setString(4, user.getUSER_LAST_NAME());
//			pstmt.setString(5, user.getUSER_EMAIL());
//			pstmt.setInt(6, user.getUSER_ROLE_ID());
//
//			// note that when we are inserting, or deleting we will be using Executeupdate()
//			// when we are querying the database we will be using Executequery()
//			pstmt.executeUpdate();
//
//			ResultSet rs = pstmt.getGeneratedKeys();
//
//			// the cursor
//			rs.next();
//
//			System.out.println(rs.getInt(1) + "I'm in the ERS_USERS DAO create method" + 1);
//			return rs.getInt("id");
//
//		} catch (SQLException sqlEx) {
//			System.out.println("This is the" + sqlEx.getMessage());
//		}
//		return null;
//
//	}
//
//	@Override
//	public ERS_USERS read(int userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean update(int userId, String email) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean delete(int userId, String email) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}

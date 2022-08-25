package com.njm.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.njm.models.ERS_REIMBURSEMENT;
import com.njm.models.ERS_USERS;
import com.njm.util.ERS_JDBC_CONNECTION_UTIL;

public class ers_reimbursementDAOimpl implements ers_reimbursementDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ERS_USERS_DAOimpl.class);

	@Override
	public int insertReimbursement(ERS_REIMBURSEMENT ERS_REIMBURSEMENT, int ERS_USERS_ID) {
		LOGGER.info("In ers_reimbursementDAOImpl - createReimbursement() started. Adding account: " + ERS_REIMBURSEMENT);
		int targetId = 0;
		//1. open my JDBC connection
		try(Connection conn = ERS_JDBC_CONNECTION_UTIL.establishComms()) {
			//2. Prepare our SQL statement
			String sql = "INSERT INTO ers_reimbursement(reimb_id,reimb_amount,reimb_submitted,reimb_resolved,reimb_descrption,reimb_reciept,reimb_author,reimb_resolver,reimb_status_id,reimb_type_id) VALUES(default,?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			ps.setInt(0, ERS_REIMBURSEMENT.getREIMB_ID());
			ps.setDouble(1, ERS_REIMBURSEMENT.getREIMB_AMOUNT());
			ps.setDate(2, Date.valueOf(ERS_REIMBURSEMENT.getREIMB_SUBMITTED()));
			ps.setDate(3, Date.valueOf(ERS_REIMBURSEMENT.getREIMB_RESOLVED()));
			ps.setString(4, ERS_REIMBURSEMENT.getREIMB_DESCRIPTION());
			ps.setString(5,ERS_REIMBURSEMENT.getREIMB_RECEIPT());
			ps.setInt(6, ERS_REIMBURSEMENT.getREIMB_AUTHOR());
			ps.setInt(7,ERS_REIMBURSEMENT.getREIMB_RESOLVER());
			ps.setInt(8, ERS_REIMBURSEMENT.getREIMB_STATUS_ID());
			ps.setInt(9, ERS_REIMBURSEMENT.getREIMB_TYPE_ID());
			//3. Execute that statement
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			while(rs.next()) {
				targetId = rs.getInt("reimb_id");
				rs.next();
			}
			
			//another db call here
			//to update the owner of this account
			//sql = "UPDATE accounts SET account_user_id = ? WHERE account_id = ?";
			sql = "UPDATE ers_reimbursement SET reimb_author = ? WHERE reimb_id = ?";
			
			PreparedStatement ps2 = conn.prepareStatement(sql); //the reason why I'm doing this is to not have any broken connections for this new account to its owner user
			//therefore, this action requires us to open another DB transction
			
			ps2.setInt(1, ERS_USERS_ID);
			ps2.setInt(2, targetId);
			int isSuccessfulChangeOwner = ps2.executeUpdate();
			LOGGER.info("Successful update of owner of reimbursement" + targetId + " to DB: 1 FOR YES/0 FOR NO: " + isSuccessfulChangeOwner);
			
		}catch(SQLException e) {
			LOGGER.warn("Unable to add new account: " + e);
		}
		
		//4. return the newly created ID number of the user
		LOGGER.info("In UserDaoImpl - createReimbursement() ended. New reimbursement id is: " + targetId);
		return targetId;
	}

}
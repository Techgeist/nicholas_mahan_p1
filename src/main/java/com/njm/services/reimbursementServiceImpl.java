package com.njm.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.njm.daos.ers_reimbursementDAO;
import com.njm.daos.ers_reimbursementDAOimpl;
import com.njm.models.*;

public class reimbursementServiceImpl implements reimbursementService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(reimbursementServiceImpl.class);
	
	private ers_reimbursementDAO ers_reimbursementDAO;
	
	public reimbursementServiceImpl( ers_reimbursementDAOimpl ers_reimbursementDAO) {
		super();
		this.ers_reimbursementDAO = ers_reimbursementDAO;
	
	}
	
	@Override
	public int createNewReimbursement( ERS_REIMBURSEMENT ERS_REIMBURSEMENT, int ERS_USERS_ID) {
		//1. log event start
		LOGGER.info("In reimbursementServiceImpl - createNewReimbursement() started. Account info for user id# " + ERS_USERS_ID +": " + ERS_REIMBURSEMENT);
		
		//2. make my DB call
		int id = ers_reimbursementDAO.insertReimbursement(ERS_REIMBURSEMENT, ERS_USERS_ID);
		
		//3. log event end
		LOGGER.info("In ErsUserServiceImpl - createNewReimbursement() ended. New account id is: " + id);
		
		//4. return data in return statement
		return id;
	}
	
	
}

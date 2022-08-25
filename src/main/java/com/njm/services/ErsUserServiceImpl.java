package com.njm.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.njm.daos.ERS_USERS_DAO;
import com.njm.daos.ERS_USERS_DAOimpl;
import com.njm.models.ERS_USERS;
import com.njm.services.*;

public class ErsUserServiceImpl implements ErsUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ErsUserServiceImpl.class);

	// we need to make an instance of our DAO in order to make DB calls from the DAO
	// methods
	// why did I use both the interface and implmentation class to create a DAO
	// instance in this class?
	// because you want to decouple your code components (aka interface & classes)
	// from the actual project structure

	// Short Reason Why: makes your code less messy (organization)
	// and you can use any part of your code in any part of your application (aka
	// modularization)
	private static ERS_USERS_DAO ERS_USERS_DAO = new ERS_USERS_DAOimpl();

	public int registerUser(ERS_USERS ERS_USERS) {
		// 1. log this event into my log file
		LOGGER.debug("In ErsUserServiceImpl - registerUser() started");

		// 2. use the DAO object to make a call to the DBE
		int id = ERS_USERS_DAO.create(ERS_USERS);

		// 3. return the data that came from the DAO layer
		LOGGER.debug("In ErsUserServiceImpl - registerUser() ended");
		return id;
	}

	@Override
	public boolean loginUser(String ERS_USERNAME, String ERS_PASSWORD) {
		// 1. log event start
		LOGGER.info("In UserServiceImpl - login() started. Credentials: Username=" + ERS_USERNAME + ", Password="
				+ ERS_PASSWORD);

		// 2. make my DB call
		ERS_USERS target = ERS_USERS_DAO.selectUserByUsername(ERS_USERNAME);

		// cinoare tge banes ti see uf tgus us a succesful match
		if (ERS_USERNAME.equalsIgnoreCase(target.getERS_USERNAME())
				&& ERS_PASSWORD.equalsIgnoreCase(target.getERS_PASSWORD())) {
			// 3. log event end
			LOGGER.info("In UserServiceImpl - login() ended. Credentials match!");
			return true;
		} else {
			LOGGER.warn("login unsuccesful credentials do not match");
		}

		LOGGER.info("In ErsUserServiceIMPL - LoginUser() ended");

		return false;
	}

	@Override
	public ERS_USERS getUserByUsername(String ERS_USERNAME) {
		// 1. log event start
		LOGGER.info("In UserServiceImpl - getUserByUsername() started. Username: " + ERS_USERNAME);

		// 2. make my DB call
		ERS_USERS target = ERS_USERS_DAO.selectUserByUsername(ERS_USERNAME);

		// 3. log event end
		LOGGER.info("In UserServiceImpl - getUserByUsername() ended. Found user: " + target);

		// 4. return data in return statement
		return target;
	}

	
}

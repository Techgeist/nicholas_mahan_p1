package com.njm.services;

import com.njm.models.ERS_USERS;

public interface ErsUserService {
	/*
	 * The difference between a DAO layer and a service layer is that:
	 * 	1. The DAO layer's responsibility is to persist and extract data from our database.
	 * 	2. The service layer is responsible for any implementation of application-only functionalities that are considered
	 * a part of the business logic. Business logic is any logical processes that pertain to the company only, usually internal
	 * logic here.
	 */
	
	int registerUser(ERS_USERS user);
	
	boolean loginUser(String ERS_USERNAME, String ERS_PASSWORD);
	
	public ERS_USERS getUserByUsername(String eRS_USERNAME);
}
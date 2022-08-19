package com.njm.daos;

import com.njm.models.ERS_USERS;

public interface ERS_USERS_DAO {

	//here we are making our CRUD methods CREATE READ UPDATE DELETE
	
	//please not that the return types can be whatever u think u needs for your project
	int create(ERS_USERS ERS_USERS);
	
	//here since I am logging in with an existing user I should be able to retrieve that user by ID or name
	//we will then use the user info found and compare that information with the user input that our client entered for this request
	ERS_USERS getByid(int  eRS_USERS_ID);
	
//	ERS_USERS getByName(String name);

//	ERS_USERS read(int userId);
//	
//	boolean update(int userId, String email);
//	
//	boolean delete(int userId, String email);
	
}

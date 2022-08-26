package com.njm.daos;

import com.njm.models.ERS_USERS;

public interface ERS_USERS_DAO {

	//here we are making our CRUD methods CREATE READ UPDATE DELETE
	
	//please not that the return types can be whatever u think u needs for your project
	public int create(ERS_USERS ERS_USERS);
		
	//READ
	public ERS_USERS selectUserByUsername(String ERS_USERNAME);
	
	
	//UPDATE
	public void updateUser(ERS_USERS ERS_USERS);


	
	//DELETE
//	ERS_USERS getByName(String name);
}
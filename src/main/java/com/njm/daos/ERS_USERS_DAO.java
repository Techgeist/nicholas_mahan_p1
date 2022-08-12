package com.njm.daos;

import com.njm.models.ERS_USERS;

public interface ERS_USERS_DAO {

	//here we are making our CRUD methods CREATE READ UPDATE DELETE
	
	//please not that the return types can be whatever u think u needs for your project
	Integer create(ERS_USERS user);

	ERS_USERS read(int userId);
	
	boolean update(int userId, String email);
	
	boolean delete(int userId, String email);
	
}

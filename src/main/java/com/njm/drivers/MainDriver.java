package com.njm.drivers;

import com.njm.daos.ERS_USERS_DAOimpl;
import com.njm.models.ERS_USERS;
import com.njm.util.ERS_JDBC_CONNECTION_UTIL;
import com.revature.util.JDBCConnectionUtil;
import com.njm.daos.*;

public class MainDriver {

	public static void main(String[] args) {
		
	System.out.println(ERS_JDBC_CONNECTION_UTIL.establishComms());
		
	ERS_USERS_DAOimpl userDao = new ERS_USERS_DAOimpl();
	
	ERS_USERS nick = new ERS_USERS("LOST SAUCE","RIPHARAMBE420","Nicholas","Mahan", "ULTRASCEO@HPG.NET",1);
	
	Integer nickId = userDao.create(nick);
	
	System.out.print(nickId);
//		
//		
//	
		
		
	}

}
 
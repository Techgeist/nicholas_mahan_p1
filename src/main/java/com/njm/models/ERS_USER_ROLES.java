package com.njm.models;

public class ERS_USER_ROLES {

	private int ERS_USER_ROLE_ID;
	private String USER_ROLE;

	public ERS_USER_ROLES() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ERS_USER_ROLES(int eRS_USER_ROLE_ID, String uSER_ROLE) {
		super();
		ERS_USER_ROLE_ID = eRS_USER_ROLE_ID;
		USER_ROLE = uSER_ROLE;
	}

	public int getERS_USER_ROLE_ID() {
		return ERS_USER_ROLE_ID;
	}

	public void setERS_USER_ROLE_ID(int eRS_USER_ROLE_ID) {
		ERS_USER_ROLE_ID = eRS_USER_ROLE_ID;
	}

	public String getUSER_ROLE() {
		return USER_ROLE;
	}

	public void setUSER_ROLE(String uSER_ROLE) {
		USER_ROLE = uSER_ROLE;
	}

	@Override
	public String toString() {
		return "ERS_USER_ROLES [ERS_USER_ROLE_ID=" + ERS_USER_ROLE_ID + ", USER_ROLE=" + USER_ROLE + "]";
	}

}

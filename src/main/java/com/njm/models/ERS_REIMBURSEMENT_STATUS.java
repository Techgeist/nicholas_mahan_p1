package com.njm.models;

public class ERS_REIMBURSEMENT_STATUS {

	private int REIMB_STATUS_ID;
	private String REIMB_STATUS;

	public ERS_REIMBURSEMENT_STATUS() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ERS_REIMBURSEMENT_STATUS(int rEIMB_STATUS_ID, String rEIMB_STATUS) {
		super();
		REIMB_STATUS_ID = rEIMB_STATUS_ID;
		REIMB_STATUS = rEIMB_STATUS;
	}
	//constructor w/o REIMB_STATUS_ID
	public ERS_REIMBURSEMENT_STATUS(String rEIMB_STATUS) {
		super();
		REIMB_STATUS = rEIMB_STATUS;
	}

	public int getREIMB_STATUS_ID() {
		return REIMB_STATUS_ID;
	}

	public void setREIMB_STATUS_ID(int rEIMB_STATUS_ID) {
		REIMB_STATUS_ID = rEIMB_STATUS_ID;
	}

	public String getREIMB_STATUS() {
		return REIMB_STATUS;
	}

	public void setREIMB_STATUS(String rEIMB_STATUS) {
		REIMB_STATUS = rEIMB_STATUS;
	}

	@Override
	public String toString() {
		return "ERS_REIMBURSEMENT_STATUS [REIMB_STATUS_ID=" + REIMB_STATUS_ID + ", REIMB_STATUS=" + REIMB_STATUS + "]";
	}

}

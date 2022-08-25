package com.njm.models;

import java.sql.Blob;
import java.sql.Timestamp;
import java.time.LocalDate;

public class ERS_REIMBURSEMENT {

	private int REIMB_ID;
	private double REIMB_AMOUNT;
	private LocalDate REIMB_SUBMITTED;
	private LocalDate REIMB_RESOLVED;
	private String REIMB_DESCRIPTION;
	private String REIMB_RECEIPT;
	private int REIMB_AUTHOR;
	private int REIMB_RESOLVER;
	private int REIMB_STATUS_ID;
	private int REIMB_TYPE_ID;

	public ERS_REIMBURSEMENT() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ERS_REIMBURSEMENT(int rEIMB_ID, int rEIMB_AMOUNT, LocalDate rEIMB_SUBMITTED, LocalDate rEIMB_RESOLVED,
			String rEIMB_DESCRIPTION, String rEIMB_RECEIPT, int rEIMB_AUTHOR, int rEIMB_RESOLVER, int rEIMB_STATUS_ID,
			int rEIMB_TYPE_ID) {
		super();
		REIMB_ID = rEIMB_ID;
		REIMB_AMOUNT = rEIMB_AMOUNT;
		REIMB_SUBMITTED = rEIMB_SUBMITTED;
		REIMB_RESOLVED = rEIMB_RESOLVED;
		REIMB_DESCRIPTION = rEIMB_DESCRIPTION;
		REIMB_RECEIPT = rEIMB_RECEIPT;
		REIMB_AUTHOR = rEIMB_AUTHOR;
		REIMB_RESOLVER = rEIMB_RESOLVER;
		REIMB_STATUS_ID = rEIMB_STATUS_ID;
		REIMB_TYPE_ID = rEIMB_TYPE_ID;
	}

	// Without REIMBURSEMENT_ID
	public ERS_REIMBURSEMENT(int rEIMB_AMOUNT, LocalDate rEIMB_SUBMITTED, LocalDate rEIMB_RESOLVED,
			String rEIMB_DESCRIPTION, String rEIMB_RECEIPT, int rEIMB_AUTHOR, int rEIMB_RESOLVER, int rEIMB_STATUS_ID,
			int rEIMB_TYPE_ID) {
		super();
		REIMB_AMOUNT = rEIMB_AMOUNT;
		REIMB_SUBMITTED = rEIMB_SUBMITTED;
		REIMB_RESOLVED = rEIMB_RESOLVED;
		REIMB_DESCRIPTION = rEIMB_DESCRIPTION;
		REIMB_RECEIPT = rEIMB_RECEIPT;
		REIMB_AUTHOR = rEIMB_AUTHOR;
		REIMB_RESOLVER = rEIMB_RESOLVER;
		REIMB_STATUS_ID = rEIMB_STATUS_ID;
		REIMB_TYPE_ID = rEIMB_TYPE_ID;
	}

	public int getREIMB_ID() {
		return REIMB_ID;
	}

	public void setREIMB_ID(int rEIMB_ID) {
		REIMB_ID = rEIMB_ID;
	}

	public double getREIMB_AMOUNT() {
		return REIMB_AMOUNT;
	}

	public void setREIMB_AMOUNT(double rEIMB_AMOUNT) {
		REIMB_AMOUNT = rEIMB_AMOUNT;
	}

	public LocalDate getREIMB_SUBMITTED() {
		return REIMB_SUBMITTED;
	}

	public void setREIMB_SUBMITTED(LocalDate rEIMB_SUBMITTED) {
		REIMB_SUBMITTED = rEIMB_SUBMITTED;
	
	}

	public LocalDate getREIMB_RESOLVED() {
		return REIMB_RESOLVED;
	}

	public void setREIMB_RESOLVED(LocalDate rEIMB_RESOLVED) {
		REIMB_RESOLVED = rEIMB_RESOLVED;
	}

	public String getREIMB_DESCRIPTION() {
		return REIMB_DESCRIPTION;
	}

	public void setREIMB_DESCRIPTION(String rEIMB_DESCRIPTION) {
		REIMB_DESCRIPTION = rEIMB_DESCRIPTION;
	}

	public String getREIMB_RECEIPT() {
		return REIMB_RECEIPT;
	}

	public void setREIMB_RECEIPT(String rEIMB_RECEIPT) {
		REIMB_RECEIPT = rEIMB_RECEIPT;
	}

	public int getREIMB_AUTHOR() {
		return REIMB_AUTHOR;
	}

	public void setREIMB_AUTHOR(int rEIMB_AUTHOR) {
		REIMB_AUTHOR = rEIMB_AUTHOR;
	}

	public int getREIMB_RESOLVER() {
		return REIMB_RESOLVER;
	}

	public void setREIMB_RESOLVER(int rEIMB_RESOLVER) {
		REIMB_RESOLVER = rEIMB_RESOLVER;
	}

	public int getREIMB_STATUS_ID() {
		return REIMB_STATUS_ID;
	}

	public void setREIMB_STATUS_ID(int rEIMB_STATUS_ID) {
		REIMB_STATUS_ID = rEIMB_STATUS_ID;
	}

	public int getREIMB_TYPE_ID() {
		return REIMB_TYPE_ID;
	}

	public void setREIMB_TYPE_ID(int string) {
		REIMB_TYPE_ID = string;
	}

	@Override
	public String toString() {
		return "ERS_REIMBURSEMENT [REIMB_ID=" + REIMB_ID + ", REIMB_AMOUNT=" + REIMB_AMOUNT + ", REIMB_SUBMITTED="
				+ REIMB_SUBMITTED + ", REIMB_RESOLVED=" + REIMB_RESOLVED + ", REIMB_DESCRIPTION=" + REIMB_DESCRIPTION
				+ ", REIMB_RECEIPT=" + REIMB_RECEIPT + ", REIMB_AUTHOR=" + REIMB_AUTHOR + ", REIMB_RESOLVER="
				+ REIMB_RESOLVER + ", REIMB_STATUS_ID=" + REIMB_STATUS_ID + ", REIMB_TYPE_ID=" + REIMB_TYPE_ID + "]";
	}

}

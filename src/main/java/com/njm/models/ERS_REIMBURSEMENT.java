package com.njm.models;

import java.sql.Blob;
import java.sql.Timestamp;

public class ERS_REIMBURSEMENT {
	
	private int REIMB_ID;
	private int REIMB_AMOUNT;
	private Timestamp REIMB_SUBMITTED;
	private Timestamp REIMB_RESOLVED;
	private String  REIMB_DESCRIPTION;
	private Blob REIMB_RECEIPT;
	private int REIMB_AUTHOR;
	private int REIMB_RESOLVER;
	private int REIMB_STATUS_ID;
	private int REIMB_TYPE_ID;
	
	
}

package com.njm.daos;

import com.njm.models.ERS_REIMBURSEMENT;
import com.njm.models.ERS_USERS;

public interface ers_reimbursementDAO {

	int insertReimbursement(ERS_REIMBURSEMENT ERS_REIMBURSEMENT, int ERS_USERS_ID);

}

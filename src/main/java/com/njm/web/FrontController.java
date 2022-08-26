package com.njm.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//in order to make a class into a servlet, it must extends the HttpServlet!
public class FrontController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontController.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// URI Rewriting = doing some string manpulation in order to get a clean format
		// to my URI pattern
		// then send the request to its appropriate RequestHelper method

		// 1. save the URI and rewrite it to determine what functionality that my user
		// is seeking
		final String URI = req.getRequestURI().replace("/ERSFrontController/", "");

		// 2. log this altered URI to my log files
		LOGGER.debug("User trying to access resource at URI: " + URI);

		// 3. now that we have rewritten the URI, we will use a SWITCH statement to call
		// the appropriate RequestHelper method
		switch (URI) {
		case "register":
			// a. log this choice to log file
			LOGGER.debug("User is trying to register for a new merc account...");

			// b. make the RequestHelper method call
			RequestHelper.processRegistration(req, resp);
			break;
		case "login":
			LOGGER.info("User is trying to login to application using id and name...");
			RequestHelper.processLogin(req, resp);
			break;
		case "reimbursements/new":
			LOGGER.info("User is trying to make a new reimbursement...");
			RequestHelper.processCreateNewReimbursement(req, resp);
			break;
		default:
			LOGGER.warn("No path present: " + URI);
			break;
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//we use URI rewriting to better manage the naviagation of our HTTP requests as they come to the FrontController
		final String URI = req.getRequestURI().replace("/ERSFrontController/", "");
		
		LOGGER.info("User trying to access endpoint: " + URI);
		
		switch(URI) {
			case "users":
				LOGGER.info("User is searching by username...");
				RequestHelper.processSearchByUsername(req, resp);
				break;
			default:
				LOGGER.warn("No path present: " + URI);
				break;
		}
	}

}
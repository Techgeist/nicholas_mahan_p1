package com.njm.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.njm.daos.ERS_USERS_DAOimpl;
import com.njm.daos.ers_reimbursementDAO;
import com.njm.daos.ers_reimbursementDAOimpl;
import com.njm.models.ERS_REIMBURSEMENT;
import com.njm.models.ERS_USERS;
import com.njm.services.ErsUserServiceImpl;
import com.njm.services.ErsUserService;

public class RequestHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestHelper.class);

	// because we are making service method calls here, we need an instance of the
	// UserService object
	private static ErsUserService ErsUserService = new ErsUserServiceImpl();
	private static ers_reimbursementDAO ReimbServ = new ers_reimbursementDAOimpl();

	// These methods will make the service call as well as create the dynamic
	// response that is returning to the client
	@SuppressWarnings("deprecation") // this annotation will suppress the Java compiler of its warnings of deprecated
										// classes/methods
	public static void processRegistration(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// NOTE: These steps are to be followed by only POST RequestHelper methods!!!
		// Not as GET, PATCH, DELETE, etc.
		// 1. log the event
		LOGGER.info("In RequestHelper - processRegistration() started");
		int id = 0;

		// 2. extract the user information from the HTTP request body
		// a. initialize a BufferReader object and a StringBuilder object

		// bufferedreader is a input stream that reads binary / non-human readable data
		// because it is retrieving data from a stream, java will throw I/OException if
		// it is unable to read the characters in stream
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();

		// now I will transfer our Reader data to our StringBuilder object, line by line
		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = sb.toString();

		// now that we have our body, we are going to 1) log, 2) split the body up based
		// on individual parameters of information
		LOGGER.debug("Request body for registration is: " + body);

		// Q: How to split body string up into different info (name, job title,
		// hiredate)

		String[] info = body.replaceAll("\\{", "").replaceAll("\"", "").replaceAll("}", "").split(",");
		List<String> values = new ArrayList<>();

		// each element in the info array, I can calling them pair in this for loop
		// in the loop, appending/adding a string to the values arraylist
		// in that string, we trim the string to look only for the value from the
		// request body

		// reader: { "name": "bob", "jobTitle": "worker", "hiredate": "2022-08-18" }
		// body would have had this same format
		// info[]: ["name: bob", "jobTitle: worker", "hiredate: 2022-08-18"]
		// ex. name=bob
		// result: values["name:bob", etc.]

		for (String pair : info) {
			LOGGER.info("Original body K/V pair: " + pair.trim());
			String valOnly = pair.substring(pair.indexOf(":") + 1).trim();
			LOGGER.info("Going into values arraylist --> " + valOnly);
			values.add(valOnly); // here, I trimmed each string in the body to be just displaying the value
			// aka removed the extra characters and key from the string. Then added it to
			// the values arraylist
		}

		// 3. put that information into a temporary User object before making the
		// service method call
		LOGGER.info("User information extracted is: " + values.toString());


		//a. set the content type of my response to return to the browser
		resp.setContentType("application/json");
		
		//b. here is where we make the service method call
//		int ERS_USERS_ID = Integer.parseInt(values.get(1));
		String ERS_USERNAME = values.get(0);
		String ERS_PASSWORD = values.get(1);
		String USER_FIRST_NAME = values.get(2);
		String USER_LAST_NAME = values.get(3);
		String USER_EMAIL = values.get(4);
		int USER_ROLE_ID = Integer.parseInt(values.get(5));
		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate hiredate = LocalDate.parse(values.get(2), formatter);
		
		ERS_USERS target = new ERS_USERS( ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID);
		LOGGER.info("Target user: " + target);
		
		//4. do the service method call
		id = ErsUserService.registerUser(target);
		
		//convert our response into JSON using Jackson Databind
		PrintWriter pw = resp.getWriter();
		
		//5. write the response that is returning to the client
		if(id != 0) {
			target.setERS_USERS_ID(id);
			
			//this comes from Jackson Databind
			ObjectMapper om = new ObjectMapper();
			
			//now converted our User object into a JSON string that will be added to the response
			String json = om.writeValueAsString(target);
			
			//adding JSON to response
			pw.println(json);
			
			resp.setStatus(200);
			LOGGER.info("New user info: " + target);
		}else{
			//if userId is 0, that means that request was successful but no new resource was made! (status code of 204)
			resp.setStatus(204, "Failed to add account in RequestHelper");
			pw.println("Sorry, system failure. Please try again later.");
		}
		
		LOGGER.info("In RequestHelper - processRegistration() ended");
	}
	
//=======OLD CODE=====================		
//		LOGGER.debug("User information extracted is: " + values.toString());
//		if (body.startsWith("ERS_PASSWORD")) {
//			// a. set the content type of my response to return to the browser
//			resp.setContentType("application/json");
//
//			// b. here is where we make the service method call
//			String ERS_PASSWORD = values.get(0);
//			String USER_FIRST_NAME = values.get(1);
//			String USER_LAST_NAME = values.get(2);
//			int USER_ROLE_ID = Integer.parseInt(values.get(3));
//			String ERS_USERNAME = values.get(4);
//			String USER_EMAIL = values.get(5);
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//			LocalDate hiredate = LocalDate.parse(values.get(2), formatter);
//
//			ERS_USERS target = new ERS_USERS(USER_EMAIL, USER_EMAIL, USER_EMAIL, USER_EMAIL, USER_EMAIL, USER_ROLE_ID);
//			// 4. do the service method call
//			id = ErsUserService.registerUser(target);
//
//			// 5. write the response that is returning to the client
//			if (id != 0) {
//				// convert our response into JSON using Jackson Databind
//				PrintWriter pw = resp.getWriter();
//
//				target.setERS_USERS_ID(id);
//
//				// this comes from Jackson Databind
//				ObjectMapper om = new ObjectMapper();
//
//				// now converted our User object into a JSON string that will be added to the
//				// response
//				String json = om.writeValueAsString(target);
//
//				// adding JSON to response
//				pw.println(json);
//
//				resp.setStatus(200);
//				LOGGER.debug("New user info: " + target);
//			} else {
//				// if userId is 0, that means that request was successful but no new resource
//				// was made! (status code of 204)
//				resp.setStatus(204, "Failed to add account in RequestHelper");
//			}
//		}
//
//		LOGGER.debug("In RequestHelper - processRegistration() ended");
//	}

	public static void processLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// 1extracting info from request body
		LOGGER.info("in RequestHelper - processLogin() started");

		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = sb.toString();

		LOGGER.info("Request body for login is: " + body);

		String[] info = body.replaceAll("\\{", "").replaceAll("\"", "").replaceAll("}", "").split(",");
		List<String> values = new ArrayList<>();

		for (String pair : info) {
			LOGGER.info("Original body K/V pair: " + pair.trim());
			String valOnly = pair.substring(pair.indexOf(":") + 1).trim();
			LOGGER.info("Going into values arraylist --> " + valOnly);
			values.add(valOnly);
		}

		// 3. put that information into a temporary User object before making the
		// service method call
		LOGGER.debug("User information extracted is: " + values.toString());
		boolean isLoggedIn = ErsUserService.loginUser(values.get(0), values.get(1));

		PrintWriter pw = resp.getWriter();
		ObjectMapper om = new ObjectMapper();

		// create the response
		if (isLoggedIn == true) {
			resp.setContentType("application/json");
			resp.setStatus(200);

			// now that we have a successfully logged in user, we must keep track on their
			// session requests
			// therefore we will be adding a HTTP cookie as a response header

			// This cookie can then be used with future, subquent requests as it will hold
			// the user's information within its
			// header info
			ERS_USERS target = ErsUserService.getUserByUsername(values.get(0));
			resp.addCookie(new Cookie("Current-User", target.getERS_USERNAME()));

			// adding JSON to response
			String json = "User was successfully able to log into application.";
			om.writeValueAsString(json);

			resp.setStatus(200);
			LOGGER.info("Login successful - returning cookie in response");
		} else {
			resp.setStatus(401); // UNAUTHORIZED STATUS CODE = 401
			pw.println("Username and/or password didn't match what was on file. Please try again.");
		}
		LOGGER.info("In RequestHelper - processLogin() ended");

	}

		/////////////
		public static void processCreateNewReimbursement(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			LOGGER.info("In RequestHelper - processCreateNewAccount() started");
			int targetId = 0;
			// first I will need to check if the user is currently logged in by checking if
			// there is a cookie present
			ERS_USERS currentUser = new ERS_USERS();
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("Current-User")) {
						LOGGER.debug("Current logged in user is: " + cookie.getValue());
						currentUser = ErsUserService.getUserByUsername(cookie.getValue());
					}
				}
			}
			
			LOGGER.info("User information recieved from cookie: " + currentUser);
			
			//now that I got my current user, let's give them an account based on the info they provided in the request body
			BufferedReader reader = req.getReader();
			StringBuilder sb = new StringBuilder();

			String line = reader.readLine();

			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}

			String body = sb.toString();

			LOGGER.info("Request body for account registration is: " + body);

			String[] info = body.replaceAll("\\{", "").replaceAll("\"", "").replaceAll("}", "").split(",");
			List<String> values = new ArrayList<>();

			for (String pair : info) {
				LOGGER.info("Original body K/V pair: " + pair.trim());
				String valOnly = pair.substring(pair.indexOf(":") + 1).trim();
				LOGGER.info("Going into values arraylist --> " + valOnly);
				values.add(valOnly);
			}

			LOGGER.info("User information extracted is: " + values.toString());
			ERS_REIMBURSEMENT reimbursement = new ERS_REIMBURSEMENT();
		
//			reimbursement.setInt(REIMB_TYPE_ID(values.get(0)));
//			reimbursement.setInt(REIMB_STATUS_ID(values.get(1)));
//			reimbursement.setInt(Double.valueOf(values.get(2)));
			
			
			
			int REIMB_TYPE_ID = Integer.parseInt(values.get(0));
			int REIMB_STATUS_ID = Integer.parseInt(values.get(1));
			int REIMB_AMMOUNT = Integer.parseInt(values.get(2));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			reimbursement.setREIMB_SUBMITTED(LocalDate.parse(values.get(3), formatter));
			
			
			
//			String ERS_USERNAME = values.get(0);
//			String ERS_PASSWORD = values.get(1);
//			String USER_FIRST_NAME = values.get(2);
//			String USER_LAST_NAME = values.get(3);
//			String USER_EMAIL = values.get(4);
//			int USER_ROLE_ID = Integer.parseInt(values.get(5));
			
			// make the service method call
			targetId = ReimbServ.createReimbursement(reimbursement, currentUser.getERS_USERS_ID());
			reimbursement.setREIMB_ID(targetId);
			
			PrintWriter pw = resp.getWriter();
			ObjectMapper om = new ObjectMapper();

			// create the response
			if (targetId != 0) {
				resp.setContentType("application/json");
				resp.setStatus(200);

				// now that we have a successfully logged in user, we must keep track on their
				// session requests
				// therefore we will be adding a HTTP cookie as a response header

				// This cookie can then be used with future, subquent requests as it will hold
				// the user's information within its
				// header info
				//User target = userService.getUserByUsername(values.get(0));
				//resp.addCookie(new Cookie("Current-User", target.getUsername()));

				// adding JSON to response
				pw.println(om.writeValueAsString(reimbursement));

				resp.setStatus(200);
				LOGGER.info("Account creation successful. New account id number: " + targetId);
			} else {
				resp.setStatus(401); // UNAUTHORIZED STATUS CODE = 401
				pw.println("User has not been authorized to perform this operation. Please try again.");
			}
			LOGGER.info("In RequestHelper - processCreateNewAccount() ended");
			
		
	}

}
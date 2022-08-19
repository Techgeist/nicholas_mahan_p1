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
import com.njm.models.ERS_USERS;
import com.njm.services.ErsUserServiceImpl;
import com.njm.services.ErsUserService;

public class RequestHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestHelper.class);

	// because we are making service method calls here, we need an instance of the
	// UserService object
	private static ErsUserService ErsUserService = new ErsUserServiceImpl();

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
		String ERS_PASSWORD = values.get(0);
		String USER_FIRST_NAME = values.get(1);
		String USER_LAST_NAME = values.get(2);
		int USER_ROLE_ID = Integer.parseInt(values.get(3));
		String ERS_USERNAME = values.get(4);
		String USER_EMAIL = values.get(5);
		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate hiredate = LocalDate.parse(values.get(2), formatter);
		
		ERS_USERS target = new ERS_USERS(USER_EMAIL, USER_EMAIL, USER_EMAIL, USER_EMAIL, USER_EMAIL, USER_ROLE_ID);
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
		}else {
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

		// now that we have our body, we are going to 1) log, 2) split the body up based
		// on individual parameters of information
		LOGGER.debug("Request body for registration is: " + body);

		// Q: How to split body string up into different info (name, job title,
		// hiredate)
		String[] info = body.split("&");
		List<String> values = new ArrayList<>();

		for (String pair : info) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}

		// 3. put that information into a temporary User object before making the
		// service method call
		LOGGER.debug("User information extracted is: " + values.toString());
		if (body.startsWith("ers_users_id")) {
			// 2make my temp user for before the service call
//			ERS_USERS target = new ERS_USERS();
//			target.setERS_USERS_ID(Integer.parseInt(values.get(0)));
//			target.setUSER_FIRST_NAME(values.get(1));
			// 3make the service method call
			boolean isLoggedIn = ErsUserService.loginUser(Integer.parseInt(values.get(0)), values.get(1));
			// 4create teh response

			PrintWriter pw = resp.getWriter();
			ObjectMapper om = new ObjectMapper();

			if (isLoggedIn == true) {
				resp.setContentType("application/json");
				resp.setStatus(200);

				// now that we have a successfully logged in user, we must keep track on their
				// session requests.
				// therefore we will be adding a HTTP cookie as a response header

				// this cookie can then be used with future, subsequent requests as it will
				// holfd the uders information within it's headed info
				resp.addCookie(new Cookie("current user", "somerandomnumbergenerator"));

				pw.println("User was succesfully able to log into app");

				resp.setStatus(200);
				LOGGER.info("Login Succesfull - reurning cookie in response");
			} else {
				resp.setStatus(401); // Unauthorized status code
				pw.println("Username and/or password didn't match what was on file please try again");
			}
			LOGGER.info("In RequestHelper - processLogin() ended");
		}

	}

}
package com.njm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ERS_JDBC_CONNECTION_UTIL {
//this utility class will establish a database connection between this Java program and our SQL database
	private static final Logger LOGGER = LoggerFactory.getLogger(ERS_JDBC_CONNECTION_UTIL.class);
	
	public static Connection establishComms() {
		// 1 establish/declare database credentials (URL for the host, USERNAME and
		// PASSWORD)

		// two ways to share credentials a. environmt variables(recommended) b.
		// hardcoded(very insecure)
		// 2 establish our connection's driver manager to make a new connection

		// teh credentials
		// URL jdbc:postgresql://[host url]:[port number]/[database name]
		// USERNAME
		// PASSWORD
		Connection conn = null;

		// List<String> strings = new ArrayList<>();

		try {

			conn = DriverManager.getConnection(System.getenv("db_url"), System.getenv("db_username"),
					System.getenv("db_password"));

		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
		}

		return conn;
	}
}
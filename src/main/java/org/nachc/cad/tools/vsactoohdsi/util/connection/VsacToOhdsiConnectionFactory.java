package org.nachc.cad.tools.vsactoohdsi.util.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import org.nachc.cad.tools.vsactoohdsi.util.auth.VsacToOhdsiAuthProperties;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VsacToOhdsiConnectionFactory {

	public static Connection getConnection() {
		try {
			log.info("Getting connection...");
			String url = VsacToOhdsiAuthProperties.getJdbcUrl();
			String token = VsacToOhdsiAuthProperties.getToken();
			log.info("URL: \n" + url);
			url = url + token;
			log.info("Got properties, getting connection...");
			Connection conn = DriverManager.getConnection(url);
			log.info("Done getting connection.");
			return conn;
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
}

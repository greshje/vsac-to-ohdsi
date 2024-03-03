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
			String schemaName = VsacToOhdsiAuthProperties.getSchemaName();
			url = url + token;
			log.info("Got properties, getting connection...");
			Connection conn = DriverManager.getConnection(url);
			log.info("Got connection, setting schema...");
			String sqlString = "use " + schemaName;
			Database.update(sqlString, conn);
			log.info("Done getting connection and setting schema.");
			return conn;
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
}

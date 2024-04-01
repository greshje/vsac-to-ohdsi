package org.nachc.cad.tools.vsactoohdsi.util.connection;

import java.sql.Connection;

import org.junit.Test;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VsacToOhdsiConnectionFactoryIntegrationTest {

	@Test
	public void shouldGetConnection() {
		log.info("Starting test...");
		Connection conn = null;
		try {
			conn = VsacToOhdsiConnectionFactory.getConnection();
			String sqlString = "select count(*) from vocabulary";
			Database.query(sqlString, conn);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}
	
}

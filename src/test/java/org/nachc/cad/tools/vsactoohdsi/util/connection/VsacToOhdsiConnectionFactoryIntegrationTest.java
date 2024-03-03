package org.nachc.cad.tools.vsactoohdsi.util.connection;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VsacToOhdsiConnectionFactoryIntegrationTest {

	@Test
	public void shouldGetConnection() {
		log.info("Starting test...");
		VsacToOhdsiConnectionFactory.getConnection();
		log.info("Done.");
	}
	
}

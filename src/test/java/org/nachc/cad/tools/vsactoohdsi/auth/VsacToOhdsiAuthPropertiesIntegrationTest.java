package org.nachc.cad.tools.vsactoohdsi.auth;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VsacToOhdsiAuthPropertiesIntegrationTest {

	@Test
	public void shouldGetProperties() {
		log.info("Starting test...");
		String url = VsacToOhdsiAuthProperties.getJdbcUrl();
		String token = VsacToOhdsiAuthProperties.getToken();
		String schemaName = VsacToOhdsiAuthProperties.getSchemaName();
		log.info("Got url: \n" + url);
		assertTrue(url != null);
		assertTrue(token != null);
		assertTrue(schemaName != null);
		log.info("Done.");
	}
	
}

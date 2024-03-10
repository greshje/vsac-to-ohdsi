package org.nachc.cad.tools.vsactoohdsi.util.ohdsi;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.cad.tools.vsactoohdsi.util.connection.VsacToOhdsiConnectionFactory;
import org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm.ConceptDvo;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetOhdsiCodeForVsacConceptIntegrationTest {

	@Test
	public void shouldGetOhdsiConcept() {
		log.info("Starting test...");
		Connection conn = null;
		try {
			log.info("Getting connection...");
			conn = VsacToOhdsiConnectionFactory.getConnection();
			log.info("Getting code...");
			String system = "CVX";
			String code = "207";
			ConceptDvo dvo = GetOhdsiCodeForVsacConcept.exec(system, code, conn);
			log.info("Got row:");
			log.info("\t" + dvo.getConceptId() + "\t" + dvo.getConceptName());
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}
	
}

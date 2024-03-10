package org.nachc.cad.tools.vsactoohdsi.util.ohdsi;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.cad.tools.vsactoohdsi.util.connection.VsacToOhdsiConnectionFactory;
import org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm.OhdsiConceptList;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetOhdsiConceptsForVsacValueSetIntegrationTest {

	private static final String VALUE_SET = FileUtil.getAsString("/test/value-set-codes/value-set-codes.txt");
	
	@Test
	public void shouldGetOhdsiRecords() {
		log.info("Starting test...");
		Connection conn = null;
		try {
			log.info("Getting connection...");
			conn = VsacToOhdsiConnectionFactory.getConnection();
			log.info("Parsing vsac value set...");
			OhdsiConceptList conceptList = GetOhdsiConceptsForVsacValueSet.exec(VALUE_SET, conn);
			log.info("Requested: " + conceptList.getRequested() + " concepts.");
			log.info("Found:     " + conceptList.getFound() + " concepts.");
			log.info("Missing:   " + conceptList.getNotFound() + " concepts.");
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}
	
}

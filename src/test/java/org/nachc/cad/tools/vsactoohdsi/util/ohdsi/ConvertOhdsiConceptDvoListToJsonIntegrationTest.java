package org.nachc.cad.tools.vsactoohdsi.util.ohdsi;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Test;
import org.nachc.cad.tools.vsactoohdsi.util.connection.VsacToOhdsiConnectionFactory;
import org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm.ConceptDvo;
import org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm.OhdsiConceptList;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConvertOhdsiConceptDvoListToJsonIntegrationTest {

	private static final String VALUE_SET_STRING = FileUtil.getAsString("/test/value-set-codes/value-set-codes.txt");

	@Test
	public void shouldGetJson() {
		log.info("Starting test...");
		Connection conn = null;
		try {
			log.info("Getting connection...");
			conn = VsacToOhdsiConnectionFactory.getConnection();
			log.info("Getting JSON string...");
			OhdsiConceptList conceptList = GetOhdsiConceptsForVsacValueSet.exec(VALUE_SET_STRING, conn);
			ArrayList<ConceptDvo> dvoList = conceptList.getDvoList();
			String json = ConvertOhdsiConceptDvoListToJson.exec(dvoList);
			log.info("Got JSON: \n\n" + json + "\n\n");
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}
	
}

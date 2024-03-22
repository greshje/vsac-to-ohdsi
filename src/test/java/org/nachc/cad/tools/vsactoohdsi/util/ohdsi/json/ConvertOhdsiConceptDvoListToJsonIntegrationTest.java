package org.nachc.cad.tools.vsactoohdsi.util.ohdsi.json;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Test;
import org.nachc.cad.tools.vsactoohdsi.util.connection.VsacToOhdsiConnectionFactory;
import org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm.ConceptDvo;
import org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm.OhdsiConceptList;
import org.nachc.cad.tools.vsactoohdsi.util.ohdsi.GetOhdsiConceptsForVsacValueSet;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConvertOhdsiConceptDvoListToJsonIntegrationTest {

	private static final String VALUE_SET_STRING = FileUtil.getAsString("/test/value-set-codes/value-set-codes.txt");

	@Test
	public void shouldGetList() {
		log.info("Starting test...");
		Connection conn = null;
		try {
			log.info("Getting connection...");
			conn = VsacToOhdsiConnectionFactory.getConnection();
			log.info("Getting concepts...");
			OhdsiConceptList conceptList = GetOhdsiConceptsForVsacValueSet.exec(VALUE_SET_STRING, conn);
			ArrayList<ConceptDvo> dvoList = conceptList.getDvoList();
			log.info("Getting JSON...");
			String json = ConvertOhdsiConceptDvoListToJson.exec(dvoList);
			log.info("Got list: \n\n" + json + "\n\n");
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}
	
}

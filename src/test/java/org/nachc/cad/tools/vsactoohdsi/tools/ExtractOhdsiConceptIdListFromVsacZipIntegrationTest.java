package org.nachc.cad.tools.vsactoohdsi.tools;

import java.io.File;
import java.sql.Connection;

import org.junit.Test;
import org.nachc.cad.tools.vsactoohdsi.util.connection.VsacToOhdsiConnectionFactory;
import org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm.OhdsiConceptList;
import org.yaorma.database.Database;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtractOhdsiConceptIdListFromVsacZipIntegrationTest {

	private static final String FILE_NAME = "./test/value-set-zip/RetrieveMultipleValueSets.txt.zip";

	@Test
	public void shouldGetConceptIdList() {
		log.info("Starting test...");
		Connection conn = null;
		try {
			log.info("Getting connection...");
			conn = VsacToOhdsiConnectionFactory.getConnection();
			log.info("Getting file...");
			File file = FileUtil.getFile(FILE_NAME);
			log.info("Got file: \n" + FileUtil.getCanonicalPath(file));
			log.info("Extracting concepts...");
			OhdsiConceptList conceptList = ExtractOhdsiConceptIdListFromVsacZip.exec(file, conn);
			String listAsString = conceptList.getListAsString();
			String msg = "";
			msg += ("\nRequested: " + conceptList.getRequested());
			msg += ("\nFound:     " + conceptList.getFound());
			msg += ("\nNot Found: " + conceptList.getNotFoundCount());
			msg += "\n";
			log.info("Summary: " + msg);
			log.info("OHDSI Concept List: \n" + listAsString + "\n");
			log.info("Summary: " + msg);
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}

}

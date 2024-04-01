package org.nachc.cad.tools.vsactoohdsi.util.parser.vsac.valueset;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.nachc.cad.tools.vsactoohdsi.util.parser.vsac.valueset.dvo.VsacValueSet;
import org.nachc.cad.tools.vsactoohdsi.util.parser.vsac.valueset.dvo.VsacValueSetRow;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VsacValueSetParserIntegrationTest {
	
	private static final String VALUE_SET_STRING = FileUtil.getAsString("/test/value-set-codes/value-set-codes.txt");
	
	@Test
	public void shouldParseVsacValueSet() {
		log.info("Starting test...");
		VsacValueSet valueSet = VsacValueSetParser.parse(VALUE_SET_STRING);
		ArrayList<VsacValueSetRow> rows = valueSet.getRows();
		log.info("Got " + rows.size() + " records");
		for(VsacValueSetRow row : rows) {
			log.info(row.getCodeSystemName() + "\t" + row.getCode() + "\t" + row.getDescriptor());
		}
		log.info("Got " + rows.size() + " records");
		assertTrue(rows.size() == 23);
		log.info("Done.");
	}
	
}

package org.nachc.cad.tools.vsactoohdsi.util.parser.vsac.valueset;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.nachc.cad.tools.vsactoohdsi.util.parser.vsac.valueset.dvo.VsacValueSet;
import org.nachc.cad.tools.vsactoohdsi.util.parser.vsac.valueset.dvo.VsacValueSetRow;

public class VsacValueSetParser {

	public static VsacValueSet parse(String vsacValueSet) {
		CSVFormat format = CSVFormat.newFormat('|').withFirstRecordAsHeader();
		Reader in = new StringReader(vsacValueSet);
		VsacValueSet valueSet = new VsacValueSet();
		CSVParser parser = null;
		try {
			parser = new CSVParser(in, format);
			for (CSVRecord record : parser) {
				VsacValueSetRow row = new VsacValueSetRow();
				valueSet.getRows().add(row);
				row.setOID(getValue(record, "OID"));
				row.setValueSetName(getValue(record, "ValueSetName"));
				row.setExpansionVersion(getValue(record, "ExpansionVersion"));
				row.setCode(getValue(record, "Code"));
				row.setDescriptor(getValue(record, "Descriptor"));
				row.setCodeSystemName(getValue(record, "CodeSystemName"));
				row.setCodeSystemVersion(getValue(record, "CodeSystemVersion"));
				row.setCodeSystemOID(getValue(record, "CodeSystemOID"));
				row.setPurpose(getValue(record, "Purpose"));
			}
		} catch (IOException exp) {
			throw new RuntimeException(exp);
		} finally {
			if(parser != null) {
				try {
					parser.close();
				} catch(Exception exp) {
					throw new RuntimeException(exp);
				}
			}
		}
		return valueSet;
	}
	
	private static String getValue(CSVRecord record, String key) {
		try {
			String rtn = record.get(key);
			if(rtn == null) {
				rtn = "";
			}
			return rtn;
		} catch(Exception exp) {
			return "";
		}
	}
}

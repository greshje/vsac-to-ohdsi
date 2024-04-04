package org.nachc.cad.tools.vsactoohdsi.util.ohdsi;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

import org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm.ConceptDvo;
import org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm.OhdsiConceptList;
import org.nachc.cad.tools.vsactoohdsi.util.parser.vsac.valueset.VsacValueSetParser;
import org.nachc.cad.tools.vsactoohdsi.util.parser.vsac.valueset.dvo.VsacValueSet;
import org.nachc.cad.tools.vsactoohdsi.util.parser.vsac.valueset.dvo.VsacValueSetRow;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetOhdsiConceptsForVsacValueSet {

	public static OhdsiConceptList exec(String vsacValueSetString, Connection conn) {
		VsacValueSet vsacValueSet = VsacValueSetParser.parse(vsacValueSetString);
		ArrayList<VsacValueSetRow> rows = vsacValueSet.getRows();
		OhdsiConceptList rtn = new OhdsiConceptList();
		log.info("Got " + rows.size() + " vsac records.");
		for(VsacValueSetRow row : rows) {
			ConceptDvo dvo = GetOhdsiCodeForVsacConcept.exec(row, conn);
			rtn.add(dvo);
			if(dvo == null) {
				rtn.addNotFound(row);
			}
		}
		return rtn;
	}

}

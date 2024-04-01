package org.nachc.cad.tools.vsactoohdsi.util.ohdsi.list;

import java.util.ArrayList;

import org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm.ConceptDvo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConvertOhdsiConceptDvoListToConceptIdList {

	public static String exec(ArrayList<ConceptDvo> conceptDvoList) {
		log.info("Creating concept list...");
		String rtn = "";
		// add each ConceptDvo as a "concept" element in the "items" array
		for (ConceptDvo conceptDvo : conceptDvoList) {
			String conceptId = conceptDvo.getConceptId() + "";
			rtn += conceptId + "\n";
			log.info("Concept added: " + conceptId);
		}
		log.info("Done getting concept list.");
		return rtn;
	}

}

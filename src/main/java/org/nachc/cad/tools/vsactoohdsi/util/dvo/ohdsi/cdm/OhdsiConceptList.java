package org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm;

import java.util.ArrayList;

import org.nachc.cad.tools.vsactoohdsi.util.ohdsi.list.ConvertOhdsiConceptDvoListToConceptIdList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OhdsiConceptList {

	private int requested = 0;
	
	private int found = 0;
	
	private ArrayList<ConceptDvo> dvoList = new ArrayList<ConceptDvo>();
	
	public void add(ConceptDvo dvo) {
		if(dvo != null) {
			this.dvoList.add(dvo);
			found++;
		}
		this.requested++;
	}
	
	public int getNotFound() {
		return requested - found;
	}

	public String getListAsString() {
		String rtn = ConvertOhdsiConceptDvoListToConceptIdList.exec(dvoList);
		return rtn;
	}
	
}

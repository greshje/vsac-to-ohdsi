package org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm;

import java.util.ArrayList;

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
	
}

package org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm;

import java.util.ArrayList;

import org.nachc.cad.tools.vsactoohdsi.util.ohdsi.list.ConvertOhdsiConceptDvoListToConceptIdList;
import org.nachc.cad.tools.vsactoohdsi.util.parser.vsac.valueset.dvo.VsacValueSetRow;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OhdsiConceptList {

	private int requested = 0;

	private int found = 0;

	private ArrayList<ConceptDvo> dvoList = new ArrayList<ConceptDvo>();

	private ArrayList<VsacValueSetRow> notFoundList = new ArrayList<VsacValueSetRow>();

	public void add(ConceptDvo dvo) {
		if (dvo != null) {
			this.dvoList.add(dvo);
			found++;
		}
		this.requested++;
	}

	public void addNotFound(VsacValueSetRow row) {
		if(row != null) {
			this.notFoundList.add(row);
		}
	}
	
	public ArrayList<VsacValueSetRow> getNotFoundList() {
		return this.notFoundList;
	}

	public int getNotFoundCount() {
		return this.notFoundList.size();
	}

	public String getListAsString() {
		String rtn = ConvertOhdsiConceptDvoListToConceptIdList.exec(dvoList);
		return rtn;
	}

}

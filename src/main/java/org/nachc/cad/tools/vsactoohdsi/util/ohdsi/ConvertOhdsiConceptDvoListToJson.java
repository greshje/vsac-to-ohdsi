package org.nachc.cad.tools.vsactoohdsi.util.ohdsi;

import java.util.ArrayList;

import org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm.ConceptDvo;

import com.google.gson.Gson;
import com.nach.core.util.json.JsonUtil;

public class ConvertOhdsiConceptDvoListToJson {

	public static String exec(ArrayList<ConceptDvo> conceptDvoList) {
		Gson gson = new Gson();
		String json = gson.toJson(conceptDvoList);
		json = JsonUtil.prettyPrint(json);
		return json;
	}

}

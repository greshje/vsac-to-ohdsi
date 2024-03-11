package org.nachc.cad.tools.vsactoohdsi.util.ohdsi;

import java.util.ArrayList;

import org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm.ConceptDvo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nach.core.util.json.JsonUtil;

public class ConvertOhdsiConceptDvoListToJson {

	public static String exec(ArrayList<ConceptDvo> conceptDvoList) {
		Gson gson = new Gson();
		JsonArray itemsArray = new JsonArray();
		// add each ConceptDvo as a "concept" element in the "items" array
		for (ConceptDvo conceptDvo : conceptDvoList) {
			JsonObject conceptObject = new JsonObject();
			JsonElement conceptDvoJsonElement = getJsonElementForConceptDvo(conceptDvo);
			conceptObject.add("concept", conceptDvoJsonElement);
			itemsArray.add(conceptObject);
		}
		// wrap the "items" array in an object
		JsonObject wrapperObject = new JsonObject();
		wrapperObject.add("items", itemsArray);
		String json = gson.toJson(wrapperObject); 
		json = JsonUtil.prettyPrint(json);
		return json;
	}

	public static JsonElement getJsonElementForConceptDvo(ConceptDvo conceptDvo) {
		Gson gson = new Gson();
		JsonElement rtn = gson.toJsonTree(conceptDvo);
		return rtn;
	}

}

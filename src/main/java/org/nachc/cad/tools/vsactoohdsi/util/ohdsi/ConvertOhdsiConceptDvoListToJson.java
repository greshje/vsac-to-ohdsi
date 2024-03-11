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

	public static JsonObject getJsonElementForConceptDvo(ConceptDvo conceptDvo) {
		// create the json object for the dvo
		JsonObject conceptJsonObject = new JsonObject();
		conceptJsonObject.addProperty("CONCEPT_CLASS_ID", conceptDvo.getConceptClassId());
		conceptJsonObject.addProperty("CONCEPT_CODE", conceptDvo.getConceptCode());
		conceptJsonObject.addProperty("CONCEPT_ID", conceptDvo.getConceptId());
		conceptJsonObject.addProperty("CONCEPT_NAME", conceptDvo.getConceptName());
		conceptJsonObject.addProperty("DOMAIN_ID", conceptDvo.getDomainId());
		conceptJsonObject.addProperty("INVALID_REASON", conceptDvo.getInvalidReason());
		// conceptJsonObject.addProperty("INVALID_REASON_CAPTION", conceptDvo.getInvalidReasonCaption()); 
		// conceptJsonObject.addProperty("STANDARD_CONCEPT_CAPTION", conceptDvo.getStandardConceptCaption()); 
		conceptJsonObject.addProperty("STANDARD_CONCEPT", conceptDvo.getStandardConcept());
		conceptJsonObject.addProperty("VOCABULARY_ID", conceptDvo.getVocabularyId());
		conceptJsonObject.addProperty("VALID_START_DATE", conceptDvo.getValidStartDate());
		conceptJsonObject.addProperty("VALID_END_DATE", conceptDvo.getValidEndDate());
		// create the parent object and add the properties
		JsonObject rtn = new JsonObject();
		rtn.add("concept", conceptJsonObject);
		rtn.addProperty("isExcluded", false);
		// done
		return rtn;
	}

}

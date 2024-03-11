package org.nachc.cad.tools.vsactoohdsi.util.ohdsi;

import java.sql.Connection;

import org.nachc.cad.tools.vsactoohdsi.util.auth.VsacToOhdsiAuthProperties;
import org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm.ConceptDvo;
import org.nachc.cad.tools.vsactoohdsi.util.parser.vsac.valueset.dvo.VsacValueSetRow;
import org.ohdsi.sql.SqlRender;
import org.ohdsi.sql.SqlTranslate;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetOhdsiCodeForVsacConcept {

	public static ConceptDvo exec(VsacValueSetRow row, Connection conn) {
		String system = row.getCodeSystemName();
		String code = row.getCode();
		return exec(system, code, conn);
	}
	
	public static ConceptDvo exec(String system, String code, Connection conn) {
		log.info("Getting: " + system + ":" + code);
		String databaseType = VsacToOhdsiAuthProperties.getDatabaseType();
		String[] parameters = {"vocabulary_id", "concept_code"};
		String[] values = {system, code};
		String sqlString = "select * from concept where vocabulary_id = '@vocabulary_id' and concept_code = '@concept_code'";
		SqlRender ren = new SqlRender();
		sqlString = SqlRender.renderSql(sqlString, parameters, values);
		sqlString = SqlTranslate.translateSql(sqlString, databaseType);
		log.info("Database type: " + databaseType);
		log.info("Executing query:\n" + sqlString);
		Data data = Database.query(sqlString, conn);
		ConceptDvo dvo = new ConceptDvo();
		if(data.size() > 0) {
			Row row = data.get(0);
			dvo = new ConceptDvo();
			dvo.setConceptClassId(row.get("conceptClassId"));
			dvo.setConceptCode(row.get("conceptCode"));
			dvo.setConceptId(row.getInt("conceptId"));
			dvo.setConceptName(row.get("conceptName"));
			dvo.setDomainId(row.get("domainId"));
			dvo.setInvalidReason(row.get("invalidReason"));
			dvo.setStandardConcept(row.get("standardConcept"));
			dvo.setValidEndDate(row.get("validEndDate"));
			dvo.setValidStartDate(row.get("validStartDate"));
			dvo.setVocabularyId(row.get("vocabularyId"));
		} else {
			dvo = null;
		}
		return dvo;
	}
	
}

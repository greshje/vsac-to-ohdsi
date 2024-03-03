//
// Data Value Object (DVO) for CONCEPT
//

package org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm;

import java.util.Date;
import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConceptDvo {
	
	private Integer conceptId;

	private String conceptName;

	private String domainId;

	private String vocabularyId;

	private String conceptClassId;

	private String standardConcept;

	private String conceptCode;

	private Date validStartDate;

	private Date validEndDate;

	private String invalidReason;

}

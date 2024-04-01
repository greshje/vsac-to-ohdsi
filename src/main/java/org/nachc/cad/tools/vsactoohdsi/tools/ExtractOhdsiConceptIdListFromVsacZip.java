package org.nachc.cad.tools.vsactoohdsi.tools;

import java.io.File;
import java.sql.Connection;

import org.nachc.cad.tools.vsactoohdsi.util.dvo.ohdsi.cdm.OhdsiConceptList;
import org.nachc.cad.tools.vsactoohdsi.util.ohdsi.GetOhdsiConceptsForVsacValueSet;
import org.nachc.cad.tools.vsactoohdsi.util.zip.UnzipUtil;

import com.google.common.io.Files;
import com.nach.core.util.file.FileUtil;
import com.nach.core.util.guid.GuidFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtractOhdsiConceptIdListFromVsacZip {

	public static OhdsiConceptList exec(File file, Connection conn) {
		log.info("Extracting OHDSI concepts from VSAC zip file...");
		try {
			// make sure the file exists
			String filePath = FileUtil.getCanonicalPath(file);
			boolean fileExists = file.exists();
			if(fileExists == false) {
				throw new RuntimeException("File does not exist: " + filePath);
			}
			// create a copy of the file with a unique name
			log.info("CREATING COPY...");
			File copy = copy(file);
			File unzipped = new File(copy.getParentFile(), copy.getName() + ".dir");
			log.info("UNZIPPING...");
			// unzip the file
			UnzipUtil.unzip(copy, unzipped);
			log.info("Unzipped to: " + FileUtil.getCanonicalPath(unzipped));
			// get the data file
			File dataFile = new File(unzipped, "value-set-codes.txt");
			String vsacValueSetString = FileUtil.getAsString(dataFile);
			// do the extraction
			log.info("Getting concepts...");
			OhdsiConceptList conceptList = GetOhdsiConceptsForVsacValueSet.exec(vsacValueSetString, conn);
			// delete the copied files
			log.info("Deleting copy: \n" + FileUtil.getCanonicalPath(file));
			FileUtil.rmdir(copy);
			log.info("Deleting unzipped files: \n" + FileUtil.getCanonicalPath(unzipped));
			FileUtil.rmdir(unzipped);
			return(conceptList);
		} finally {
			
		}
	}

	private static File copy(File src) {
		try {
			log.info("src: " + FileUtil.getCanonicalPath(src));
			String fileName = GuidFactory.getGuid();
			fileName += ".zip";
			File dst = new File(src.getParent(), fileName);
			log.info("dst: " + FileUtil.getCanonicalPath(dst));
			Files.copy(src, dst);
			if(dst.exists() == false) {
				throw new RuntimeException("Could not copy file to: " + FileUtil.getCanonicalPath(dst));
			}
			return dst;
		} catch(Exception exp) {
			throw(new RuntimeException(exp));
		}
	}
	
}

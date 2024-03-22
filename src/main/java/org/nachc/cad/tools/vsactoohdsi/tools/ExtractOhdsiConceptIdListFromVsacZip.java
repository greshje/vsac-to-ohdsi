package org.nachc.cad.tools.vsactoohdsi.tools;

import java.io.File;
import java.sql.Connection;

import org.nachc.cad.tools.vsactoohdsi.util.zip.UnzipUtil;

import com.google.common.io.Files;
import com.nach.core.util.file.FileUtil;
import com.nach.core.util.guid.GuidFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtractOhdsiConceptIdListFromVsacZip {

	public static void exec(File file, Connection conn) {
		log.info("Extracting OHDSI concepts from VSAC zip file...");
		try {
			String filePath = FileUtil.getCanonicalPath(file);
			boolean fileExists = file.exists();
			if(fileExists == false) {
				throw new RuntimeException("File does not exist: " + filePath);
			}
			log.info("CREATING COPY...");
			File copy = copy(file);
			log.info("UNZIPPING...");
			File unzipped = UnzipUtil.unzip(copy, copy.getParentFile());
			log.info("Unzipped to: " + FileUtil.getCanonicalPath(unzipped));
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

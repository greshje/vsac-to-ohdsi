package org.nachc.cad.tools.vsactoohdsi.util.zip;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnzipUtilIntegrationTest {

	private static final File ZIP_FILE = FileUtil.getFromProjectRoot("./src/main/resources/test/value-set-zip/RetrieveMultipleValueSets.txt.zip");

	private static final String TARGET_DIR_NAME = "test";
	
	private static final String CODE_FILE_NAME = "value-set-codes.txt";
	
	@Test
	public void shouldUnzipFile() {
		log.info("Starting test...");
		// get and remove the target dir
		File targetDir = new File(ZIP_FILE.getParentFile(), TARGET_DIR_NAME);
		FileUtil.rmdir(targetDir);
		assertTrue(targetDir.exists() == false);
		// unzip the file and assert the target was created
		File destFile = UnzipUtil.unzip(ZIP_FILE, targetDir);
		log.info(FileUtil.getCanonicalPath(destFile));
		assertTrue(destFile.exists() == true);
		// assert that the file we want was correctly extracted
		File vsFile = new File(destFile, CODE_FILE_NAME);
		log.info(FileUtil.getCanonicalPath(vsFile));
		assertTrue(vsFile.exists() == true);
		// echo the results
		String msg = "";
		msg += "Zip file unzipped:\n";
		msg += "    src: " + FileUtil.getCanonicalPath(ZIP_FILE) + "\n";
		msg += "    dst: " + FileUtil.getCanonicalPath(destFile) + "\n";
		msg += "    txt: " + FileUtil.getCanonicalPath(vsFile);
		log.info(msg);
		log.info("Done.");
	}

}

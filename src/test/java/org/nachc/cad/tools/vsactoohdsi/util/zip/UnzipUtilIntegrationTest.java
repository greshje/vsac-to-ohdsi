package org.nachc.cad.tools.vsactoohdsi.util.zip;

import java.io.File;

import org.junit.Test;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnzipUtilIntegrationTest {

	private static final File ZIP_FILE = FileUtil.getFromProjectRoot("./src/main/resources/test/value-set-zip/RetrieveMultipleValueSets.txt.zip");
	
	@Test
	public void shouldUnzipFile() {
		log.info("Starting test...");
		log.info("Done.");
	}
	
}

package org.nachc.cad.tools.vsactoohdsi.util.zip;

import java.io.File;

import com.nach.core.util.file.ZipUtil;

public class UnzipUtil {

	public static File unzip(File zipFile) {
		File targetDir = zipFile.getParentFile();
		File rtn = ZipUtil.unzip(zipFile, targetDir);
		return rtn;
	}
	
}

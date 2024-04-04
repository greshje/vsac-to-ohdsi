package org.nachc.cad.tools.vsactoohdsi.util.auth;

import java.io.File;
import java.util.Properties;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.props.PropertiesUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VsacToOhdsiAuthProperties {

	private static final String AUTH_FILE_POINTER = "./auth/VsacToOhdsiAuth.properties";
	
	private static final Properties PROPS;
	
	static {
		log.info("Initializing auth properties...");
		File propsFile = new File(AUTH_FILE_POINTER);
		log.info("PROPS FILE: \n" + FileUtil.getCanonicalPath(propsFile));
		Properties srcFileProps = null;
		if(propsFile.exists()) {
			log.info("USING USER DEFINED PROPS");
			srcFileProps = PropertiesUtil.getAsProperties(propsFile); 
		} else {
			log.info("USING DEFAULT PROPS");
			srcFileProps = PropertiesUtil.getAsProperties(AUTH_FILE_POINTER);
		}
		String fileName = srcFileProps.getProperty("AuthFile");
		log.info("Got properties file name: \n" + fileName);
		File authFile = new File(fileName);
		log.info("Found properties file: " + authFile.exists());
		PROPS = PropertiesUtil.getAsProperties(authFile);
		log.info("Auth successfully initialized.");
	}
	
	public static String getJdbcUrl() {
		return PROPS.getProperty("JdbcUrl");
	}
	
	public static String getToken() {
		return PROPS.getProperty("Token");
	}
	
	public static String getSchemaName() {
		return PROPS.getProperty("SchemaName");
	}
	
	public static String getDatabaseType() {
		return PROPS.getProperty("DatabaseType");
	}
	
}

package org.nachc.cad.tools.vsactoohdsi.util.auth;

import java.io.File;
import java.util.Properties;

import com.nach.core.util.props.PropertiesUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VsacToOhdsiAuthProperties {

	private static final String AUTH_FILE_POINTER = "/auth/VsacToOhdsiAuth.properties";
	
	private static final Properties PROPS;
	
	static {
		log.info("Initializing auth properties...");
		Properties srcFileProps = PropertiesUtil.getAsProperties(AUTH_FILE_POINTER);
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
	
}

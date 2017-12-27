package it.flavio.snk.database;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseHelper {
	
	private static final Logger logger = LogManager.getLogger(DatabaseHelper.class);

	/**
	 * Creates the application database if it does not exist
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public void createDatabase() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		
		String userSQL = getSQLFromFile(classLoader, DatabaseConstants.DDL_USER);
		String followerUserSQL = getSQLFromFile(classLoader, DatabaseConstants.DDL_FOLLOWER_USER);
		String messageSQL = getSQLFromFile(classLoader, DatabaseConstants.DDL_MESSAGE);

		try (Connection conn = DriverManager.getConnection(DatabaseConstants.DB_URL); 
				Statement stmt = conn.createStatement()) {
			stmt.execute(userSQL);
			stmt.execute(followerUserSQL);
			stmt.execute(messageSQL);
		} catch (SQLException e) {
			logger.fatal("Failure reported when creating schema", e);
		}
		
	}

	private String getSQLFromFile(ClassLoader classLoader, String ddlFile) throws IOException {
		File userFile = new File(classLoader.getResource(ddlFile).getFile());
		String userSQL = FileUtils.readFileToString(userFile, StandardCharsets.UTF_8);
		return userSQL;
	}

}

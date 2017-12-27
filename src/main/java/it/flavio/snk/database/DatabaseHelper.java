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

	public void createDatabase() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		
		File userFile = new File(classLoader.getResource(DatabaseConstants.DDL_USER).getFile());
		String userSQL = FileUtils.readFileToString(userFile, StandardCharsets.UTF_8);

		File followerUserFile = new File(classLoader.getResource(DatabaseConstants.DDL_FOLLOWER_USER).getFile());
		String followerUserSQL = FileUtils.readFileToString(followerUserFile, StandardCharsets.UTF_8);

		File messageFile = new File(classLoader.getResource(DatabaseConstants.DDL_MESSAGE).getFile());
		String messageSQL = FileUtils.readFileToString(messageFile, StandardCharsets.UTF_8);
		
		try (Connection conn = DriverManager.getConnection(DatabaseConstants.DB_URL); 
				Statement stmt = conn.createStatement()) {
			stmt.execute(userSQL);
			stmt.execute(followerUserSQL);
			stmt.execute(messageSQL);
		} catch (SQLException e) {
			logger.fatal("Failure reported when creating schema", e);
		} 
		
	}

}

package it.flavio.snk;

import java.io.IOException;

import it.flavio.snk.database.DatabaseHelper;
import it.flavio.snk.service.UserDataService;

public class Application {

	public void execute() throws IOException {
		DatabaseHelper dbHelper = new DatabaseHelper();
		dbHelper.createDatabase();
	}

	public void testDB() {
		UserDataService ds = new UserDataService();
		ds.getAllUsers();
		ds.getAllFollowers();
		ds.getAllFollowed();
	}
}

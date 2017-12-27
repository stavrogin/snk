package it.flavio.snk;

import java.io.IOException;

import it.flavio.snk.database.DatabaseHelper;

public class Application {
	
	public void execute() throws IOException {
		DatabaseHelper dbHelper = new DatabaseHelper();
		dbHelper.createDatabase();
	}
}

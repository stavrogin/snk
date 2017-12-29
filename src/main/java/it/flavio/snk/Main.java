package it.flavio.snk;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.flavio.snk.command.CommandFactory;
import it.flavio.snk.service.DataService;
import it.flavio.snk.service.DataServiceImpl;

/**
 * Hello world!
 *
 */
public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);

//	posting: <user name> -> <message>
//	reading: <user name>
//	following: <user name> follows <another user>
//	wall: <user name> wall

	
	
	public static void main(String[] args) throws IOException {
		logger.info("Starting app!");
		DataService dataService = new DataServiceImpl();
		
		Application app = new Application();
		app.console(dataService);
//		app.testDB();
		logger.info("Exiting");
	}
}

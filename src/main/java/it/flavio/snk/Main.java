package it.flavio.snk;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.flavio.snk.service.DataService;
import it.flavio.snk.service.DataServiceFactory;

/**
 * 
 * Main class to run the SNK application
 * Available command list:
 * 
 * 	posting: <user name> -> <message>
 *	reading: <user name>
 *	following: <user name> follows <another user>
 *	wall: <user name> wall
 *  exit
 *
 * @author flavio
 *
 */
public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) throws IOException {
		logger.info("*** Starting app! ***");
		
		DataService dataService = DataServiceFactory.getDataService();
		
		Application app = new Application();
		app.setDataService(dataService);
		app.console();
		
		logger.info("Exiting");
	}
}

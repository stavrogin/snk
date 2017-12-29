package it.flavio.snk.service;

/**
 * 
 * Factory class to create the proper data service
 * @author flavio
 *
 */
public class DataServiceFactory {
	
	/**
	 * Gets the proper data service
	 * @return the proper data service implementation
	 */
	public static DataService getDataService() {
		return new DataServiceImpl();
	}
	
}

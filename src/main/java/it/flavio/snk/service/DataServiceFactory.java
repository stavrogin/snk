package it.flavio.snk.service;

/**
 * Factory class to create the proper data service
 * @author flavio
 */
public class DataServiceFactory {
	
	/**
	 * Gets the proper data service
	 * @param persistenceUnitName the persistence unit name
	 * @return the proper data service implementation
	 */
	public static DataService getDataService(String persistenceUnitName) {
		DataService dataService = new DataServiceImpl();
		dataService.setPersistenceUnitName(persistenceUnitName);
		return dataService;
	}
	
}

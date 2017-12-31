package it.flavio.snk.console;

/**
 * Factory to return a new console implementation
 * @author flavio
 */
public class ConsoleFactory {
	
	/**
	 * Gets the console implementation
	 * @return the console implementation
	 */
	public static Console getConsole() {
		return new ConsoleImpl();
	}
	
}

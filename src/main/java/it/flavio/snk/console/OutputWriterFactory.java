package it.flavio.snk.console;

/**
 * Factory to return a new console implementation
 * @author flavio
 */
public class OutputWriterFactory {
	
	/**
	 * Gets the console implementation
	 * @return the console implementation
	 */
	public static OutputWriter getConsole() {
		return new ConsoleImpl();
	}
	
}

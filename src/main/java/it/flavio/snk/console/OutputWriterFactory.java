package it.flavio.snk.console;

/**
 * Factory to return a new output writer implementation
 * @author flavio
 */
public class OutputWriterFactory {
	
	/**
	 * Gets the output implementation
	 * @return the output implementation
	 */
	public static OutputWriter getOutput() {
		return new ConsoleImpl();
	}
	
}

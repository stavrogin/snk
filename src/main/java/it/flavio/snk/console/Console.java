package it.flavio.snk.console;

import it.flavio.snk.database.model.Message;

public interface Console {

	/**
	 * Writes a message string to the console output
	 * @param message the message to write
	 */
	void write(String message);
	
	/**
	 * Writes a wall message to the console output
	 * @param message the message to be written
	 */
	void writeWallMessage(Message message);
	
	/**
	 * Writes a timeline message to the console output
	 * @param message the message to be written
	 */
	void writeTimelineMessage(Message message);
	
}

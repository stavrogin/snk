package it.flavio.snk.utils;

import java.io.ObjectInputStream.GetField;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import it.flavio.snk.database.model.Message;

/**
 * 
 * Utility class for operations on console
 * @author flavio
 *
 */
public class ConsoleUtils {

	private static final String DAYS_AGO = "days ago";
	private static final String HOURS_AGO = "hours ago";
	private static final String MINUTES_AGO = "minutes ago";
	private static final String SECONDS_AGO = "seconds ago";
	
	
	/**
	 * Writes a message string on the console output
	 * @param message the String message to write on the console output
	 */
	public static void write(String message) {
		System.out.println(message);
	}
	
	/**
	 * Writes a message on the console output
	 * @param message the Message message to write on the console output
	 */
	public static void write(Message message) {
		String text = message.getMessage();
		String timeGap = getTimeGapAsString(Message message);
		StringBuilder sb = new StringBuilder();
		sb.append(text).append(" ").append(timeGap);
		write(sb.toString());
	}
	
	/**
	 * Gets the time gap between now and the message insertion date in a readable string
	 * @param message the message
	 * @return the readable time gap
	 */
	private static String getTimeGapAsString(Message message) {
		
		Instant now = Instant.now();
		Instant msgInstant = message.getInsertts().toInstant();
		
		long gap = ChronoUnit.DAYS.between(msgInstant, now);
		if (gap > 0) {
			return getFormattedGap(gap, DAYS_AGO);
		}
		gap = ChronoUnit.HOURS.between(msgInstant, now);
		if (gap > 0) {
			return getFormattedGap(gap, HOURS_AGO);
		}
		gap = ChronoUnit.MINUTES.between(msgInstant, now);
		if (gap > 0) {
			return getFormattedGap(gap, MINUTES_AGO);
		}
		gap = ChronoUnit.SECONDS.between(msgInstant, now);
		if (gap > 0) {
			return getFormattedGap(gap, SECONDS_AGO);
		}
		return null;
	}
	
	/**
	 * Returns the formatted gap (e.g.: 5 mintues ago)
	 * @param gap the gap time
	 * @param unit the time unit
	 * @return the formatted string
	 */
	private static String getFormattedGap(long gap, String unit) {
		return String.format("(%d %s)", gap, unit);
	}
	
}

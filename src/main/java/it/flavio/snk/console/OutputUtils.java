package it.flavio.snk.console;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import it.flavio.snk.database.model.Message;

/**
 * 
 * Utility class for operations on console
 * @author flavio
 *
 */
public class OutputUtils {

	private static final String DAYS_AGO = "days ago";
	private static final String HOURS_AGO = "hours ago";
	private static final String MINUTES_AGO = "minutes ago";
	private static final String SECONDS_AGO = "seconds ago";
	private static final String NOW = "now";
	
	/**
	 * Gets the time gap between now and the message insertion date in a readable string
	 * @param message the message
	 * @return the readable time gap
	 */
	public static String getTimeGapAsString(Message message) {
		
		Instant now = Instant.now();
		Instant msgInstant = message.getInsertts().toInstant();
		
		long gap = ChronoUnit.DAYS.between(msgInstant, now);
		if (gap > 0) {
			return String.format("(%d %s)", gap, DAYS_AGO);
		}
		gap = ChronoUnit.HOURS.between(msgInstant, now);
		if (gap > 0) {
			return String.format("(%d %s)", gap, HOURS_AGO);
		}
		gap = ChronoUnit.MINUTES.between(msgInstant, now);
		if (gap > 0) {
			return String.format("(%d %s)", gap, MINUTES_AGO);
		}
		gap = ChronoUnit.SECONDS.between(msgInstant, now);
		if (gap > 0) {
			return String.format("(%d %s)", gap, SECONDS_AGO);
		}
		return String.format("(%s)", NOW);
	}
	
}

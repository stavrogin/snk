package it.flavio.snk.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Singleton containing app configuration
 * @author flavio
 */
public class Settings {
	
	private static Logger logger = LogManager.getLogger(Settings.class);
	
	private static Settings settings;
	private long timelineMaxLength;
	private long wallMaxLength;
	
	/**
	 * Get or create the singleton instance
	 * @return the singleton instance
	 */
	public static Settings getInstance() {
		if (settings == null) {
			settings = new Settings();
			init();
		}
		return settings;
	}
	
	/**
	 * Init singleton
	 */
	private static void init() {
		try {
			InputStream is = Settings.class.getClassLoader().getResourceAsStream("configuration.properties");
			Properties p = new Properties();
			p.load(is);
			settings.timelineMaxLength = Long.parseLong(p.getProperty("timeline.maxlength"));
			settings.wallMaxLength = Long.parseLong(p.getProperty("wall.maxlength"));
		} catch (IOException e) {
			logger.warn("Cannot read configuration.properties; setting values to defualt");
			settings.timelineMaxLength = 10;
			settings.wallMaxLength = 10;
		}
	}
	
	/**
	 * Gets the maximum length of the user timeline
	 * @return the maximum length of the user timeline
	 */
	public long getTimelineMaxLength() {
		return timelineMaxLength;
	}
	
	/**
	 * Gets the maximum length of the user wall
	 * @return the maximum length of the user wall
	 */
	public long getWallMaxLength() {
		return wallMaxLength;
	}
	
}

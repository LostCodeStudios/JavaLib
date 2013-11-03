/**
 * 
 */
package com.lostcode.javalib.utils;

import java.util.HashMap;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.utils.Logger;

/**
 * @author MadcowD A logging utility for all JavaLib projects.
 */
public final class LogManager {
	
	/**
	 * Contains the logging types for GDX applications.
	 * 
	 * @author William
	 * @created Jul 29, 2013
	 */
	public enum LogType {
		NONE(Application.LOG_NONE), INFO(Application.LOG_INFO), ERROR(
				Application.LOG_ERROR), DEBUG(Application.LOG_DEBUG);

		private int value;

		private LogType(int value) {
			this.value = value;
		}

		/**
		 * The int value of the LoggerType
		 * 
		 * @return The LogType as an int.
		 */
		public int asInt() {
			return value;
		}
	}

	private static HashMap<String, Logger> logs;

	// region INIT
	/**
	 * Initializes the logger with a specific logType under which specific logs
	 * function.
	 * 
	 * @param application
	 *            The application in which the Logger will run.
	 * @param lT
	 *            The maximum LogType that the application will print. (NONE ->
	 *            ERROR -> INFO -> DEBUG);
	 */
	public static void init(Application application, LogType lT) {
		application.setLogLevel(lT.asInt());
		logs = new HashMap<String, com.badlogic.gdx.utils.Logger>();
	}

	/**
	 * Initializes the logger with a logtype that allows all logs to operate.
	 * 
	 * @param application
	 */
	public static void init(Application application) {
		LogManager.init(application, LogType.DEBUG);
	}

	// endregion INIT

	// region Logging Functions

	/**
	 * Logs a message of a specific LogType to a registered (or to be
	 * registered) log in the Logger.
	 * 
	 * @param logName
	 *            The name of the log to which the message will be sent.
	 * @param logType
	 *            The type of the log message to be sent.
	 * @param message
	 *            The message to be sent.
	 */
	private static void log(String logName, LogType logType, String message) {
		Logger log = LogManager.getLog(logName, logType);
		
		// Invoke the log to log a message
		switch (logType) {
		
		case INFO:
			log.info(message);
			break;
		case DEBUG:
			log.debug(message);
			break;
		case ERROR:
			log.error(message);
			break;
		case NONE:
			// Nothing will be logged
			break;
			
		}
	}

	/**
	 * Writes an info message to a specified log.
	 * 
	 * @param logName
	 *            The log to send the info message.
	 * @param message
	 *            The message to send the log.
	 */
	public static void info(String logName, String message) {
		LogManager.log(logName, LogType.INFO, message);
	}

	/**
	 * Writes a debug message to a specified log.
	 * 
	 * @param logName
	 *            The log to send the debug message.
	 * @param message
	 *            The message to send the log.
	 */
	public static void debug(String logName, String message) {
		LogManager.log(logName, LogType.DEBUG, message);
	}

	/**
	 * Writes an error message to a specified log.
	 * 
	 * @param logName
	 *            The log to send the error message.
	 * @param message
	 *            The message to send the log.
	 */
	public static void error(String logName, String message) {
		LogManager.log(logName, LogType.ERROR, message);
	}

	// endregion Logging Functions

	// region Getters/Setters
	
	/**
	 * Retrieves a log from the LogManager or creates one if it doesn't exist.
	 * 
	 * @param logName
	 *            The name of the log to retrieve.
	 * @param type
	 * 			  The type of the log to retrieve.   
	 * @return The specified log from the manager.
	 */
	public static Logger getLog(String logName, LogType type) {
		// If the log does not exist in the current logs list.
		if (!logs.containsKey(logName)) {
			// Create a new log with logName.
			logs.put(logName, new com.badlogic.gdx.utils.Logger(logName, type.asInt()));
		}
		
		return logs.get(logName);
	}
	
	/**
	 * @param logName The name of the log to return
	 * @return A debug log of the given name.
	 */
	public static Logger getLog(String logName) {
		return LogManager.getLog(logName, LogType.DEBUG);
	}
	
	// endregion

}

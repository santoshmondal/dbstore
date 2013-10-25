package common.util.reflection;

import org.apache.log4j.Logger;

/**
 * REFERENCE::
 * http://veerasundar.com/blog/2009/08/log4j-tutorial-additivity-what-and-why/
 * 
 * @author santoshm
 *
 */
public class CommonLogger {

	// POINTS rootLogger
	private static final Logger LOG = Logger.getLogger(CommonLogger.class);
	private static final Logger LOG_FILE_STORE = Logger.getLogger("FILE_STORE");
	private static final Logger LOG_DAILY_FILE_STORE = Logger.getLogger("DAILY_FILE_STORE");
	private static final Logger LOG_CUSTOM = Logger.getLogger("CUSTOM");

	public static void main(String args[]) {
		demoRootLogger();
		demoFileStoreLogger();
		demoDailyFileStoreLogger();
		demoCustomLogger();
	}

	public static void demoRootLogger() {
		LOG.info("Root Logger Demo");
	}

	public static void demoFileStoreLogger() {
		LOG_FILE_STORE.info("File_Store Logger Demo");
	}

	public static void demoDailyFileStoreLogger() {
		LOG_DAILY_FILE_STORE.info("Daily_File_Store Logger Demo");
	}

	public static void demoCustomLogger() {
		LOG_CUSTOM.info("Custom Logger Demo");
	}
}

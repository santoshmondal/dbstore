package common.util.velocity;

import java.util.logging.Logger;

/*
 * JUL: Java utility Logger.
 */
public class JulLogger {

	private static final Logger LOG = Logger.getLogger(JulLogger.class.getName());

	public static void main(String args[]) {
		consoleLog();
	}

	public static void consoleLog() {
		LOG.info("Console logging");
	}

}

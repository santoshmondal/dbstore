package common.util.config;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class Config {
	private static final Logger LOG = Logger.getLogger(Config.class);
	private static final String CONFIG = "config";
	private static ResourceBundle RB_CONFIG;

	static {
		try {
			RB_CONFIG = ResourceBundle.getBundle(CONFIG);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public static String getProperty(String key) {
		try {
			return RB_CONFIG.getString(key);
		} catch (Exception e) {
			LOG.error(e);
			return null;
		}

	}

	public static void main(String[] args) {
		LOG.info(getProperty("test"));
	}
}

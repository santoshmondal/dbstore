package common.util.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/*
 * Reference::
 * http://stackoverflow.com/questions/6608795/what-is-the-difference-between-class-getresource-and-classloader-getresource
 */
public class ReadSteramUtility {

	private static final Logger LOG = Logger.getLogger(ReadSteramUtility.class);

	private static final String CLASS_LOADER_CONFIG = "config.properties";
	private static final String CLASS_CONFIG = "packconfig.properties";
	private static Properties prop = new Properties();

	public static void main(String args[]) {
		readUsingClass();
		readUsingClassLoader();
		readUsingClassLoaderThread();
		readUsingResouceBundle();
		readUsingPropResourceBundle();
		readExternalProperty();
	}

	public static void readUsingClass() {
		InputStream ras = null;
		try {
			// WILL THROW NULL
			/*ras = ReadSteramUtility.class.getResourceAsStream(CONFIG_PROP);
			prop.load(ras);
			LOG.info("CLASS::" + prop);
			ras.close();*/

			// nw look inside the package
			ras = ReadSteramUtility.class.getResourceAsStream(CLASS_CONFIG);
			prop.load(ras);
			LOG.info("PACKAGE_CLASS::" + prop);
			ras.close();
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public static void readUsingClassLoader() {
		try {
			InputStream ras = ReadSteramUtility.class.getClassLoader().getResourceAsStream(CLASS_LOADER_CONFIG);
			prop.load(ras);
			LOG.info("CLASS_LOADER::" + prop);
			ras.close();

			// using class loader to read inside the package
			ras = ReadSteramUtility.class.getClassLoader().getResourceAsStream("common/util/config/packconfig.properties");
			prop.load(ras);
			LOG.info("PACKAGE_CLASS_LOADER::" + prop);
			ras.close();
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public static void readUsingClassLoaderThread() {
		try {
			InputStream ras = Thread.currentThread().getContextClassLoader().getResourceAsStream(CLASS_LOADER_CONFIG);
			prop.load(ras);
			LOG.info("THREAD_CLASS_LOADER::" + prop);
			ras.close();
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public static void readUsingResouceBundle() {
		try {
			ResourceBundle rb = ResourceBundle.getBundle("config");
			LOG.info("ResourceBundle::" + rb.keySet());

			rb = ResourceBundle.getBundle("common/util/config/packconfig");
			LOG.info("Package_ResourceBundle::" + rb.keySet());
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public static void readUsingPropResourceBundle() {
		try {
			InputStream is = ReadSteramUtility.class.getClassLoader().getResourceAsStream("config.properties");
			ResourceBundle rb = new PropertyResourceBundle(is);
			LOG.info("PROPResourceBundle" + rb.keySet());
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public static void readExternalProperty() {
		try {
			InputStream is = new FileInputStream(new File("external.properties"));
			Properties iprop = new Properties();
			iprop.load(is);
			LOG.info("External::" + iprop);
		} catch (Exception e) {
			LOG.error(e);
		}
	}
}

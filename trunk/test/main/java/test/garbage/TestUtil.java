package test.garbage;

import org.apache.log4j.Logger;

public class TestUtil {

	private static final Logger LOG = Logger.getLogger(TestUtil.class);

	public static void main(String args[]) throws Exception {
		mysqltest();
	}

	public static void mysqltest() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		LOG.info("DONE");
	}
}

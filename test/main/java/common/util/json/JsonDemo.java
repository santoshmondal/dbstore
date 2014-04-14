package common.util.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import common.util.file.FileUtil;

public class JsonDemo {
	private static final Logger LOG = Logger.getLogger(JsonDemo.class);

	public static void main(String args[]) {
		jsonToObject();
		// objectToJson();
	}

	public static void jsonToObject() {
		try {
			String json = FileUtil.readFile("user.json", null, true);
			json = "{\"age\":29, \"id\":123}";
			User user = (User) JsonUtil.jsonToObject(json, User.class.getName());
			LOG.info(user);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public static void objectToJson() {
		User user = new User();
		user.setAge(29);
		user.setName("rediff");

		List<String> msg = new ArrayList<String>();
		user.setMessages(msg);

		String json = JsonUtil.objectToJson(user);
		LOG.info(json);

	}
}

package common.util.jms;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.amqp_1_0.jms.impl.ConnectionFactoryImpl;
import org.apache.qpid.amqp_1_0.jms.impl.QueueImpl;
import org.apache.qpid.amqp_1_0.jms.impl.TopicImpl;

public class Publisher {

	public static void main(String[] args) throws Exception {

		String user = env("ACTIVEMQ_USER", "admin");
		String password = env("ACTIVEMQ_PASSWORD", "password");
		String host = env("ACTIVEMQ_HOST", "localhost");
		int port = Integer.parseInt(env("ACTIVEMQ_PORT", "5672"));
		String destination = arg(args, 0, "topic://event");

		int messages = 10000;
		int size = 256;

		String DATA = "abcdefghijklmnopqrstuvwxyz";
		String body = "";
		for (int i = 0; i < size; i++) {
			body += DATA.charAt(i % DATA.length());
		}

		ConnectionFactoryImpl factory = new ConnectionFactoryImpl(host, port, user, password);
		Destination dest = null;
		if (destination.startsWith("topic://")) {
			dest = new TopicImpl(destination);
		} else {
			dest = new QueueImpl(destination);
		}

		Connection connection = factory.createConnection(user, password);
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageProducer producer = session.createProducer(dest);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		for (int i = 1; i <= messages; i++) {
			TextMessage msg = session.createTextMessage("#:" + i);
			msg.setIntProperty("id", i);
			producer.send(msg);
			if (i % 1000 == 0) {
				System.out.println(String.format("Sent %d messages", i));
			}
		}

		producer.send(session.createTextMessage("SHUTDOWN"));
		Thread.sleep(1000 * 3);
		connection.close();
		System.exit(0);
	}

	private static String env(String key, String defaultValue) {
		String rc = System.getenv(key);
		if (rc == null) {
			return defaultValue;
		}
		return rc;
	}

	private static String arg(String[] args, int index, String defaultValue) {
		if (index < args.length) {
			return args[index];
		} else {
			return defaultValue;
		}
	}

}
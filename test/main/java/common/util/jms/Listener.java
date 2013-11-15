package common.util.jms;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.amqp_1_0.jms.impl.ConnectionFactoryImpl;
import org.apache.qpid.amqp_1_0.jms.impl.QueueImpl;
import org.apache.qpid.amqp_1_0.jms.impl.TopicImpl;

public class Listener {

	public static void main(String[] args) throws JMSException {

		String user = env("ACTIVEMQ_USER", "admin");
		String password = env("ACTIVEMQ_PASSWORD", "password");
		String host = env("ACTIVEMQ_HOST", "localhost");
		int port = Integer.parseInt(env("ACTIVEMQ_PORT", "5672"));
		String destination = arg(args, 0, "topic://event");

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
		MessageConsumer consumer = session.createConsumer(dest);
		long start = System.currentTimeMillis();
		long count = 1;
		System.out.println("Waiting for messages...");
		while (true) {
			Message msg = consumer.receive();
			if (msg instanceof TextMessage) {
				String body = ((TextMessage) msg).getText();
				if ("SHUTDOWN".equals(body)) {
					long diff = System.currentTimeMillis() - start;
					System.out.println(String.format("Received %d in %.2f seconds", count, 1.0 * diff / 1000.0));
					connection.close();
					System.exit(1);
				} else {
					try {
						if (count != msg.getIntProperty("id")) {
							System.out.println("mismatch: " + count + "!=" + msg.getIntProperty("id"));
						}
					} catch (NumberFormatException ignore) {
					}
					if (count == 1) {
						start = System.currentTimeMillis();
					} else if (count % 1000 == 0) {
						System.out.println(String.format("Received %d messages.", count));
					}
					count++;
				}

			} else {
				System.out.println("Unexpected message type: " + msg.getClass());
			}
		}
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
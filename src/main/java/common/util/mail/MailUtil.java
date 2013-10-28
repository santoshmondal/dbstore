package common.util.mail;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import common.util.CommonUtil;
import common.util.config.Config;

public class MailUtil {

	private static final Logger LOG = Logger.getLogger("EXTERNAL_SERVICE");
	private static final Logger LOG_RECEPIENT = Logger.getLogger("LOG_RECEPIENT");

	private static Properties properties = null;
	private static final String MAIL_CONTENT_FORMAT = "text/html";
	private static ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(Integer.parseInt(Config.getProperty("NUMBER_OF_THREAD")));

	static {
		properties = new Properties();
		properties.put("mail.smtp.host", Config.getProperty("mail.smtp.host"));
		properties.put("mail.smtp.port", Config.getProperty("mail.smtp.port"));
		properties.put("mail.smtp.connectiontimeout", Config.getProperty("mail.smtp.connectiontimeout"));
		properties.put("mail.smtp.timeout", Config.getProperty("mail.smtp.timeout"));
		properties.put("mail.smtp.localhost", Config.getProperty("mail.smtp.localhost"));

		LOG.info("SMTP DETAILS::" + Config.getProperty("mail.smtp.host") + ", " + Config.getProperty("mail.smtp.port") + ", " + Config.getProperty("NUMBER_OF_THREAD"));
	}

	public static void postMailTask(final String mailFrom, final String mailTo[], final String mailSubject, final String mailBody) {

		newFixedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				Thread.currentThread().setName("POST_MAIL_SENDER");
				try {
					long startTime = System.currentTimeMillis();
					Session session = Session.getDefaultInstance(properties, null);
					session.setDebug(false);

					Message msg = new MimeMessage(session);
					InternetAddress addressFrom = new InternetAddress(mailFrom);
					msg.setFrom(addressFrom);

					InternetAddress[] addressTo = new InternetAddress[mailTo.length];
					for (int i = 0; i < mailTo.length; i++) {
						addressTo[i] = new InternetAddress(mailTo[i]);
						addressTo[i].validate();
					}
					msg.setRecipients(Message.RecipientType.TO, addressTo);
					msg.setSubject(mailSubject);
					msg.setContent(mailBody, MAIL_CONTENT_FORMAT);

					Transport.send(msg);

					String[] headers = msg.getHeader("Message-ID");
					String sMessageId = "";
					if (null != headers && headers.length > 0) {
						sMessageId = headers[0];
					}

					long endTime = System.currentTimeMillis();
					LOG.info(Arrays.asList(mailTo).toString() + ", " + mailFrom + ", " + mailSubject + ", " + sMessageId + ", " + CommonUtil.getTimeDifference(startTime, endTime) + ", " + Thread.currentThread().getId());
					LOG_RECEPIENT.info("SUCCESS, " + Arrays.asList(mailTo).toString());
				} catch (AddressException ex) {
					ex.printStackTrace();
					LOG.error("FAILURE," + Arrays.asList(mailTo).toString() + ", " + ex.getMessage());
					LOG_RECEPIENT.error("FAILURE," + Arrays.asList(mailTo).toString() + ", " + ex.getMessage());
				} catch (MessagingException ex) {
					ex.printStackTrace();
					LOG.error("FAILURE," + Arrays.asList(mailTo).toString() + ", " + ex.getMessage());
					LOG_RECEPIENT.error("FAILURE," + Arrays.asList(mailTo).toString() + ", " + ex.getMessage());
				} catch (Exception ex) {
					ex.printStackTrace();
					LOG.error("FAILURE," + Arrays.asList(mailTo).toString() + ", " + ex.getMessage());
					LOG_RECEPIENT.error("FAILURE," + Arrays.asList(mailTo).toString() + ", " + ex.getMessage());
				}
			}
		});
	}

	public static ExecutorService getNewFixedThreadPool() {
		return newFixedThreadPool;
	}

	public static void setNewFixedThreadPool(ExecutorService newFixedThreadPool) {
		MailUtil.newFixedThreadPool = newFixedThreadPool;
	}

	public static void main(String args[]) {
		LOG.info("MAIL UTIL");

		String mailFrom = "santoshm@rediff.co.in";
		String[] mailTo = { "santoshm@rediff.co.in" };
		String mailSubject = "DEMO::NEWS LETTER " + Calendar.getInstance().get(Calendar.DATE);
		String mailBody = "<b>DEMO::MAIL BODY!!</b>";

		MailUtil.postMailTask(mailFrom, mailTo, mailSubject, mailBody);
		System.out.println("Mail TO::" + Arrays.asList(mailTo).toString() + "::" + new Date());

		newFixedThreadPool.shutdown();
	}
}

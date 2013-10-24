package common.util.log;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class CustomLog4jAppender extends AppenderSkeleton {

	private String prop1;
	private String prop2;

	@Override
	protected void append(LoggingEvent event) {
		System.out.println("Custom Logger::Start.");
		LocationInfo locationInformation = event.getLocationInformation();
		System.out.println(locationInformation.getLineNumber());
		System.out.println(prop1 + "::" + prop2);
		System.out.println("Custom Logger::END.");
	}

	@Override
	public void close() {
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	public String getProp1() {
		return prop1;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public String getProp2() {
		return prop2;
	}

	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}

}

/****************************************************************************************************/
/*								Basics of runnable jar
/****************************************************************************************************/
Run the default main launch.
	java -jar ETLApps.jar
	


Run the specific main by passing the launcher name.
	java -cp ETLApps.jar com.rediff.etl.util.MailSender
	



Run the launcher with the specific memory arguments.
	java -Xms64m -Xmx256m HelloWorld
	
	


Run the launcher with JMX settings.
	# AT LOCAL HOST
	java -XX:+UseConcMarkSweepGC 
		-Dcom.sun.management.jmxremote 
		-Dcom.sun.management.jmxremote.port=9010  
		-Dcom.sun.management.jmxremote.local.only=false 
		-Dcom.sun.management.jmxremote.authenticate=false 
		-Dcom.sun.management.jmxremote.ssl=false 
		-cp ETLApps.jar com.rediff.newsletter.NewsLetterExecutor &

	# AT REMOTE HOST
	java  -XX:+UseConcMarkSweepGC 
		-Dcom.sun.management.jmxremote 
		-Djava.rmi.server.hostname=202.137.233.147 
		-Dcom.sun.management.jmxremote.port=9010  
		-Dcom.sun.management.jmxremote.local.only=false 
		-Dcom.sun.management.jmxremote.authenticate=false 
		-Dcom.sun.management.jmxremote.ssl=false 
		-cp ETLApps.jar com.rediff.newsletter.NewsLetterExecutor &






References::
	http://tomcat.apache.org/tomcat-7.0-doc/monitoring.html
	http://docs.oracle.com/cd/E13150_01/jrockit_jvm/jrockit/jrdocs/refman/optionX.html
	http://www.oracle.com/technetwork/java/javase/tech/vmoptions-jsp-140102.html			
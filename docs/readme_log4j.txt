Log4j Component::
	logger 
	appender
	layout

Logger::
	logger lets you specify what to log. 
	logger can be root logger or custom logger.
	custom logger required when you want to log in two different file.
	while using custom logger, the parent logger also get the logging event what custom logger is getting.
	Custom logger can override the behavior of logging of same event in two places using additivity property.
	
Appender::
	Appender lets you specify where to log.
	Appender can be console, file, database.
	Appender can be custom also.
	Refer the link below for more.
	
Layout::
	layout lets you specify the format of the log in which it will be written in appender.
		
	

Log4j additivity Property::
http://veerasundar.com/blog/2009/08/log4j-tutorial-additivity-what-and-why/

Custom Appender::
http://stackoverflow.com/questions/6072389/how-to-create-a-own-appender-in-log4j/16194360#16194360
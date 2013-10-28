/****************************************************************************************************/
/*				Basics of Config.properties reading
/****************************************************************************************************/
Reference::
	http://stackoverflow.com/questions/6608795/what-is-the-difference-between-class-getresource-and-classloader-getresource
	

Reading config can be of three types::
	1. reading the config at class level. 
	2. reading the config at package level. 
	3. reading the config/extrenal propety not included in the source level or package level. or inside the jar. Basically
       if you want to read the some configuration from the filesystem.
     

Lets assume we have a source structure following::
	src--
		com--
			core--
				Config.java
				pack_config.properties
		config.properties
	external.propeties

Usage at class level::
	Config.class.getClassLoader().getResourceAsStream("config.properties");
		
		
At Package level::
	Config.class.getResourceAsStream("pack_config.properties");
	This will try to read the file from the following location com/core/pack_config.properties.
	
	
Reading External Property
	Config.class.getClassLoader().getResourceAsStream("external.properties");
		
		
There is a similiarity in syntax reading the at class level and reading external properties. But there is huge difference in 
the class level the property file is part of the jar/runnable_jar. But in the external it resides at the same folder with the
jar/runnabl_jar.		
/****************************************************************************************************/
/*								Basics of Maven
/****************************************************************************************************/
Installation of Maven. 
pom.xml (with dtd).
Maven Plugin.

Maven Repository
	local repos
	central repos
	remote repos

Maven Dependecy




/****************************************************************************************************/
/*								Eclipse and Maven
/****************************************************************************************************/
Installation of maven plugin into eclipse.
	With kepler java ee developer ide its integrated with eclipse. 

	
Make java project using maven.
	Option-1
		New->Maven->Maven Project->
		Check the option create simple project.
		And choose the packaging option as jar. 
		Fill the other required field.
	Options-2-(Preference)
		New->Maven->Maven Project->
		Dont check the option of create simple project.->
		Click Next->
		Select the archtype-->maven-archetype-quickstart
		fill the required fields.
	
	
		
Mave web project using maven.
	Option-1
		New->Maven->Maven Project->
		Dont check the option of create simple project.->
		Click Next->
		Select the archtype-->maven-archetype-webapp
		fill the required fields.
		Add the source folder into the class path if required.
		Add server runtime library.
				
	


Convert existing java project in maven based java project.
	Right Click on the project
		-> configure
		-> Covert to Maven project. 
		-> Choose the packaging jar for java project
		-> it will create the pom file.
		-> add the goal into the build tag.
		


	
Convert existing web project in maven based web project.
	Right Click on project ->Configure->Covert to Maven Project->
	Select Packaging option war.
	Referesh the buildpath libraries.
	



Adding External libraries into the maven other than maven dependency.
	http://stackoverflow.com/questions/5692256/maven-best-way-of-linking-custom-external-jar-to-my-project
	Refer::https://devcenter.heroku.com/articles/local-maven-dependencies
	
	create folder::lib/gid/aid/version/aid-version.jar
	<repositories>
		<repository>
			<id>in-project</id>
			<name>project</name>
			<url>file://C:/Users/santoshm/rediff/ws/tmpws/quickweb/lib</url>
		</repository>
	</repositories>
	<dependencies>
		<dependency>
				<groupId>gid</groupId>
				<artifactId>aid</artifactId>
				<version>1.0</version>
		</dependency>
	</dependencies>





Import an existing Maven project into eclipse.
	Right Click in the Project Explorer->
	Import->
	Maven->Existing Maven Projects->
	Browse to the location of the maven project, till pom.xml
	you are done.
	
	

/****************************************************************************************************/
/*								QnA
/****************************************************************************************************/

The problem related with goal while running the pom.xml.
	No goals have been specified for this build. You must specify a valid lifecycle phase or a goal in the format <plugin-prefix>:<goal> or <plugin-group-id>:<plugin-artifact-id>[:<plugin-version>]:<goal>. Available lifecycle phases are: validate, initialize, generate-sources, process-sources, generate-resources, process-resources, compile, process-classes, generate-test-sources, process-test-sources, generate-test-resources, process-test-resources, test-compile, process-test-classes, test, prepare-package, package, pre-integration-test, integration-test, post-integration-test, verify, install, deploy, pre-site, site, post-site, site-deploy, pre-clean, clean, post-clean.
	Add below tags to the pom.xml
		<build>
			<defaultGoal>install</defaultGoal>
		</build>

		
How to change the sevlet-api version in the project facet?
	go to navigator package explorer.
	look for .settings folder
	open the below file.
		org.eclipse.wst.common.project.facet.core.xml
	make appropriate changes
	RightClick on the project->
	Maven->
	Update the Project.
	(While updating update the xsd of the web.xml)
	


Maven change the target location of archive. 
	First we need to decide we are building what archive jar or war or ...
	Include the respective plugin into the pom.xml file.
	For jar::
		<plugin>
			<artifactId>maven-jar-plugin</artifactId>
			<version>2.3.2</version>
			<configuration>
				 <outputDirectory>${project.basedir}/usr/local/standalone/dbstore</outputDirectory>
			</configuration>
		</plugin>
	Important is we need to add the 'outputDirectory' and specify the user defined location. 



Maven include another maven project as dependencies.
	Its simple.
	Its as simple as including other dependencies.
	The other maven project will be available by default into your local repository.
	So you can include it as dependencies which is locally available. 


Maven Runnable Jar. 
	Refrence::
		http://www.mkyong.com/maven/how-to-create-a-jar-file-with-maven/
		http://www.javavids.com/video/how-to-create-runnable-jar-file-with-maven.html

		
Multiple Maven Module Handling:
	Reference::
	Tomeeplus source code.
	http://codepioneer.wordpress.com/2012/12/12/create-maven-multi-module-roject-using-eclipse/ 
	
	Create a maven project without slecting any archetype.
	The project type must contains the packageing "pom".
	
	Now create another project.
	This time select project type is maven-module.
	Now select the parent project to which it will be uses as module.
	
	Note::
		pom.xml. of the parent project of type "pom"
		this contains all the modules under the modules tag.
		
		pom.xml of the individual module.
		it contains a reference of the parent project. 	
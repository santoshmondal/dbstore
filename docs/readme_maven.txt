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
	http://www.tutorialspoint.com/maven/maven_external_dependencies.htm (good one)
	
	create folder::lib/gid/aid/version/aid-version.jar
	<repositories>
		<repository>
			<id>in-project</id>
			<name>project</name>
			<url>file://C:/Users/santoshm/rediff/ws/tmpws/quickweb/lib</url>
		</repository>
		
		<repository>
			<id>in-project</id>
			<name>project</name>
			<url>file://${project.basedir}/lib</url>
		</repository>
	</repositories>
	<dependencies>
		<dependency>
				<groupId>gid</groupId>
				<artifactId>aid</artifactId>
				<version>1.0</version>
		</dependency>
	</dependencies>
	
	OOOOOORRRRR
	<dependency>
		<groupId>daas</groupId>
		<artifactId>daas</artifactId>
		<version>1.0</version>
		<scope>system</scope>
		<systemPath>${basedir}\lib\DatastoreAsService.jar</systemPath>
	</dependency>





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

		<!-- Runnable Jar plugin -->
		<plugin>
				<artifactId>maven-assembly-plugin</artifactId>
				<configuration>
					<outputDirectory>${project.basedir}/var/ETLApps/build</outputDirectory>
					<archive>
						<manifest>
							<mainClass>com.rediff.etl.util.MailSender</mainClass>
						</manifest>
					</archive>
					<descriptorRefs>
						<descriptorRef>jar-with-dependencies</descriptorRef>
					</descriptorRefs>
				</configuration>
				<executions>
					<execution>
						<phase>package</phase>
						<goals>
							<goal>single</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		
		
		
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
		
		
		
Maven create war and jar together?
Ans: http://communitygrids.blogspot.in/2007/11/maven-making-war-and-jar-at-same-time.html
	http://preilly.me/2013/08/06/maven-making-both-a-war-and-jar-at-the-same-time/

	Put the following::
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-jar-plugin</artifactId>
				<executions>
					<execution>
						<id>make-a-jar</id>
						<phase>compile</phase>
						<goals>
							<goal>jar</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>


NB::
	<plugin>
		<groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-install-plugin</artifactId>
		<executions>
			<execution>
				<phase>install</phase>
				<goals>
					<goal>install-file</goal>
				</goals>
				<configuration>
					<packaging>jar</packaging>
					<artifactId>${project.artifactId}</artifactId>
					<groupId>${project.groupId}</groupId>
					<version>${project.version}</version>
					<file>
						${project.build.directory}/${project.artifactId}-${project.version}.jar
					</file>
				</configuration>
			</execution>
		</executions>
	</plugin>
		

Maven and ANT task?
Ans: http://stackoverflow.com/questions/586202/best-practices-for-copying-files-with-maven
http://stackoverflow.com/questions/2623632/execute-ant-task-with-maven
Ref::
	<plugin>
		<artifactId>maven-antrun-plugin</artifactId>
		<version>1.7</version>
		<configuration>
			<tasks>
				<copy file="${basedir}/AppConstant.properties" tofile="${basedir}/var/ETLApps/build/AppConstant.properties" />
				<copy file="${basedir}/ApplicationLog4j.properties" tofile="${basedir}/var/ETLApps/build/ApplicationLog4j.properties" />
			</tasks>
		</configuration>
		<executions>
			<execution>
				<phase>install</phase>
				<goals>
					<goal>run</goal>
				</goals>
			</execution>
		</executions>
	</plugin>



Maven dependency in classpath problem?
Ref:http://stackoverflow.com/questions/7071470/maven-does-not-add-classpath-to-eclipse-project

	<classpathentry exported="true" kind="con" path="org.eclipse.m2e.MAVEN2_CLASSPATH_CONTAINER">
		<attributes>
			<attribute name="maven.pomderived" value="true"/>
			<attribute name="org.eclipse.jst.component.dependency" value="/WEB-INF/lib"/>
		</attributes>
	</classpathentry>
	
	
Java Maven Project appears in another project as directory not a jar
Ref::http://stackoverflow.com/questions/18257218/java-maven-project-appears-in-another-project-as-directory-not-a-jar




Maven--dependency managment understanding??
Ans:: <dependency>
			<groupId>daas-maven</groupId>
			<artifactId>daas-maven</artifactId>
			<version>0.0.3</version>
			<exclusions>
				<exclusion>
					<groupId>*</groupId>
					<artifactId>*</artifactId>
				</exclusion>
			</exclusions>
		</dependency>



Maven packaging without test (skip tests)??
Ref:: http://stackoverflow.com/questions/7456006/maven-packaging-without-test-skip-tests
Anss:: 
	<plugin>
	  <groupId>org.apache.maven.plugins</groupId>
	  <artifactId>maven-surefire-plugin</artifactId>
	  <configuration>
	    <skipTests>true</skipTests>
	  </configuration>
	</plugin>


Maven--Changing the project facet?
like java 5 to java 7.
servlet specification.
rest specification.
Ans:: pending.

	
	
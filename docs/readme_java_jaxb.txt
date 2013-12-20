/****************************************************************************************************/
/*								Basics of JDBC
/****************************************************************************************************/
	1. Marshaller
	2. UnMarshaller
	3. Validator
	4. Transformer

>>> Jaxb annotations?
	@XmlRootElement
	@XmlType(propOrder ={"", ""})
	
	@XmlValue(It can'nt be mix with element)
	
	@XmlElement
	@XmlElementWrapper
	
	@XmlAttribute
	@XmlTransient
	
	@XmlList
	@XmlAnyElement
	@XmlAnyAttribute


>>> Ignore Case for tags?
	http://blog.bdoughan.com/2010/12/case-insensitive-unmarshalling.html


>>> CDATA Support in xml generation and reading?
	<![CDATA[]]>
	
	How to put CDATA using jaxb?
		Step 1: Use Moxy cum Eclipselink libraries.
		Step 2: Dont forget to put the jaxb.properties file in model class path.
		javax.xml.bind.context.factory=org.eclipse.persistence.jaxb.JAXBContextFactory
		
	Ref: http://blog.bdoughan.com/2010/07/cdata-cdata-run-run-data-run.html


		
>>> Xml Namespace problem?
	http://blog.bdoughan.com/2010/08/jaxb-namespaces.html
	http://java.dzone.com/articles/jaxb-and-namespace-prefixes
	
	
	CASE-1::
		<root xmlns="http://www.test.com">
		   <a>A</a>
		   <b>BB</b>
		   <c>CCC</c>
		</root>
	To marshal/unmarshal such kind of xml add following package-info.java.
	package-info helps you to create clean prefiex namespace xml.
	
	@XmlSchema(namespace = "http://www.test.com", elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)
	package common.util.jaxb;
	import javax.xml.bind.annotation.XmlSchema;

			
	CASE-2::
		<ns0:root xmlns:ns0="http://www.test.com">
		   <a>A</a>
		   <b>BB</b>
		   <c>CCC</c>
		</ns0:root>
	If we are not going to put the package-info.java.
	And putting namespace in the root elment will produce the above xml.
	See the namespace prefix with the root element. 	
	
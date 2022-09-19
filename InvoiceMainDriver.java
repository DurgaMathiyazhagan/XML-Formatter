package ids.parser;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ids.parser.StaxParserHandler;

public class InvoiceMainDriver 
{	public static final String FILE_NAME ="sample.xml";
	
	public static void main(String[] args) throws FileNotFoundException, XMLStreamException, FactoryConfigurationError, TransformerException 
	{
//		System.out.println((new StaxParserHandler()).processXMLFile(new File(FILE_NAME)).toString());		
		System.out.println(xmlFormatter(4,(new StaxParserHandler()).processXMLFile(new File(FILE_NAME)).toString()));
//		System.out.println(xmlFormatter(counter, DB2_addressURI));
	}
	
	public static String xmlFormatter(int indentation, String rawXML) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		transformerFactory.setAttribute("indent-number", indentation);
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		StreamResult streamResult = new StreamResult(new StringWriter());
		transformer.transform(new StreamSource(new StringReader(rawXML)), streamResult);
		return rawXML;
	}
	
	
}
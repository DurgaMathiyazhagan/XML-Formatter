package ids.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaxParserHandler {
	public StringBuffer processXMLFile(File xmlFile) throws FileNotFoundException, XMLStreamException, FactoryConfigurationError {
		
		XMLEvent xmlEvent = null;
		Characters characters = null;
		StringBuffer rawXml = new StringBuffer();
		XMLEventReader xmlEventReader = XMLInputFactory.newInstance()
				.createXMLEventReader(new FileInputStream(xmlFile));
		while (xmlEventReader.hasNext()) {
			xmlEvent = xmlEventReader.nextEvent();
			switch (xmlEvent.getEventType()) {
			case XMLStreamConstants.START_ELEMENT:
				rawXml.append("<" + ((StartElement) xmlEvent).getName().getLocalPart() + ">");
				break;
			case XMLStreamConstants.CHARACTERS:
				characters = (Characters) xmlEvent;
				if (!(characters.isWhiteSpace()) || !(characters.isIgnorableWhiteSpace()))
					rawXml.append(characters.getData());
				break;
			case XMLStreamConstants.END_ELEMENT:
				rawXml.append("</" + ((EndElement) xmlEvent).getName().getLocalPart() + ">");
				break;
			}
		}		 
		
		/*
		 * StringBuffer rawXml = new StringBuffer(); XMLStreamReader xmlStreamReader =
		 * (XMLInputFactory.newInstance()).createXMLStreamReader(new
		 * FileInputStream(xmlFile)); while(xmlStreamReader.hasNext()) { switch
		 * (xmlStreamReader.next()) { case XMLStreamConstants.START_ELEMENT:
		 * rawXml.append("<"+xmlStreamReader.getLocalName()+">"); break; case
		 * XMLStreamConstants.CHARACTERS: if (!xmlStreamReader.isWhiteSpace())
		 * rawXml.append(xmlStreamReader.getText()); break;
		 * 
		 * case XMLStreamConstants.END_ELEMENT:
		 * rawXml.append("</"+xmlStreamReader.getLocalName()+">"); break; } }
		 */
		return rawXml;	
	}
}

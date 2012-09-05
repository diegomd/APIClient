package com.sambatech.apiclient.parser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sambatech.apiclient.exception.ParserException;

public class JAXBParser {

	@SuppressWarnings("unchecked")
	public static<T> T stringToObject(String xml, Class<T> clazz) throws ParserException {
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			InputStream is = new ByteArrayInputStream(xml.getBytes());
			
			T result = (T) jaxbUnmarshaller.unmarshal(is);
			
			return result;
		} catch (JAXBException e) {
			throw new ParserException("Could not parse string", e);
		} catch (Exception e) {
			throw new ParserException("Unexpected error parsing string", e);
		}
	}
	
	public static<T> String objectToString(T object, Class<T> clazz) throws ParserException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(object, baos);
			
			return baos.toString();			
		} catch (JAXBException e) {
			throw new ParserException("Could not parse object", e);
		} catch (Exception e) {
			throw new ParserException("Unexpected error parsing object", e);
		}
	}
	
}

package shared;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

public class Utils {
	public static String transformXML(String xmlString){

		StreamSource styler = new javax.xml.transform.stream.StreamSource("public/stylesheets/styler.xsl");
		String result = null;
		
		try {
			StringReader reader = new StringReader(xmlString);
		    StringWriter writer = new StringWriter();
		    TransformerFactory tFactory = TransformerFactory.newInstance();
		    Transformer transformer = tFactory.newTransformer(styler);
		    
		    //Set XML indent configs.
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		    transformer.transform(
		            new javax.xml.transform.stream.StreamSource(reader), 
		            new javax.xml.transform.stream.StreamResult(writer));

		    result = writer.toString();
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return result;
	}
	
	public static void main (String [] args) {
		System.out.println(transformXML("<teste><object>bla</object></teste>"));
	}
}

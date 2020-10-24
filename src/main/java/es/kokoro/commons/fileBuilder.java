package es.kokoro.commons;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class fileBuilder {
    public static Document nuevoXmlDoc() throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();    // Creamos instancia
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  // Iniciamos contructor
        Document doc = dBuilder.newDocument();
        return doc;
    }
    public static void buildXmlDoc(Document doc, String xmlFile) throws Exception {
        TransformerFactory transformaXmlInstance = TransformerFactory.newInstance();
        Transformer transformerXML = transformaXmlInstance.newTransformer();
        DOMSource fuenteDatos = new DOMSource(doc);
        StreamResult xmlFinal = new StreamResult(new File(xmlFile));
        transformerXML.transform(fuenteDatos, xmlFinal);
    }
}

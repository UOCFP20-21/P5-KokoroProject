package es.kokoro.commons;

import es.kokoro.dao.DAO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

public class fileXmlBuilder {
    public static void xheckXmlExists(String xmlFile, String elemento) throws Exception {
        File archivo = new File(xmlFile);
        if(!archivo.exists())
        {
            Document doc = nuevoXmlDoc();
            // definimos el elemento raíz del documento
            Element xmlRoot = doc.createElement(elemento);
            doc.appendChild(xmlRoot);

            buildXmlDoc(doc, xmlFile);
            System.out.println("Archivo creado");
        }
    }
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

    public static List subElementoIdList(Element eParent, DAO xmlBuilderDao, String eList, String eItem) throws Exception {

        List exitList = new ArrayList();
        Node nNodeList = eParent.getElementsByTagName(eList).item(0); // Declaramos NODO
        Element eNodeList = (Element) nNodeList;  // Convertimos nodo en Element

        NodeList nNodeListChild = eNodeList.getElementsByTagName(eItem);   //Sacamos listado de nodos internos


        for(int tempSubLinea = 0; tempSubLinea < nNodeListChild.getLength(); tempSubLinea++) {
            Node nNodeChild = nNodeListChild.item(tempSubLinea);

            if(nNodeChild.getNodeType() == Node.ELEMENT_NODE) // Comrpobamos si el nodo es == al tipo de nodo que hemos pedido
            {
                Element eNodeChild = (Element) nNodeChild;
                Long id = parseLong(eNodeChild.getTextContent());
                if(xmlBuilderDao.get(id) != null)
                {
                    exitList.add(xmlBuilderDao.get(id));
                }

            }
        }
        return exitList;
    }
}

package es.kokoro.dao.xml;

import es.kokoro.dao.LineaAccionDAO;
import es.kokoro.model.LineaAccion;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.fileXmlBuilder.*;
import static java.lang.Long.parseLong;

public class XmlLineaAccionDAO implements LineaAccionDAO {

    private final String xmlFile = "src/main/resources/xml/LineasAccion.xml";

    public XmlLineaAccionDAO() throws Exception {

        try {
            xheckXmlExists(xmlFile, "LineasAccion");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
            throw e;
        }

    }

    private Element crearElemento(LineaAccion itemLineaAccion, Document doc) throws Exception {

        // definimos el nodo que contendrá los elementos
        Element eLineaAccion = doc.createElement("LineaAccion");

        // atributo para el nodo
        Attr attr = doc.createAttribute("id");
        String saveId = Long.toString(itemLineaAccion.getIdLineaAccion());
        attr.setValue(saveId);
        eLineaAccion.setAttributeNode(attr);

        // definimos cada uno de los elementos y le asignamos un valor
        Element eCodigoProyecto = doc.createElement("linea");
        eCodigoProyecto.appendChild(doc.createTextNode(itemLineaAccion.getLinea()));
        eLineaAccion.appendChild(eCodigoProyecto);

        return eLineaAccion;

    }

    @Override
    public LineaAccion get(long id) throws Exception {
        List<LineaAccion> listado = getAll();
        for (LineaAccion itemLineaAccion: listado) {
            if(itemLineaAccion.getIdLineaAccion().equals(id)){ return itemLineaAccion; }
        }
        return null;
    }

    public List<LineaAccion> getAll() throws Exception {
        List<LineaAccion> listLineaAccion = new ArrayList<LineaAccion>();
        File proyectosDB = new File(xmlFile);
        Document doc;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();    // Creamos instancia
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  // Iniciamos contructor
            doc = dBuilder.parse(proyectosDB);


            NodeList listadoNodos = doc.getElementsByTagName("LineaAccion");

            for(int temp = 0; temp < listadoNodos.getLength(); temp++) {
                Node nNode = listadoNodos.item(temp);

                if(nNode.getNodeType() == Node.ELEMENT_NODE) // Comrpobamos si el nodo es == al tipo de nodo que hemos pedido
                {
                    Element eLineaAccion = (Element) nNode;

                    LineaAccion tmpLineaAccion = new LineaAccion(
                            parseLong(eLineaAccion.getAttribute("id")),
                            eLineaAccion.getElementsByTagName("linea").item(0).getTextContent()
                    );
                    listLineaAccion.add(tmpLineaAccion);
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return listLineaAccion;
    }


    @Override
    public void save(LineaAccion lineaAccion) throws Exception {

        Boolean isNew = true;
        List<LineaAccion> lineaAccionList = getAll();

        try {
            Document doc = nuevoXmlDoc();

            // definimos el elemento raíz del documento
            Element xmlRoot = doc.createElement("LineasAccion");
            doc.appendChild(xmlRoot);
            for (LineaAccion itemLineaAccion: lineaAccionList) {

                if(itemLineaAccion.getIdLineaAccion().equals(lineaAccion.getIdLineaAccion()) )
                {
                    itemLineaAccion = lineaAccion;
                    isNew = false;
                }
                xmlRoot.appendChild(crearElemento(itemLineaAccion, doc));

            }

            if (isNew) {
                xmlRoot.appendChild(crearElemento(lineaAccion, doc));
            }

            // clases necesarias finalizar la creación del archivo XML
            buildXmlDoc(doc, xmlFile);
        } catch(Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update(LineaAccion lineaAccion, String[] params) throws Exception {
        save(lineaAccion);
    }

    public void update(LineaAccion lineaAccion) throws Exception {
        save(lineaAccion);
    }

    @Override
    public void delete(LineaAccion lineaAccion) throws Exception {

        List<LineaAccion> lineaAccionList = getAll();

        try {
            Document doc = nuevoXmlDoc();

            // definimos el elemento raíz del documento
            Element xmlRoot = doc.createElement("LineasAccion");
            doc.appendChild(xmlRoot);

            for (LineaAccion itemLineaAccion: lineaAccionList) {

                if(itemLineaAccion.getIdLineaAccion() != lineaAccion.getIdLineaAccion()){
                    xmlRoot.appendChild(crearElemento(itemLineaAccion, doc));
                }

            }

            // clases necesarias finalizar la creación del archivo XML
            buildXmlDoc(doc, xmlFile);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

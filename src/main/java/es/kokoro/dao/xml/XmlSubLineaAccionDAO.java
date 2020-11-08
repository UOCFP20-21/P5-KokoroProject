package es.kokoro.dao.xml;

import es.kokoro.dao.SubLineaAccionDAO;
import es.kokoro.model.SubLineaAccion;
import org.w3c.dom.*;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static es.kokoro.commons.fileXmlBuilder.*;
import static java.lang.Long.parseLong;

public class XmlSubLineaAccionDAO implements SubLineaAccionDAO {
    private final String xmlFile = "src/main/resources/xml/SubLineasAccion.xml";

    public XmlSubLineaAccionDAO() throws Exception {

            checkXmlExists(xmlFile, "SubLineasAccion");
    }

    private Element crearElemento(SubLineaAccion itemSubLineaAccion, Document doc) throws Exception {

        // definimos el nodo que contendrá los elementos
        Element eSubLineaAccion = doc.createElement("SubLineaAccion");

        // atributo para el nodo
        Attr attr = doc.createAttribute("id");
        String saveId = Long.toString(itemSubLineaAccion.getIdSubLinea());
        attr.setValue(saveId);
        eSubLineaAccion.setAttributeNode(attr);

        // definimos cada uno de los elementos y le asignamos un valor
        Element eCodigoProyecto = doc.createElement("nombreSubLinea");
        eCodigoProyecto.appendChild(doc.createTextNode(itemSubLineaAccion.getNombreSubLinea()));
        eSubLineaAccion.appendChild(eCodigoProyecto);

        return eSubLineaAccion;

    }
    @Override
    public SubLineaAccion get(long id) throws Exception {

        List<SubLineaAccion> listado = getAll();
        for (SubLineaAccion itemSubLineaAccion: listado) {
            if(itemSubLineaAccion.getIdSubLinea().equals(id)){ return itemSubLineaAccion; }
        }
        return null;
    }

    @Override
    public List<SubLineaAccion> getAll() throws Exception {
        List<SubLineaAccion> listSubLineaAccion = Collections.emptyList();
        try {
            File archivo = new File(xmlFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();    // Creamos instancia
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  // Iniciamos contructor
            Document doc = dBuilder.parse(archivo);

            NodeList listadoNodos = doc.getElementsByTagName("SubLineaAccion");
            if(listadoNodos.getLength() > 0) {
                listSubLineaAccion = new ArrayList<SubLineaAccion>();
            }
            for(int temp = 0; temp < listadoNodos.getLength(); temp++) {
                Node nNode = listadoNodos.item(temp);

                if(nNode.getNodeType() == Node.ELEMENT_NODE) // Comrpobamos si el nodo es == al tipo de nodo que hemos pedido
                {
                    Element eSubLineaAccion = (Element) nNode;

                    SubLineaAccion tmpSubLineaAccion = new SubLineaAccion(
                            parseLong(eSubLineaAccion.getAttribute("id")),
                            eSubLineaAccion.getElementsByTagName("nombreSubLinea").item(0).getTextContent()
                    );
                    listSubLineaAccion.add(tmpSubLineaAccion);
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return listSubLineaAccion;
    }

    @Override
    public SubLineaAccion save(SubLineaAccion subLineaAccion) throws Exception {
        ;
        Boolean isNew = true;
        List<SubLineaAccion> subLineaAccionList = getAll();

        try {
            Document doc = nuevoXmlDoc();

            // definimos el elemento raíz del documento
            Element xmlRoot = doc.createElement("SubLineasAccion");
            doc.appendChild(xmlRoot);
            for (SubLineaAccion itemSubLineaAccion: subLineaAccionList) {

                if(itemSubLineaAccion.getIdSubLinea().equals(subLineaAccion.getIdSubLinea()) )
                {
                    itemSubLineaAccion = subLineaAccion;
                    isNew = false;
                }
                xmlRoot.appendChild(crearElemento(itemSubLineaAccion, doc));

            }

            if (isNew) {
                xmlRoot.appendChild(crearElemento(subLineaAccion, doc));
            }

            // clases necesarias finalizar la creación del archivo XML
            buildXmlDoc(doc, xmlFile);

        } catch(Exception e) {
            e.printStackTrace();
        }        return subLineaAccion;

    }

    @Override
    public SubLineaAccion update(SubLineaAccion subLineaAccion) throws Exception {
        save(subLineaAccion);
        return subLineaAccion;

    }

    @Override
    public void delete(SubLineaAccion subLineaAccion) throws Exception {

        List<SubLineaAccion> subLineaAccionList = getAll();

        try {
            Document doc = nuevoXmlDoc();

            // definimos el elemento raíz del documento
            Element xmlRoot = doc.createElement("SubLineasAccion");
            doc.appendChild(xmlRoot);

            for (SubLineaAccion itemSubLineaAccion: subLineaAccionList) {

                if(itemSubLineaAccion.getIdSubLinea() != subLineaAccion.getIdSubLinea()){
                    xmlRoot.appendChild(crearElemento(itemSubLineaAccion, doc));
                }

            }

            // clases necesarias finalizar la creación del archivo XML
            buildXmlDoc(doc, xmlFile);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

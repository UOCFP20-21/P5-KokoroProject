package es.kokoro.dao.xml;



import es.kokoro.dao.SocioDAO;
import es.kokoro.enums.Periodo;
import es.kokoro.model.*;
import org.w3c.dom.*;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.util.*;


import static es.kokoro.commons.fileXmlBuilder.*;
import static java.lang.Long.parseLong;

public class XmlSocioDAO implements SocioDAO {

    private final String xmlFile = "src/main/resources/xml/Socios.xml";

    public XmlSocioDAO() throws Exception {


            checkXmlExists(xmlFile, "Socios");

    }


    private Element crearElemento(Socio itemSocio, Document doc) throws Exception {

        // definimos el nodo que contendrá los elementos
        Element eSocio = doc.createElement("Socio");


        // atributo para el nodo socio
        Attr attr = doc.createAttribute("id");
        String saveIdSocio = Long.toString(itemSocio.getIdSocio());
        attr.setValue(saveIdSocio);
        eSocio.setAttributeNode(attr);

        // definimos cada uno de los elementos y le asignamos un valor
        Element eTipoPeriodo = doc.createElement("tipoPeriodo");
        eTipoPeriodo.appendChild(doc.createTextNode(itemSocio.getPeriodo().name()));
        eSocio.appendChild(eTipoPeriodo);

        Element eCuota = doc.createElement("cuota");
        String saveCuota = Double.toString(itemSocio.getCuota());
        eCuota.appendChild(doc.createTextNode(saveCuota));
        eSocio.appendChild(eCuota);

        Element eEstado = doc.createElement("estado");
        String saveEstado = "0";
        if (itemSocio.isEstado()) {
            saveEstado = "1";
        }
        eEstado.appendChild(doc.createTextNode(saveEstado));
        eSocio.appendChild(eEstado);

        Element eIdPersona = doc.createElement("idPersona");
        eIdPersona.appendChild(doc.createTextNode(Long.toString(itemSocio.getIdPersona())));
        eSocio.appendChild(eIdPersona);

        return eSocio;
    }

    @Override
    public Socio get(long id) throws Exception {
        List<Socio> listado = getAll();
        for (Socio socio : listado) {
            if (socio.getIdSocio().equals(id)) {
                return socio;
            }
        }
        return null;
    }

    public List<Socio> getAll() throws Exception {
        List<Socio> socioList = new ArrayList<>();
        File sociosDB = new File(xmlFile);
        Document doc;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();    // Creamos instancia
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  // Iniciamos contructor
            doc = dBuilder.parse(sociosDB);

            NodeList listadoNodos = doc.getElementsByTagName("Socio");

            for (int temp = 0; temp < listadoNodos.getLength(); temp++) {
                Node nNode = listadoNodos.item(temp); // Socio

                if (nNode.getNodeType() == Node.ELEMENT_NODE) // Comrpobamos si el nodo es == al tipo de nodo que hemos pedido
                {
                    Element eSocio = (Element) nNode;

                    /***
                     * Persona de prueba
                     */

                    Periodo tmpPeriodo = Periodo.valueOf(eSocio.getElementsByTagName("tipoPeriodo").item(0).getTextContent());
                    Socio tmpSocio = new Socio(
                            parseLong(eSocio.getElementsByTagName("idPersona").item(0).getTextContent()), "Sara", "Planas", "44455578A",
                            "Española", "calle", "Bcn", "65644846", "fajuh@sdfhaui.conm",
                            parseLong(eSocio.getAttribute("id")),
                            tmpPeriodo,
                            Double.parseDouble(eSocio.getElementsByTagName("cuota").item(0).getTextContent()),
                            Boolean.parseBoolean(eSocio.getElementsByTagName("estado").item(0).getTextContent())
                    );
                    socioList.add(tmpSocio);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return socioList;
    }


    @Override
    public void save(Socio socio) throws Exception {


        boolean isNew = true;
        List<Socio> socioList = getAll();

        try {
            Document doc = nuevoXmlDoc();

            // definimos el elemento raíz del documento
            Element xmlRoot = doc.createElement("Socios");
            doc.appendChild(xmlRoot);
            for (Socio itemSocio : socioList) {

                if (itemSocio.getIdSocio().equals(socio.getIdSocio())) {
                    itemSocio = socio;
                    isNew = false;
                }
                xmlRoot.appendChild(crearElemento(itemSocio, doc));

            }

            if (isNew) {
                xmlRoot.appendChild(crearElemento(socio, doc));
            }

            // clases necesarias finalizar la creación del archivo XML
            buildXmlDoc(doc, xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Socio socio, String[] params) throws Exception {
        save(socio);
    }

    public void update(Socio socio) throws Exception {
        save(socio);
    }

    @Override
    public void delete(Socio socio) throws Exception {

        List<Socio> socioList = getAll();

        try {
            Document doc = nuevoXmlDoc();

            // definimos el elemento raíz del documento
            Element xmlRoot = doc.createElement("Socios");
            doc.appendChild(xmlRoot);

            for (Socio itemSocio : socioList) {
                if (!itemSocio.getIdSocio().equals(socio.getIdSocio())) {
                    xmlRoot.appendChild(crearElemento(itemSocio, doc));
                }
            }

            // clases necesarias finalizar la creación del archivo XML
            buildXmlDoc(doc, xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

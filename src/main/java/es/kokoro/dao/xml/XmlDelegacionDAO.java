package es.kokoro.dao.xml;

import es.kokoro.dao.DelegacionDAO;
import es.kokoro.model.Delegacion;
import es.kokoro.model.*;
import org.w3c.dom.*;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import java.util.*;

import static es.kokoro.commons.fileXmlBuilder.*;
import static java.lang.Long.parseLong;

public class XmlDelegacionDAO implements DelegacionDAO {

    private final String xmlFile = "src/main/resources/xml/Delegacion.xml";

    public XmlDelegacionDAO() throws Exception {
        checkXmlExists(xmlFile, "Delegaciones");

    }
    public XmlDelegacionDAO(String cXmlFile) throws Exception {
        checkXmlExists(xmlFile, "Delegaciones");

    }
    private Element crearElemento(Delegacion itemDelegacion, Document doc) throws  Exception {

        //definimmos el nodo que contendrá los elementos
        Element eDelegacion = doc.createElement("Delegacion");

        //atributo para el nodo Delegación
        Attr attr = doc.createAttribute("id");
        String saveIdDelegacion = Long.toString(itemDelegacion.getIdDelegacion());
        attr.setValue(saveIdDelegacion);
        eDelegacion.setAttributeNode(attr);

        //definimos cada uno de los elementos y le asignamos un valor
        Element eTrabajdorList = doc.createElement("trabajadorList");
        if(!itemDelegacion.getTrabajadorList().isEmpty()) {
            for (Trabajador tmpTrabajador : itemDelegacion.getTrabajadorList()) {
                Element eTrabajador = doc.createElement("idTrabajador");
                eTrabajador.appendChild(doc.createTextNode(Long.toString(tmpTrabajador.getIdTrabajador())));
                eTrabajdorList.appendChild(eTrabajador);
            }
        }
        eDelegacion.appendChild(eTrabajdorList);

        Element eOng = doc.createElement("ong");
        String saveIdOng = Long.toString(itemDelegacion.getOng().getIdOng());
        eOng.appendChild(doc.createTextNode(saveIdOng));
        eDelegacion.appendChild(eOng);

        Element eAreaOperativa = doc.createElement("areaOperativa");
        eAreaOperativa.appendChild(doc.createTextNode(itemDelegacion.getAreaOperativa()));
        eDelegacion.appendChild(eAreaOperativa);


        Element eEmpresa = doc.createElement("idEmpresa");
        String sIdEmpresa = Long.toString(itemDelegacion.getIdEmpresa());

        eEmpresa.appendChild(doc.createTextNode(sIdEmpresa));
        eDelegacion.appendChild(eEmpresa);

        return eDelegacion;
    }

    public Delegacion get(long id) throws Exception{
        List<Delegacion> listado = getAll();
        for(Delegacion delegacion : listado){
            if (delegacion.getIdDelegacion().equals(id)){
                return delegacion;
            }
        }
        return null;
    }


    public List<Delegacion> getAll() throws  Exception {
        List<Delegacion> delegacionList = new ArrayList<>();
        File delegacionDB = new File(xmlFile);
        Document doc;

        try{DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();    // Creamos instancia
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  // Iniciamos contructor
            doc = dBuilder.parse(delegacionDB);

            NodeList listadoNodos = doc.getElementsByTagName("Delegacion");
            for (int temp = 0; temp < listadoNodos.getLength();temp++){
                Node nNode = listadoNodos.item(temp); //Delegacion

                if (nNode.getNodeType() == Node.ELEMENT_NODE) // Comrpobamos si el nodo es == al tipo de nodo que hemos pedido
                {
                    Element eDelegacion = (Element) nNode;
                    /***
                    Delegacion de prueba
                    ***/
                    List<Trabajador> trabajadorList = new ArrayList<>();
                    List<Delegacion> delegacionLista = new ArrayList<>();
                    List<Socio> socioList =  new ArrayList<>();
                    List<Ingreso> ingresoList = new ArrayList<>();

                    XmlProyectoDAO proyectoData = new XmlProyectoDAO();
                    List<Proyecto> proyectoList = proyectoData.getAll();
                   // XmlTrabajadorDAO trabajadorData = new XmlTrabajadorDAO();
                   // List<Trabajador> trabajadorList = subElementoIdList(eDelegacion, trabajadorData, "trabajadorList", "idTrabajador");

                     Ong ong = new Ong(
                            1L,
                            "Kokoro Sin Fronteras",
                            delegacionLista,
                            ingresoList,
                            socioList,
                            proyectoList


                    );
                    Long idDelegacion = parseLong(eDelegacion.getAttribute("id"));
                    Delegacion tmpDelegacion = new Delegacion(
                            parseLong(eDelegacion.getElementsByTagName("idEmpresa").item(0).getTextContent()),
                            "Kokora SA",
                            "España",
                            "Mataro",
                            "Av. Maresme, 32",
                            "Kokora SA",
                            "32569833",
                            "939998877",
                            "kokoro@kokoro.es",
                            idDelegacion,
                            trabajadorList,
                            ong,
                            eDelegacion.getElementsByTagName("areaOperativa").item(0).getTextContent()


                    );
                    delegacionList.add(tmpDelegacion);

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return delegacionList;
    }


    public void save(Delegacion delegacion) throws Exception {

        boolean isNew = true;
        List<Delegacion> delegacionList = getAll();

        try {
            Document doc = nuevoXmlDoc();

            //definimos el elemento raíz del documento
            Element xmlRoot = doc.createElement("Delegaciones");
            doc.appendChild(xmlRoot);
            for (Delegacion itemDelegacion : delegacionList){

                if (itemDelegacion.getIdDelegacion().equals(delegacion.getIdDelegacion())){
                    itemDelegacion = delegacion;
                    isNew = false;
                }
                xmlRoot.appendChild(crearElemento(itemDelegacion, doc));
            }

            if (isNew){
                xmlRoot.appendChild(crearElemento(delegacion, doc));
            }
            //clases necesario finalizar la creacion del archivo XML
            buildXmlDoc(doc, xmlFile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void update(Delegacion delegacion, String[] params) {

    }
     public void update(Delegacion delegacion) throws  Exception{
        save(delegacion);
     }


    public void delete(Delegacion delegacion) throws Exception {

        List<Delegacion> delegacionList = getAll();

        try{
            Document doc = nuevoXmlDoc();

            //definimos el elemento raiz del documento
            Element xmlRoot = doc.createElement("Delegaciones");
            doc.appendChild(xmlRoot);

            for (Delegacion itemDelegacion : delegacionList ){
                if (itemDelegacion.getIdDelegacion() != delegacion.getIdDelegacion()){
                    xmlRoot.appendChild(crearElemento(itemDelegacion, doc));
                }
            }
            // clases necesarias finalizar la creación del archivo XML
            buildXmlDoc(doc, xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

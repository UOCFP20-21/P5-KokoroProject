package es.kokoro.dao.xml;

import es.kokoro.dao.DAO;
import es.kokoro.dao.ProyectoDAO;
import es.kokoro.model.*;
import org.w3c.dom.*;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

import static es.kokoro.commons.FormatFecha.*;
import static es.kokoro.commons.FormatFecha.FFStringToDate;
import static es.kokoro.commons.fileXmlBuilder.*;
import static java.lang.Long.parseLong;


public class XmlProyectoDAO implements ProyectoDAO {

    private final String xmlFile = "src/main/resources/xml/Proyectos.xml";
    public XmlProyectoDAO() throws Exception {

        try {
            xheckXmlExists(xmlFile, "Proyectos");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
            throw e;
        }

    }

    private Element crearElemento(Proyecto itemProyecto, Document doc) throws Exception {

        // definimos el nodo que contendrá los elementos
        Element eProyecto = doc.createElement("Proyecto");


        // atributo para el nodo coche
        Attr attr = doc.createAttribute("id");
        String saveIdProyecto = Long.toString(itemProyecto.getIdProyecto());
        attr.setValue(saveIdProyecto);
        eProyecto.setAttributeNode(attr);

        // definimos cada uno de los elementos y le asignamos un valor
        Element eCodigoProyecto = doc.createElement("codigoProyecto");
        eCodigoProyecto.appendChild(doc.createTextNode(itemProyecto.getCodigoProyecto()));
        eProyecto.appendChild(eCodigoProyecto);

        Element eNombreProyecto = doc.createElement("nombreProyecto");
        eNombreProyecto.appendChild(doc.createTextNode(itemProyecto.getNombreProyecto()));
        eProyecto.appendChild(eNombreProyecto);

        Element ePais = doc.createElement("pais");
        ePais.appendChild(doc.createTextNode(itemProyecto.getPais()));
        eProyecto.appendChild(ePais);

        Element eLocalizacion = doc.createElement("localizacion");
        eLocalizacion.appendChild(doc.createTextNode(itemProyecto.getLocalizacion()));
        eProyecto.appendChild(eLocalizacion);

        //Listados serán hijos

        Element eSocioLocalList = doc.createElement("socioLocalList");
        if(!itemProyecto.getSocioLocalList().isEmpty()){

            for (SocioLocal tmpSocioLocal: itemProyecto.getSocioLocalList()) {
                Element eSocioLocal = doc.createElement("idSocioLocal");
                eSocioLocal.appendChild(doc.createTextNode(Long.toString(tmpSocioLocal.getIdSocioLocal())));
                eSocioLocalList.appendChild(eSocioLocal);
            }

        }
        eProyecto.appendChild(eSocioLocalList);

        Element eTrabajadorList = doc.createElement("trabajadorList");
        if(!itemProyecto.getTrabajadorList().isEmpty()) {
            for (Trabajador tmpTrabajador : itemProyecto.getTrabajadorList()) {
                Element eTrabajador = doc.createElement("idTrabajador");
                eTrabajador.appendChild(doc.createTextNode(Long.toString(tmpTrabajador.getIdTrabajador())));
                eTrabajadorList.appendChild(eTrabajador);
            }
        }
        eProyecto.appendChild(eTrabajadorList);

        Element eFinanciadorList = doc.createElement("financiadorList");
        if(!itemProyecto.getFinanciadorList().isEmpty()) {
            for (Financiador tmpFinanciador : itemProyecto.getFinanciadorList()) {
                Element eFinanciador = doc.createElement("idFinanciador");
                eFinanciador.appendChild(doc.createTextNode(Long.toString(tmpFinanciador.getIdFinanciador())));
                eFinanciadorList.appendChild(eFinanciador);
            }
        }
        eProyecto.appendChild(eFinanciadorList);

        Element eAccionList = doc.createElement("accionList");
        if(!itemProyecto.getAccionList().isEmpty()) {
            for (Accion tmpAccion : itemProyecto.getAccionList()) {
                Element eAccion = doc.createElement("idAccion");
                eAccion.appendChild(doc.createTextNode(Long.toString(tmpAccion.getIdAccion())));
                eAccionList.appendChild(eAccion);
            }
        }
        eProyecto.appendChild(eAccionList);

        // Fin listado con hijos
        Element eFechaInicio = doc.createElement("fechaInicio");
        eFechaInicio.appendChild(doc.createTextNode(FFDateToString(itemProyecto.getFechaInicio())));
        eProyecto.appendChild(eFechaInicio);

        Element eFechaFin = doc.createElement("fechaFin");
        eFechaFin.appendChild(doc.createTextNode(FFDateToString(itemProyecto.getFechaFin())));
        eProyecto.appendChild(eFechaFin);

        Element eLineaAccion = doc.createElement("lineaAccion");
        LineaAccion tmpLineaAccion = itemProyecto.getLineaAccion();
        eLineaAccion.appendChild(doc.createTextNode(Long.toString(tmpLineaAccion.getIdLineaAccion())));
        eProyecto.appendChild(eLineaAccion);


        Element eSubLineaAccionList = doc.createElement("subLineaAccionList");
        if(!itemProyecto.getSubLineaAccionList().isEmpty()) {
            for (SubLineaAccion tmpSubLineaAccion : itemProyecto.getSubLineaAccionList()) {
                Element eSubLineaAccion = doc.createElement("idSubLineaAccion");
                eSubLineaAccion.appendChild(doc.createTextNode(Long.toString(tmpSubLineaAccion.getIdSubLinea())));
                eSubLineaAccionList.appendChild(eSubLineaAccion);
            }
        }
        eProyecto.appendChild(eSubLineaAccionList);


        return eProyecto;

    }

    @Override
    public Proyecto get(long id) throws Exception {
        List<Proyecto> listado = getAll();
        for (Proyecto proyecto: listado) {
            if(proyecto.getIdProyecto().equals(id)){ return proyecto; }
        }
        return null;
    }

    public List<Proyecto> getAll() throws Exception {
        List<Proyecto> listadoProyectos = new ArrayList<>();
        File proyectosDB = new File(xmlFile);
        Document doc;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();    // Creamos instancia
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  // Iniciamos contructor
            doc = dBuilder.parse(proyectosDB);


            NodeList listadoNodos = doc.getElementsByTagName("Proyecto");
            /*if(listadoNodos.getLength() > 0) {
                listadoProyectos = new ArrayList<Proyecto>();
            }*/
            for(int temp = 0; temp < listadoNodos.getLength(); temp++) {
                Node nNode = listadoNodos.item(temp); // Proyecto

                if(nNode.getNodeType() == Node.ELEMENT_NODE) // Comrpobamos si el nodo es == al tipo de nodo que hemos pedido
                {
                    Element eProyecto = (Element) nNode;

                    List<SocioLocal> socioLocalList = new ArrayList<>();
                    XmlSocioLocalDAO socioLocalData = new XmlSocioLocalDAO();
                    /***
                     * Descomentar linea inferior si XmlSocioLocalDAO() Carga los datos
                     */
                    //socioLocalList = subElementoIdList(eProyecto, socioLocalData, "socioLocalList", "idSocioLocal");

                    List<Trabajador> trabajadorList = new ArrayList<Trabajador>();
                    XmlTrabajadorDAO trabajadorData = new XmlTrabajadorDAO();
                    /***
                     * Descomentar linea inferior si XmlTrabajadorDAO() Carga los datos
                     */
                    //trabajadorList = subElementoIdList(eProyecto, trabajadorData, "trabajadorList", "idTrabajador");

                    List<Financiador> financiadorList = new ArrayList<Financiador>();
                    XmlFinanciadorDAO financiadorData = new XmlFinanciadorDAO();
                    /***
                     * Descomentar linea inferior si XmlTrabajadorDAO() Carga los datos
                     */
                    //financiadorList = subElementoIdList(eProyecto, financiadorData, "financiadorList", "idFinanciador");

                    List<Accion> accionList = new ArrayList<>();
                    XmlAccionDAO accionData = new XmlAccionDAO();
                    /***
                     * Descomentar linea inferior si XmlAccionDAO() Carga los datos
                     */
                    //accionList = subElementoIdList(eProyecto, accionData, "accionList", "idAccion");

                    XmlLineaAccionDAO lineaAccionData = new XmlLineaAccionDAO();
                    LineaAccion lineaAccion = lineaAccionData.get(parseLong(eProyecto.getElementsByTagName("lineaAccion").item(0).getTextContent()));

                    /** SubLineaAccion **/
                    XmlSubLineaAccionDAO subLineaAccionData = new XmlSubLineaAccionDAO();
                    List<SubLineaAccion> subLineaAccionList = subElementoIdList(eProyecto, subLineaAccionData, "subLineaAccionList", "idSubLineaAccion");


                    /***
                     * Configuramos la fecha
                     */

                    Date eFechaInicio = FFStringToDate(eProyecto.getElementsByTagName("fechaInicio").item(0).getTextContent());
                    Date eFechaFin = FFStringToDate(eProyecto.getElementsByTagName("fechaFin").item(0).getTextContent());
                    /*** FIN Configuracion de fecha ***/

                    Proyecto tmpProyecto = new Proyecto(
                            parseLong(eProyecto.getAttribute("id")),
                            eProyecto.getElementsByTagName("codigoProyecto").item(0).getTextContent(),
                            eProyecto.getElementsByTagName("nombreProyecto").item(0).getTextContent(),
                            eProyecto.getElementsByTagName("pais").item(0).getTextContent(),
                            eProyecto.getElementsByTagName("localizacion").item(0).getTextContent(),
                            socioLocalList,
                            trabajadorList,
                            financiadorList,
                            accionList,
                            eFechaInicio,
                            eFechaFin,
                            lineaAccion,
                            subLineaAccionList
                    );
                    listadoProyectos.add(tmpProyecto);
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return listadoProyectos;
    }

    @Override
    public void save(Proyecto proyecto) throws Exception {

        /*File proyectosDB = new File(xmlFile);*/
        boolean isNew = true;
        List<Proyecto> proyectosList = getAll();

        try {
            Document doc = nuevoXmlDoc();

            // definimos el elemento raíz del documento
            Element xmlRoot = doc.createElement("Proyectos");
            doc.appendChild(xmlRoot);
            for (Proyecto itemProyecto: proyectosList) {

                if(itemProyecto.getIdProyecto().equals(proyecto.getIdProyecto()) )
                {
                    itemProyecto = proyecto;
                    isNew = false;
                }
                xmlRoot.appendChild(crearElemento(itemProyecto, doc));

            }

            if (isNew) {
                xmlRoot.appendChild(crearElemento(proyecto, doc));
            }

            // clases necesarias finalizar la creación del archivo XML
            buildXmlDoc(doc, xmlFile);
        } catch(Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update(Proyecto proyecto, String[] params) throws Exception {
        save(proyecto);
    }

    public void update(Proyecto proyecto) throws Exception {
        save(proyecto);
    }

    @Override
    public void delete(Proyecto proyecto) throws Exception {

        List<Proyecto> proyectosList = getAll();

        try {
            Document doc = nuevoXmlDoc();

            // definimos el elemento raíz del documento
            Element xmlRoot = doc.createElement("Proyectos");
            doc.appendChild(xmlRoot);

            for (Proyecto itemProyecto: proyectosList) {
                if(itemProyecto.getIdProyecto().equals(proyecto.getIdProyecto())){
                    xmlRoot.appendChild(crearElemento(itemProyecto, doc));
                }
            }

            // clases necesarias finalizar la creación del archivo XML
            buildXmlDoc(doc, xmlFile);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

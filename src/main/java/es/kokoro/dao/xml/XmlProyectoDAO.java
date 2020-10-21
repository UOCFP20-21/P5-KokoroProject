package es.kokoro.dao.xml;

import es.kokoro.dao.ProyectoDAO;
import es.kokoro.model.*;
import org.w3c.dom.*;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static es.kokoro.commons.FormatFecha.*;
import static es.kokoro.commons.FormatFecha.FFStringToDate;
import static java.lang.Long.parseLong;


public class XmlProyectoDAO implements ProyectoDAO {

    private final String xmlFile = "src/main/resources/xml/Proyectos.xml";


    @Override
    public Proyecto get(long id) {

        return null;
    }

    public List<Proyecto> getAll() throws Exception {
        List<Proyecto> listadoProyectos = Collections.emptyList();
        File proyectosDB = new File(xmlFile);
        Document doc;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();    // Creamos instancia
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  // Iniciamos contructor
            doc = dBuilder.parse(proyectosDB);


            NodeList listadoNodos = doc.getElementsByTagName("Proyecto");
            if(listadoNodos.getLength() > 0) {
                listadoProyectos = new ArrayList<Proyecto>();
            }
            for(int temp = 0; temp < listadoNodos.getLength(); temp++) {
                Node nNode = listadoNodos.item(temp);

                if(nNode.getNodeType() == Node.ELEMENT_NODE) // Comrpobamos si el nodo es == al tipo de nodo que hemos pedido
                {
                    Element eProyecto = (Element) nNode;
                    /***
                     * DESDE AQUÍ:
                     * Se tienen que modificar buscando por ID de objeto en su correspondiente XML-DAO
                     */
                    List<SocioLocal> socioLocalList = new ArrayList<SocioLocal>();
                    List<Trabajador> trabajadorList = new ArrayList<Trabajador>();
                    List<Financiador> financiadorList = new ArrayList<Financiador>();
                    List<Accion> accionList = new ArrayList<Accion>();
                    LineaAccion lineaAccion = new LineaAccion(1L, "Modificado para ejemplo");
                    List<SubLineaAccion> subLineaAccionList = new ArrayList<SubLineaAccion>();

                    /***
                     * HASTA AQUÍ
                     */
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
    public void save(Proyecto proyecto) {

        File proyectosDB = new File(xmlFile);
        Document doc;

        try {
            DocumentBuilderFactory dbXmlFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlDB = dbXmlFactory.newDocumentBuilder();
            doc = xmlDB.newDocument();

            // definimos el elemento raíz del documento
            Element xmlRoot = doc.createElement("Proyectos");
            doc.appendChild(xmlRoot);

            // definimos el nodo que contendrá los elementos
            Element eProyecto = doc.createElement("Proyecto");
            xmlRoot.appendChild(eProyecto);

            // atributo para el nodo coche
            Attr attr = doc.createAttribute("id");
            String saveIdProyecto = Long.toString(proyecto.getIdProyecto());
            attr.setValue(saveIdProyecto);
            eProyecto.setAttributeNode(attr);

            // definimos cada uno de los elementos y le asignamos un valor
            Element eCodigoProyecto = doc.createElement("codigoProyecto");
            eCodigoProyecto.appendChild(doc.createTextNode(proyecto.getCodigoProyecto()));
            eProyecto.appendChild(eCodigoProyecto);

            Element eNombreProyecto = doc.createElement("nombreProyecto");
            eNombreProyecto.appendChild(doc.createTextNode(proyecto.getNombreProyecto()));
            eProyecto.appendChild(eNombreProyecto);

            Element ePais = doc.createElement("pais");
            ePais.appendChild(doc.createTextNode(proyecto.getPais()));
            eProyecto.appendChild(ePais);

            Element eLocalizacion = doc.createElement("localizacion");
            eLocalizacion.appendChild(doc.createTextNode(proyecto.getLocalizacion()));
            eProyecto.appendChild(eLocalizacion);

            //Listados serán hijos

            Element eSocioLocalList = doc.createElement("socioLocalList");
            if(!proyecto.getSocioLocalList().isEmpty()){

                for (SocioLocal tmpSocioLocal: proyecto.getSocioLocalList()) {
                    Element eSocioLocal = doc.createElement("idSocioLocal");
                    eSocioLocal.appendChild(doc.createTextNode(Long.toString(tmpSocioLocal.getIdSocioLocal())));
                    eSocioLocalList.appendChild(eSocioLocal);
                }

            }
            eProyecto.appendChild(eSocioLocalList);

            Element eTrabajadorList = doc.createElement("trabajadorList");
            if(!proyecto.getTrabajadorList().isEmpty()) {
                for (Trabajador tmpTrabajador : proyecto.getTrabajadorList()) {
                    Element eTrabajador = doc.createElement("idTrabajador");
                    eTrabajador.appendChild(doc.createTextNode(Long.toString(tmpTrabajador.getIdTrabajador())));
                    eTrabajadorList.appendChild(eTrabajador);
                }
            }
            eProyecto.appendChild(eTrabajadorList);

            Element eFinanciadorList = doc.createElement("financiadorList");
            if(!proyecto.getFinanciadorList().isEmpty()) {
                for (Financiador tmpFinanciador : proyecto.getFinanciadorList()) {
                    Element eFinanciador = doc.createElement("idFinanciador");
                    eFinanciador.appendChild(doc.createTextNode(Long.toString(tmpFinanciador.getIdFinanciador())));
                    eFinanciadorList.appendChild(eFinanciador);
                }
            }
            eProyecto.appendChild(eFinanciadorList);

            Element eAccionList = doc.createElement("accionList");
            if(!proyecto.getAccionList().isEmpty()) {
                for (Accion tmpAccion : proyecto.getAccionList()) {
                    Element eAccion = doc.createElement("idAccion");
                    eAccion.appendChild(doc.createTextNode(Long.toString(tmpAccion.getIdAccion())));
                    eAccionList.appendChild(eAccion);
                }
            }
            eProyecto.appendChild(eAccionList);

            // Fin listado con hijos
            Element eFechaInicio = doc.createElement("fechaInicio");
            eFechaInicio.appendChild(doc.createTextNode(FFDateToString(proyecto.getFechaInicio())));
            eProyecto.appendChild(eFechaInicio);

            Element eFechaFin = doc.createElement("fechaFin");
            eFechaFin.appendChild(doc.createTextNode(FFDateToString(proyecto.getFechaFin())));
            eProyecto.appendChild(eFechaFin);

            Element eLineaAccion = doc.createElement("lineaAccion");
            LineaAccion tmpLineaAccion = proyecto.getLineaAccion();
            eLineaAccion.appendChild(doc.createTextNode(Long.toString(tmpLineaAccion.getIdLineaAccion())));
            eProyecto.appendChild(eLineaAccion);


            Element eSubLineaAccionList = doc.createElement("subLineaAccionList");
            if(!proyecto.getSubLineaAccionList().isEmpty()) {
                for (SubLineaAccion tmpSubLineaAccion : proyecto.getSubLineaAccionList()) {
                    Element eSubLineaAccion = doc.createElement("idSubLineaAccion");
                    eSubLineaAccion.appendChild(doc.createTextNode(Long.toString(tmpSubLineaAccion.getIdSubLinea())));
                    eSubLineaAccionList.appendChild(eSubLineaAccion);
                }
            }
            eProyecto.appendChild(eSubLineaAccionList);

            // clases necesarias finalizar la creación del archivo XML
            TransformerFactory transformaXmlInstance = TransformerFactory.newInstance();
            Transformer transformerXML = transformaXmlInstance.newTransformer();
            DOMSource fuenteDatos = new DOMSource(doc);
            StreamResult xmlFinal = new StreamResult(new File(xmlFile));

            transformerXML.transform(fuenteDatos, xmlFinal);
        } catch(Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update(Proyecto proyecto, String[] params) {

    }

    @Override
    public void delete(Proyecto proyecto) {

    }
}

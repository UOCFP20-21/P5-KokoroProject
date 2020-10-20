package es.kokoro.dao.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import es.kokoro.dao.SocioDAO;
import es.kokoro.model.Socio;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class XmlSocioDAO implements SocioDAO {

    XmlMapper mapper;

    public XmlSocioDAO() {
        mapper = new XmlMapper();
    }

    public Socio get(long id) {
        try {
            return mapper.readValue(new File("src/main/resources/xml/socio_" + id + ".xml"), Socio.class);
        } catch (IOException e) {
            System.out.println("Error devolviendo socio_" + id + ".xml");
            return null;
        }
    }

    @Override
    public List<Socio> getAll() {
        return null;
    }

    public void save(Socio socio) {
        try {
            mapper.writeValue(new File("src/main/resources/xml/socio" + "_" + socio.getIdSocio() + ".xml"), socio);
        } catch (IOException e) {
            System.out.println("Error guardando socio_" + socio.getIdentificador() + ".xml");
        }
    }

    @Override
    public void update(Socio socio, String[] params) {

    }

    public void delete(Socio socio) {

    }

    @Override
    public Socio buscarDNI(String dni) {
        return null;
    }
}

package es.kokoro.dao.xml;

import es.kokoro.dao.SociedadDAO;
import es.kokoro.model.Sociedad;

import java.util.List;

public class XmlSociedadDAO implements SociedadDAO {
    @Override
    public Sociedad get(long id) {
        return null;
    }

    @Override
    public List<Sociedad> getAll() {
        return null;
    }

    @Override
    public Sociedad save(Sociedad sociedad) {
        return sociedad;

    }

    @Override
    public Sociedad update(Sociedad sociedad) {
        return sociedad;

    }

    @Override
    public void delete(Sociedad sociedad) {

    }
}

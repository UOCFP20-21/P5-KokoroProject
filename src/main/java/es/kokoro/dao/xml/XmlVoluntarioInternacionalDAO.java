package es.kokoro.dao.xml;

import es.kokoro.dao.VoluntarioInternacionalDAO;
import es.kokoro.model.VoluntarioInternacional;

import java.util.List;

public class XmlVoluntarioInternacionalDAO implements VoluntarioInternacionalDAO {
    @Override
    public VoluntarioInternacional get(long id) {
        return null;
    }

    @Override
    public List<VoluntarioInternacional> getAll() {
        return null;
    }

    @Override
    public VoluntarioInternacional save(VoluntarioInternacional voluntarioInternacional) {
        return voluntarioInternacional;

    }

    @Override
    public VoluntarioInternacional update(VoluntarioInternacional voluntarioInternacional) {
        return voluntarioInternacional;

    }

    @Override
    public void delete(VoluntarioInternacional voluntarioInternacional) {

    }
}
package es.kokoro.dao.xml;

import es.kokoro.dao.SedeDAO;
import es.kokoro.model.Sede;


import java.util.List;

public class XmlSedeDAO implements SedeDAO {
    @Override
    public Sede get(long id) {
        return null;
    }

    @Override
    public List<Sede> getAll() {
        return null;
    }

    @Override
    public Sede save(Sede sede) {
        return sede;

    }

    @Override
    public Sede update(Sede sede) {
        return sede;

    }

    @Override
    public void delete(Sede sede) {

    }
}

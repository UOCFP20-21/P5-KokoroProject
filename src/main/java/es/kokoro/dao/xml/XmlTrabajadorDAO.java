package es.kokoro.dao.xml;

import es.kokoro.dao.TrabajadorDAO;
import es.kokoro.model.Trabajador;

import java.util.List;

public class XmlTrabajadorDAO implements TrabajadorDAO {
    @Override
    public Trabajador get(long id) {
        return null;
    }

    @Override
    public List<Trabajador> getAll() {
        return null;
    }

    @Override
    public Trabajador save(Trabajador trabajador) {
        return trabajador;

    }

    @Override
    public Trabajador update(Trabajador trabajador) {
        return trabajador;

    }

    @Override
    public void delete(Trabajador trabajador) {

    }
}

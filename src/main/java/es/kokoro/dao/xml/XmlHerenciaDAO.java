package es.kokoro.dao.xml;

import es.kokoro.dao.HerenciaDAO;
import es.kokoro.model.Herencia;

import java.util.List;

public class XmlHerenciaDAO implements HerenciaDAO {
    @Override
    public Herencia get(long id) {
        return null;
    }

    @Override
    public List<Herencia> getAll() {
        return null;
    }

    @Override
    public Herencia save(Herencia herencia) {
        return herencia;
    }

    @Override
    public Herencia update(Herencia herencia) {
        return herencia;
    }

    @Override
    public void delete(Herencia herencia) {

    }
}

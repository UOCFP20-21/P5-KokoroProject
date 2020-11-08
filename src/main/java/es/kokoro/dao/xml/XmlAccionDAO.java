package es.kokoro.dao.xml;

import es.kokoro.dao.AccionDAO;
import es.kokoro.model.Accion;

import java.util.List;

public class XmlAccionDAO implements AccionDAO {
    @Override
    public Accion get(long id) {
        return null;
    }

    @Override
    public List<Accion> getAll() {
        return null;
    }

    @Override
    public Accion save(Accion accion) {
return accion;
    }

    @Override
    public Accion update(Accion accion) {
return accion;
    }

    @Override
    public void delete(Accion accion) {

    }
}

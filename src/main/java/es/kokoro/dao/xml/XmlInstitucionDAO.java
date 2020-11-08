package es.kokoro.dao.xml;

import es.kokoro.dao.InstitucionDAO;
import es.kokoro.model.Ingreso;
import es.kokoro.model.Institucion;

import java.util.List;

public class XmlInstitucionDAO implements InstitucionDAO {
    @Override
    public Institucion get(long id) {
        return null;
    }

    @Override
    public List<Institucion> getAll() {
        return null;
    }

    @Override
    public Institucion save(Institucion institucion) {
        return institucion;
    }

    @Override
    public Institucion update(Institucion institucion) {
        return institucion;
    }

    @Override
    public void delete(Institucion institucion) {

    }
}

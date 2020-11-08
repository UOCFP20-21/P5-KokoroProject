package es.kokoro.dao.xml;

import es.kokoro.dao.IngresoDAO;
import es.kokoro.model.Ingreso;

import java.util.List;

public class XmlIngresoDAO implements IngresoDAO {
    @Override
    public Ingreso get(long id) {
        return null;
    }

    @Override
    public List<Ingreso> getAll() {
        return null;
    }

    @Override
    public Ingreso save(Ingreso ingreso) {
        return ingreso;
    }

    @Override
    public Ingreso update(Ingreso ingreso) throws Exception {
        return ingreso;
    }

    @Override
    public void delete(Ingreso ingreso) {

    }
}

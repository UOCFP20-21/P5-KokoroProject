package es.kokoro.dao.xml;

import es.kokoro.dao.VoluntarioColaboradorDAO;
import es.kokoro.model.VoluntarioColaborador;

import java.util.List;

public class XmlVoluntarioColaboradorDAO implements VoluntarioColaboradorDAO {
    @Override
    public VoluntarioColaborador get(long id) {
        return null;
    }

    @Override
    public List<VoluntarioColaborador> getAll() {
        return null;
    }

    @Override
    public VoluntarioColaborador save(VoluntarioColaborador voluntarioColaborador) {
        return voluntarioColaborador;

    }

    @Override
    public VoluntarioColaborador update(VoluntarioColaborador voluntarioColaborador) {
        return voluntarioColaborador;

    }

    @Override
    public void delete(VoluntarioColaborador voluntarioColaborador) {

    }
}

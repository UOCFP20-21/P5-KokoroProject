package es.kokoro.dao.xml;

import es.kokoro.dao.ContratadoDAO;
import es.kokoro.model.Contratado;

import java.util.List;

public class XmlContratadoDAO implements ContratadoDAO {
    @Override
    public Contratado get(long id) {
        return null;
    }

    @Override
    public List<Contratado> getAll() {
        return null;
    }

    @Override
    public Contratado save(Contratado contratado) {
        return contratado;
    }

    @Override
    public Contratado update(Contratado contratado) {
        return contratado;
    }

    @Override
    public void delete(Contratado contratado) {

    }
}

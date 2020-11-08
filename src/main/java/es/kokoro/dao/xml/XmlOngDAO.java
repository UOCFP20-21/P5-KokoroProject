package es.kokoro.dao.xml;

import es.kokoro.dao.OngDAO;
import es.kokoro.model.Ong;

import java.util.List;

public class XmlOngDAO implements OngDAO {
    @Override
    public Ong get(long id) {
        return null;
    }

    @Override
    public List<Ong> getAll() {
        return null;
    }

    @Override
    public Ong save(Ong ong) {
        return ong;
    }

    @Override
    public Ong update(Ong ong) {

        return ong;

    }

    @Override
    public void delete(Ong ong) {

    }
}

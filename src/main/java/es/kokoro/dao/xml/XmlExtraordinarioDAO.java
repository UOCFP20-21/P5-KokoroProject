package es.kokoro.dao.xml;

import es.kokoro.dao.ExtraordinarioDAO;
import es.kokoro.model.Extraordinario;

import java.util.List;

public class XmlExtraordinarioDAO implements ExtraordinarioDAO {
    @Override
    public Extraordinario get(long id) {
        return null;
    }

    @Override
    public List<Extraordinario> getAll() {
        return null;
    }

    @Override
    public Extraordinario save(Extraordinario extraordinario) {
        return extraordinario;
    }

    @Override
    public Extraordinario update(Extraordinario extraordinario) {
        return extraordinario;
    }

    @Override
    public void delete(Extraordinario extraordinario) {

    }
}

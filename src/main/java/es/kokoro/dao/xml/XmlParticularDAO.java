package es.kokoro.dao.xml;

import es.kokoro.dao.ParticularDAO;
import es.kokoro.model.Particular;

import java.util.List;

public class XmlParticularDAO implements ParticularDAO {
    @Override
    public Particular get(long id) {
        return null;
    }

    @Override
    public List<Particular> getAll() {
        return null;
    }

    @Override
    public Particular save(Particular particular) {
        return particular;

    }

    @Override
    public Particular update(Particular particular) {
        return particular;

    }

    @Override
    public void delete(Particular particular) {

    }
}

package es.kokoro.dao.xml;

import es.kokoro.dao.EstatalDAO;
import es.kokoro.model.Estatal;

import java.util.List;

public class XmlEstatalDAO implements EstatalDAO {
    @Override
    public Estatal get(long id) {
        return null;
    }

    @Override
    public List<Estatal> getAll() {
        return null;
    }

    @Override
    public Estatal save(Estatal estatal) {
        return estatal;
    }

    @Override
    public Estatal update(Estatal estatal) {
        return estatal;
    }

    @Override
    public void delete(Estatal estatal) {

    }
}

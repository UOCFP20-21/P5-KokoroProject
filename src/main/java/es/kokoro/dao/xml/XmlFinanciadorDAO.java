package es.kokoro.dao.xml;

import es.kokoro.dao.FinanciadorDAO;
import es.kokoro.model.Financiador;

import java.util.List;

public class XmlFinanciadorDAO implements FinanciadorDAO {
    @Override
    public Financiador get(long id) {
        return null;
    }

    @Override
    public List<Financiador> getAll() {
        return null;
    }

    @Override
    public Financiador save(Financiador financiador) {
        return financiador;
    }

    @Override
    public Financiador update(Financiador financiador) {
        return financiador;
    }

    @Override
    public void delete(Financiador financiador) {

    }
}

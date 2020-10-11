package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Financiador;

import java.util.List;
import java.util.Optional;

public class DAOfinanciador<pFinanciador> implements DAO<Financiador, pFinanciador>
{
    @Override
    public Optional<Financiador> devuelve(pFinanciador pFinanciador) {
        return Optional.empty();
    }

    @Override
    public List<Financiador> devuelveTodos() {
        return null;
    }

    @Override
    public Long crear(Financiador financiador) {
        return null;
    }

    @Override
    public void actualiza(Financiador financiador) {

    }
}

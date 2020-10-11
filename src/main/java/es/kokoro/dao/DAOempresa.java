package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.Empresa;

import java.util.List;
import java.util.Optional;

public class DAOempresa<pEmpresa> implements DAO<Empresa, pEmpresa> {
    @Override
    public Optional<Empresa> devuelve(pEmpresa pEmpresa) {
        return Optional.empty();
    }

    @Override
    public List<Empresa> devuelveTodos() {
        return null;
    }

    @Override
    public Long crear(Empresa empresa) {
        return null;
    }

    @Override
    public void actualiza(Empresa empresa) {

    }
}

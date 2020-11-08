package es.kokoro.dao.xml;

import es.kokoro.dao.EmpresaDAO;
import es.kokoro.model.Empresa;

import java.util.List;

public class XmlEmpresaDAO implements EmpresaDAO {
    @Override
    public Empresa get(long id) {
        return null;
    }

    @Override
    public List<Empresa> getAll() {
        return null;
    }

    @Override
    public Empresa save(Empresa empresa) {
        return empresa;
    }

    @Override
    public Empresa update(Empresa empresa) {
        return empresa;
    }

    @Override
    public void delete(Empresa empresa) {

    }
}

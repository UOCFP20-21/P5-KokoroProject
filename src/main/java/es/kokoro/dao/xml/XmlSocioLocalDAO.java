package es.kokoro.dao.xml;

import es.kokoro.dao.SocioLocalDAO;
import es.kokoro.model.SocioLocal;


import java.util.List;

public class XmlSocioLocalDAO implements SocioLocalDAO {
    @Override
    public SocioLocal get(long id) {
        return null;
    }

    @Override
    public List<SocioLocal> getAll() {
        return null;
    }

    @Override
    public SocioLocal save(SocioLocal socioLocal) {
        return socioLocal;

    }

    @Override
    public SocioLocal update(SocioLocal socioLocal) {
        return socioLocal;

    }

    @Override
    public void delete(SocioLocal socioLocal) {

    }
}

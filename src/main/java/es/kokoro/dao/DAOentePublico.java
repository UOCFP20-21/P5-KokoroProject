package es.kokoro.dao;

import es.kokoro.dao.interfaces.DAO;
import es.kokoro.model.EntePublico;

import java.util.List;
import java.util.Optional;

public class DAOentePublico<pEntePublico> implements DAO<EntePublico, pEntePublico> {
    @Override
    public Optional<EntePublico> devuelve(pEntePublico pEntePublico) {
        return Optional.empty();
    }

    @Override
    public List<EntePublico> devuelveTodos() {
        return null;
    }

    @Override
    public Long crea(EntePublico entePublico) {
        return null;
    }

    @Override
    public void actualiza(EntePublico entePublico, String[] params) {

    }

    @Override
    public void borra(EntePublico entePublico) {

    }
}

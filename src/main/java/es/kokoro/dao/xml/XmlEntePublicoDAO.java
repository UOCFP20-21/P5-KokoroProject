package es.kokoro.dao.xml;

import es.kokoro.dao.EntePublicoDAO;
import es.kokoro.model.EntePublico;

import java.util.List;

public class XmlEntePublicoDAO implements EntePublicoDAO {
    @Override
    public EntePublico get(long id) {
        return null;
    }

    @Override
    public List<EntePublico> getAll() {
        return null;
    }

    @Override
    public EntePublico save(EntePublico entePublico) {
        return entePublico;
    }

    @Override
    public EntePublico update(EntePublico entePublico) {
        return entePublico;
    }

    @Override
    public void delete(EntePublico entePublico) {

    }
}

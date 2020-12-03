package es.kokoro.dao.mysql;

import es.kokoro.dao.ParticularDAO;
import es.kokoro.model.Particular;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static es.kokoro.commons.sqlConection.commitData;

public class MySQLParticularDAO implements ParticularDAO {

    public MySQLParticularDAO() {
        super();
    }

    private Particular setObject(ResultSet set)
    {
       return null;

    }


    @Override
    public Particular get(long id) throws Exception {
        return null;
    }

    @Override
    public List<Particular> getAll() throws Exception {
        return null;
    }

    @Override
    public void save(Particular particular) throws Exception {

    }

    @Override
    public void update(Particular particular) throws Exception {

    }

    @Override
    public void delete(Particular particular) throws Exception {

    }
}

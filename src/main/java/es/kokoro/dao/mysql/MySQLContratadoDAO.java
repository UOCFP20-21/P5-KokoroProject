package es.kokoro.dao.mysql;

import es.kokoro.model.Contratado;
import es.kokoro.model.Trabajador;

import java.util.List;

public class MySQLContratadoDAO extends MySQLTrabajadorDAO {


    public MySQLContratadoDAO() {
        super();
    }


    @Override
    public Contratado get(long id) {
        return null;
    }

    @Override
    public List<Trabajador> getAll() {
        return null;
    }

    @Override
    public void save(Trabajador trabajador) {

    }

    @Override
    public void update(Trabajador trabajador) {

    }

    @Override
    public void delete(Trabajador trabajador) {


    }
}

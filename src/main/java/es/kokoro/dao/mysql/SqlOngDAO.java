package es.kokoro.dao.mysql;

import es.kokoro.dao.OngDAO;
import es.kokoro.model.Ong;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SqlOngDAO extends MySqlFactoryDAO implements OngDAO {
    @Override
    public Ong get(long id) throws Exception {
        return null;
    }

    @Override
    public List<Ong> getAll() throws Exception {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from ongs");

        List<Ong> ongList = new ArrayList<>();

        while (resultSet.next()) {
            Long id = resultSet.getLong("idOngs");
            String nombre = resultSet.getString("nombre");

            Ong ong = new Ong(id, nombre);
            ongList.add(ong);
        }

        return ongList;
    }

    @Override
    public void save(Ong ong) throws Exception {

    }

    @Override
    public void update(Ong ong, String[] params) throws Exception {

    }

    @Override
    public void delete(Ong ong) throws Exception {

    }


}

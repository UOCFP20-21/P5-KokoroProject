package es.kokoro.dao;

import es.kokoro.model.Socio;

public interface SocioDAO extends DAO<Socio> {
    Socio buscarDNI(String dni);
}

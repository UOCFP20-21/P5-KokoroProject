package es.kokoro.dao;

import es.kokoro.model.Persona;

public interface PersonaDAO extends DAO<Persona> {
    Persona buscarDNI(String dni);
}
package es.kokoro.dao;

import es.kokoro.model.*;

public class FactoryDao {
    public static Dao getFactoryDao(Class classInstance) throws Exception {
        if (classInstance.equals(Socio.class)) {
            return new SocioDao();
        } else if (classInstance.equals(Contratado.class)) {
            return new ContratadoDao();
        } else if (classInstance.equals(VoluntarioColaborador.class)) {
            return new VoluntarioColaboradorDao();
        } else if (classInstance.equals(VoluntarioInternacional.class)) {
            return new VoluntarioInternacionalDao();
        } else if (classInstance.equals(Sede.class)) {
            return new SedeDao();
        } else if (classInstance.equals(Trabajador.class)) {
            return new TrabajadorDao();
        } else if (classInstance.equals(Persona.class)) {
            return new PersonaDao();
        } else if (classInstance.equals(Delegacion.class)) {
            return new DelegacionDao();
        } else if (classInstance.equals(Empresa.class)) {
            return new EmpresaDao();
        } else if (classInstance.equals(Particular.class)) {
            return new ParticularDao();
        } else if (classInstance.equals(Herencia.class)) {
            return new HerenciaDao();
        } else if (classInstance.equals(Sociedad.class)) {
            return new SociedadDao();
        } else if (classInstance.equals(Institucion.class)) {
            return new InstitucionDao();
        } else if (classInstance.equals(Extraordinario.class)) {
            return new ExtraordinarioDao();
        } else if (classInstance.equals(Ong.class)) {
            return new OngDao();
        } else if (classInstance.equals(SocioLocal.class)) {
            return new SocioLocalDao();
        } else if (classInstance.equals(Financiador.class)) {
            return new FinanciadorDao();
        } else if (classInstance.equals(Proyecto.class)) {
            return new ProyectoDao();
        } else if (classInstance.equals(Ingreso.class)) {
            return new IngresoDao();
        } else if (classInstance.equals(Estatal.class)) {
            return new EstatalDao();
        } else if (classInstance.equals(Internacional.class)) {
            return new InternacionalDao();
        } else if (classInstance.equals(EntePublico.class)) {
            return new EntePublicoDao();
        } else if (classInstance.equals(Accion.class)) {
            return new AccionDao();
        } else if (classInstance.equals(LineaAccion.class)) {
            return new LineaAccionDao();
        } else if (classInstance.equals(SubLineaAccion.class)) {
            return new SubLineaAccionDao();
        }
        throw new Exception("Clase desconocida");

    }
}



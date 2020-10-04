package es.kokoro.model;

import java.util.Date;

public class Contratados extends Trabajador{
    private Date inicioContract;
    private Date finalContract;
    private float salario;
    private String puestoTrabajo;

    public Contratados(Long idPersona, String nombre, String apellidos, String identificador, String nacionalidad, String direccion, String poblacion, String telefono, String email, Long idTrabajador, Delegacion delegacion, Date fechaNac, boolean activo, Date inicioContract, Date finalContract, float salario, String puestoTrabajo) {
        super(idPersona, nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email, idTrabajador, delegacion, fechaNac, activo);
        this.inicioContract = inicioContract;
        this.finalContract = finalContract;
        this.salario = salario;
        this.puestoTrabajo = puestoTrabajo;
    }

    public Date getInicioContract() {
        return inicioContract;
    }

    public void setInicioContract(Date inicioContract) {
        this.inicioContract = inicioContract;
    }

    public Date getFinalContract() {
        return finalContract;
    }

    public void setFinalContract(Date finalContract) {
        this.finalContract = finalContract;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public void setPuestoTrabajo(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    @Override
    public String toString() {
        return "Contratados{" +
                "inicioContract=" + inicioContract +
                ", finalContract=" + finalContract +
                ", salario=" + salario +
                ", puestoTrabajo='" + puestoTrabajo + '\'' +
                "} " + super.toString();
    }
}

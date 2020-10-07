package es.kokoro.model;

import java.util.Date;

public class Contratado extends Trabajador{
    private Long idContratado;
    private Date inicioContract;
    private String finalContract;
    private float salario;
    private String puestoTrabajo;

    /***
     *
     * @param idPersona Herencia de Persona
     * @param nombre Herencia de Persona
     * @param apellidos Herencia de Persona
     * @param identificador Herencia de Persona
     * @param nacionalidad Herencia de Persona
     * @param direccion Herencia de Persona
     * @param poblacion Herencia de Persona
     * @param telefono Herencia de Persona
     * @param email Herencia de Persona
     * @param idTrabajador Herencia de Trabajador
     * @param delegacion Herencia de Trabajador
     * @param fechaNac Herencia de Trabajador
     * @param activo Herencia de Trabajador
     * @param idContratado Índice de Contratado
     * @param inicioContract Fecha de inicio de contrato
     * @param finalContract Fecha o estado del fin de contrato (String que puede contener: una fecha, temporal o indefinido)
     * @param salario Cantidad que cobra el trabajador al mes.
     * @param puestoTrabajo Puesto de trabajo del Trabajador
     */
    public Contratado(Long idPersona, String nombre, String apellidos, String identificador, String nacionalidad, String direccion, String poblacion, String telefono, String email, Long idTrabajador, Delegacion delegacion, Date fechaNac, boolean activo, Long idContratado, Date inicioContract, String finalContract, float salario, String puestoTrabajo) {
        super(idPersona, nombre, apellidos, identificador, nacionalidad, direccion, poblacion, telefono, email, idTrabajador, delegacion, fechaNac, activo);
        this.idContratado = idContratado;
        this.inicioContract = inicioContract;
        this.finalContract = finalContract;
        this.salario = salario;
        this.puestoTrabajo = puestoTrabajo;
    }

    /***
     *
     * @return Date: inicioContract
     */
    public Date getInicioContract() {
        return inicioContract;
    }

    /***
     *
     * @param inicioContract Date: Fecha de inicio del contrato
     */
    public void setInicioContract(Date inicioContract) {
        this.inicioContract = inicioContract;
    }

    /***
     *
     * @return finalContract
     */
    public String getFinalContract() {
        return finalContract;
    }

    /***
     *
     * @param finalContract Fecha o estado del fin de contrato (String que puede contener: una fecha, temporal o indefinido)
     */
    public void setFinalContract(String finalContract) {
        this.finalContract = finalContract;
    }

    /***
     *
     * @return salario
     */
    public float getSalario() {
        return salario;
    }

    /***
     *
     * @param salario Devuelve el Salario
     */
    public void setSalario(float salario) {
        this.salario = salario;
    }

    /***
     *
     * @return Devuelve el Puesto de Trabajo
     */
    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    /***
     *
     * @param puestoTrabajo
     */
    public void setPuestoTrabajo(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    /***
     *
     * @return String con los parámetros
     */
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

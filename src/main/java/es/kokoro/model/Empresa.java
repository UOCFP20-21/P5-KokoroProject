package es.kokoro.model;

/**
 * Clase empresa
 * Contiene información de la empresa
 */
public abstract class Empresa {
    //Atributos
    /**
     * Acumulador para darle un num. de empresa
     */
    private static int cuentaEmpresa=0;
    /**
     * Numero de empresa
     */
    private int idEmpresa;
    /**
     * Nombre Empresa
     */
    private String nombre;
    /**
     * Pais Empresa
     */
    private String pais;
    /**
     * Poblacion Empresa
     */
    private String poblacion;
    /**
     * Direccion Social Empresa
     */
    private String direccionSocial;
    /**
     * Razon social Empresa
     */
    private String razonSocial;
    /**
     * Identificación Social Empresa
     */
    private String identificacionSocial;
    /**
     * Telefono de Empresa
     */
    private int telefono;
    /**
     * Email de Empresa
     */
    private String email;

    //Constructor
   /* public Empresa(String nombre, String pais, String poblacion, String direccionSocial, String razonSocial, String identificacionSocial, int telefono, String email){
        this.idEmpresa = ++cuentaEmpresa;
        this.nombre =nombre;
        this.pais =pais;
        this.poblacion =poblacion;
        this.direccionSocial=direccionSocial;
        this.razonSocial=razonSocial;
        this.identificacionSocial = identificacionSocial;
        this.telefono = telefono;
        this.email = email;

    }*/

    public Empresa() {

    }


    //Metodos publicos
    /**
     * Devuelve el numero de empresa
     * @return numero empresa
     */
    public int getIdEmpresa(){
        return idEmpresa;
    }
    /**
     * Modifica el numero de empresa
     * @param idEmpresa
     */
    public void setIdEmpresa(int idEmpresa){

        this.idEmpresa = idEmpresa;
    }

    /**
     * Devuelve el nombre de la empresa
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de la empresa
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el pais de la empresa
     *
     * @return pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * Modifica el pais de la empresa
     *
     * @param pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }
    /**
     * Devuelve la población de la empresa
     *
     * @return poblacion
     */
    public String getPoblacion() {
        return poblacion;
    }

    /**
     * Modifica la población de la empresa
     *
     * @param poblacion
     */
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    /**
     * Devuelve la direccion Social de la empresa
     *
     * @return direccionSocial
     */
    public String getDireccionSocial() {
        return direccionSocial;
    }

    /**
     * Modifica la direccion Social de la empresa
     *
     * @param direccionSocial
     */
    public void setDireccionSocial(String direccionSocial) {
        this.direccionSocial = direccionSocial;
    }
    /**
     * Devuelve la razon Social de la empresa
     *
     * @return razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Modifica la Razon Social de la empresa
     *
     * @param razonSocial
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    /**
     * Devuelve la identificacion Social de la empresa
     *
     * @return identificacionSocial
     */
    public String getIdentificacionSocial() {
        return identificacionSocial;
    }

    /**
     * Modifica el identificación Social de la empresa
     *
     * @param identificacionSocial
     */
    public void setIdentificacionSocial(String identificacionSocial) {
        this.identificacionSocial = identificacionSocial;
    }
    /**
     * Devuelve el telefono de la empresa
     *
     * @return telefono
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Modifica el nombre de la empresa
     *
     * @param telefono
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    /**
     * Devuelve el email de la empresa
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifica el email de la empresa
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }


}

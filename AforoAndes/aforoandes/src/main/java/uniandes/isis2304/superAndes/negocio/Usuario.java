package uniandes.isis2304.superAndes.negocio;

public class Usuario implements VOUsuario{
    private long id;
    private String tipoIdentificacion;
    private String nombre;
    private String correo_electronico;
    private String palabra_clave;
    private long rolUsuario;
    private long idSucursal;
    
    
    public Usuario() {
        this.id = 0;
        this.tipoIdentificacion = "";
        this.nombre = "";
        this.correo_electronico = "";
        this.palabra_clave = "";
        this.rolUsuario = 0;
        this.idSucursal = 0;
    }
    public Usuario(long id, String tipoIdentificacion, String nombre, String correo_electronico, String palabra_clave,
            long rolNuevoUsuario, long sucuNuevoUsuario) {
        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.nombre = nombre;
        this.correo_electronico = correo_electronico;
        this.palabra_clave = palabra_clave;
        this.rolUsuario = rolNuevoUsuario;
        this.idSucursal = sucuNuevoUsuario;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }
    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo_electronico() {
        return correo_electronico;
    }
    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }
    public String getPalabra_clave() {
        return palabra_clave;
    }
    public void setPalabra_clave(String palabra_clave) {
        this.palabra_clave = palabra_clave;
    }
    public long getRolUsuario() {
        return getRolUsuario();
    }
    public void setRolUsuario(long rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
    public long getIdSucursal() {
        return idSucursal;
    }
    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", tipoIdentificacion=" + tipoIdentificacion + ", nombre=" + nombre
                + ", correo_electronico=" + correo_electronico + ", palabra_clave=" + palabra_clave + ", rolUsuario="
                + rolUsuario + "]";
    }


}

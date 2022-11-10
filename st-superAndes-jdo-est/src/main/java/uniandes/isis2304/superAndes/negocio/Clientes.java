package uniandes.isis2304.superAndes.negocio;

public class Clientes implements VOClientes{
    private long id;
    private String nombre;
    private String correo;
    private String direccion;
    private long id_supermercado;
    private long compra;
    private String tipo;
    public Clientes() {
        this.id = 0;
        this.nombre = "";
        this.correo = "";
        this.direccion = "";
        this.id_supermercado = 0;
        this.compra = 0;
        this.tipo = "";
    }
    public Clientes(long id, String nombre, String correo, String direccion, long id_supermercado, long compra, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.id_supermercado = id_supermercado;
        this.compra = compra;
        this.tipo = tipo;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public long getId_supermercado() {
        return id_supermercado;
    }
    public void setId_supermercado(long id_supermercado) {
        this.id_supermercado = id_supermercado;
    }
    public long getCompra() {
        return compra;
    }
    public void setCompra(long compra) {
        this.compra = compra;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    @Override
    public String toString() {
        return "Clientes [id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", direccion=" + direccion
                + ", id_supermercado=" + id_supermercado + ", compra=" + compra + "]";
    }
}

package uniandes.isis2304.superAndes.negocio;

public class RolUsuario implements VORolUsuario{
    private long idProducto;
    private String nombre;
    public RolUsuario() {
        this.idProducto = 0;
        this.nombre = "";
    }
    public RolUsuario(long idProducto, String nombre) {
        this.idProducto = idProducto;
        this.nombre = nombre;
    }
    public long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return "RolUsuario [idProducto=" + idProducto + ", nombre=" + nombre + "]";
    }
}

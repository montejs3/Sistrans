package uniandes.isis2304.superAndes.negocio;

public class ProveedoresProductos implements VOProveedoresProductos { 
    private long nitProveedor;
    private long idProducto;
    public ProveedoresProductos() {
        this.nitProveedor = 0;
        this.idProducto = 0;
    }
    public ProveedoresProductos(long nitProveedor, long idProducto) {
        this.nitProveedor = nitProveedor;
        this.idProducto = idProducto;
    }
    public long getNitProveedor() {
        return nitProveedor;
    }
    public void setNitProveedor(long nitProveedor) {
        this.nitProveedor = nitProveedor;
    }
    public long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }
    @Override
    public String toString() {
        return "ProveedoresProductos [nitProveedor=" + nitProveedor + ", idProducto=" + idProducto + "]";
    }
    
}

package uniandes.isis2304.superAndes.negocio;

public class ProductosCarrito implements VOProductosCarrito{
    private long idCarrito;
    private long codigoBarras;
    private int cantidad;
    public ProductosCarrito() {
        this.idCarrito = 0;
        this.codigoBarras = 0;
        this.cantidad = 0;
    }
    public ProductosCarrito(long idCarrito, long codigoBarras, int cantidad) {
        this.idCarrito = idCarrito;
        this.codigoBarras = codigoBarras;
        this.cantidad = cantidad;
    }
    public long getIdCarrito() {
        return idCarrito;
    }
    public void setIdCarrito(long idCarrito) {
        this.idCarrito = idCarrito;
    }
    public long getCodigoBarras() {
        return codigoBarras;
    }
    public void setCodigoBarras(long codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    @Override
    public String toString() {
        return "ProductosCarrito [idCarrito=" + idCarrito + ", codigoBarras=" + codigoBarras + ", cantidad=" + cantidad
                + "]";
    }
    
}

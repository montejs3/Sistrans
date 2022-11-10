package uniandes.isis2304.superAndes.negocio;

public class productoCompra implements VOProductoCompra {
    private int cantidadProducto;
    private long compra;
    private long producto;
    private long promocion;
    public productoCompra() {
        this.cantidadProducto = 0;
        this.compra = 0;
        this.producto = 0;
        this.promocion = 0;
    }
    public productoCompra(int cantidadProducto, long compra, long producto, long promocion) {
        this.cantidadProducto = cantidadProducto;
        this.compra = compra;
        this.producto = producto;
        this.promocion = promocion;
    }
    public int getCantidadProducto() {
        return cantidadProducto;
    }
    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
    public long getCompra() {
        return compra;
    }
    public void setCompra(long compra) {
        this.compra = compra;
    }
    public long getProducto() {
        return producto;
    }
    public void setProducto(long producto) {
        this.producto = producto;
    }
    public long getPromocion() {
        return promocion;
    }
    public void setPromocion(long promocion) {
        this.promocion = promocion;
    }
    @Override
    public String toString() {
        return "productoCompra [cantidadProducto=" + cantidadProducto + ", compra=" + compra + ", producto=" + producto
                + ", promocion=" + promocion + "]";
    }
}

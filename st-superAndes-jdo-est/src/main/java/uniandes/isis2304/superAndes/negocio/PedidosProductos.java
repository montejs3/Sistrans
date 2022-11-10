package uniandes.isis2304.superAndes.negocio;

public class PedidosProductos implements VOPedidosProductos{
    private long idPedidos;
    private long idProducto; 
    public PedidosProductos() {
    }
    public PedidosProductos(long idPedidos, long idProducto) {
        this.idPedidos = idPedidos;
        this.idProducto = idProducto;
    }
    public long getIdPedidos() {
        return idPedidos;
    }
    public void setIdPedidos(long idPedidos) {
        this.idPedidos = idPedidos;
    }
    public long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }
    @Override
    public String toString() {
        return "PedidosProductos [idPedidos=" + idPedidos + ", idProducto=" + idProducto + "]";
    }
    
}

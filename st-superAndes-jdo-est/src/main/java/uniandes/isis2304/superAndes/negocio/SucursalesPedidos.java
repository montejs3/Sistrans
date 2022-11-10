package uniandes.isis2304.superAndes.negocio;

public class SucursalesPedidos implements VOSucursalesPedidos{
    private long idSucursal;
    private long idPedido;
    public SucursalesPedidos() {
        this.idSucursal = 0;
        this.idPedido = 0;
    }
    public SucursalesPedidos(long idSucursal, long idPedido) {
        this.idSucursal = idSucursal;
        this.idPedido = idPedido;
    }
    public long getIdSucursal() {
        return idSucursal;
    }
    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }
    public long getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }
    @Override
    public String toString() {
        return "SucursalesPedidos [idSucursal=" + idSucursal + ", idPedido=" + idPedido + "]";
    }
}

package uniandes.isis2304.superAndes.negocio;

public class ProveedoresSucursales implements VOProveedoresSucursales{
    private long idProveedor;
    private long idSucursal;
    public ProveedoresSucursales() {
        this.idProveedor = 0;
        this.idSucursal = 0;
    }
    public ProveedoresSucursales(long idProveedor, long idSucursal) {
        this.idProveedor = idProveedor;
        this.idSucursal = idSucursal;
    }
    public long getIdProveedor() {
        return idProveedor;
    }
    public void setIdProveedor(long idProveedor) {
        this.idProveedor = idProveedor;
    }
    public long getIdSucursal() {
        return idSucursal;
    }
    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }
    @Override
    public String toString() {
        return "ProveedoresSucursales [idProveedor=" + idProveedor + ", idSucursal=" + idSucursal + "]";
    }

}

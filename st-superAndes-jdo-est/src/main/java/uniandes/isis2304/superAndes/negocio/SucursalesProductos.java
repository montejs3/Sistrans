package uniandes.isis2304.superAndes.negocio;

public class SucursalesProductos implements VOSucursalesProductos{
    private long idSucursal;
    private long idProducto;
    private int existenciasEnEstantes;
    private int existenciasEnBodega;
    

    public SucursalesProductos() {
        this.idSucursal = 0;
        this.idProducto = 0;
        this.existenciasEnEstantes = 0;
        this.existenciasEnBodega = 0;
    }
    public SucursalesProductos(long idSucursal, long idProducto, long existenciasEnEstante, long existenciasEnBodega) {
        this.idSucursal = idSucursal;
        this.idProducto = idProducto;
    }
    public long getIdSucursal() {
        return idSucursal;
    }
    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }
    public long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }
    public int getExistenciasEnEstantes() {
        return existenciasEnEstantes;
    }
    public void setExistenciasEnEstantes(int existenciasEnEstantes) {
        this.existenciasEnEstantes = existenciasEnEstantes;
    }
    public int getExistenciasEnBodega() {
        return existenciasEnBodega;
    }
    public void setExistenciasEnBodega(int existenciasEnBodega) {
        this.existenciasEnBodega = existenciasEnBodega;
    }

    @Override
    public String toString() {
        return "SucursalesProductos [idSucursal=" + idSucursal + ", idProducto=" + idProducto
                + ", existenciasEnEstantes=" + existenciasEnEstantes + ", existenciasEnBodega=" + existenciasEnBodega
                + "]";
    }
    
    
}

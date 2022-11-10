package uniandes.isis2304.superAndes.negocio;

public interface VOSucursalesProductos {
    public long getIdSucursal();
    public long getIdProducto();
    public int getExistenciasEnEstantes();
    public int getExistenciasEnBodega();
    
    @Override
    public String toString();
}

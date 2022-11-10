package uniandes.isis2304.superAndes.negocio;

public interface VOBodegas {
    public long getId_bodega();

    public double getCapacidad();

    public int getOcupacion();

    public long getTipoProducto();

    public long getSucursal();

    @Override
    public String toString();
}

package uniandes.isis2304.superAndes.negocio;

public interface VOEstantes {
    public long getId_estantes();

    public int getCant_productos();

    public long getTipoProducto();

    public double getCapacidad();

    public int getOcupacion();

    public long getSucursal();

    @Override
    public String toString();
}


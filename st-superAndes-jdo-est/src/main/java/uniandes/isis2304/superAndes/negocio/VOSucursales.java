package uniandes.isis2304.superAndes.negocio;

public interface VOSucursales {
    public String getCiudad();

    public String getDireccion();

    public String getNombre();

    public long getId_sucursal();

    public int getNivel_reorden();

    public int getRecompra();

    public long getId_supermercado();

    @Override
    public String toString();
}

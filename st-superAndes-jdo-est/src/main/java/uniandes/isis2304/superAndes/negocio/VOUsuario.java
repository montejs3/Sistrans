package uniandes.isis2304.superAndes.negocio;

public interface VOUsuario {
    public long getId();

    public String getTipoIdentificacion();

    public String getNombre();

    public String getCorreo_electronico();

    public String getPalabra_clave();

    public long getRolUsuario();

    public VOSucursales getIdSucursal();

    @Override
    public String toString();
}

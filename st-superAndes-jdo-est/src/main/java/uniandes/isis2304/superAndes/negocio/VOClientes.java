package uniandes.isis2304.superAndes.negocio;

public interface VOClientes {
    public long getId();
    public String getNombre();
    public String getCorreo();
    public String getDireccion();
    public long getId_supermercado();
    public long getCompra();
    public String getTipo();
    @Override
    public String toString();
}

package uniandes.isis2304.superAndes.negocio;

public interface VOTipoProducto {
    public long getId();

    public String getTipo();

    public String getCategoria();

    @Override
    public String toString();
}

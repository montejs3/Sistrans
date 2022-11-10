package uniandes.isis2304.superAndes.negocio;

public interface VOProveedores {
    public long getNit();

    public String getNombre();

    public int getCalidad();

    public boolean isEsExclusivo();

    @Override
    public String toString();
}

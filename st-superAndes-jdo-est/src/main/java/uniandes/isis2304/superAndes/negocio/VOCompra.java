package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

public interface VOCompra {
    public long getId();
    public int getPrecioCompra();
    public Timestamp getFecha();
    public long getCliente();
    @Override
    public String toString();
}

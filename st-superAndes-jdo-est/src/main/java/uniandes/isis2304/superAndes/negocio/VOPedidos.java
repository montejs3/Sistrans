package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public interface VOPedidos {
    public long getId_pedido();

    public String getProductos();

    public double getVolumenes();

    public double getPrecio();

    public Date getFecha();

    @Override
    public String toString();
}

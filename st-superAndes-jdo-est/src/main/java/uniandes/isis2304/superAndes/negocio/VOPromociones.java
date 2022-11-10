package uniandes.isis2304.superAndes.negocio;


import java.sql.Timestamp;
import java.util.Date;

public interface VOPromociones {
    public long getNum_promocion();

    public String getTipo_promocion();

    public Timestamp getFecha_inicio();

    public Timestamp getFecha_final();

    public int getPorcentaje();

    public long getProveedor();

    public int getUnidadesVendidas();

    public int getUnidadesDisponibles();

    @Override
    public String toString();
}

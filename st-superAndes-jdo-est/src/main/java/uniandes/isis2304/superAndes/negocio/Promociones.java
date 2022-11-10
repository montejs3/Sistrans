package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;
import java.util.Date;

public class Promociones implements VOPromociones{
    private long num_promocion;
    private String tipo_promocion;
    private Timestamp fecha_inicio;
    private Timestamp fecha_final;
    private int porcentaje;
    private String estado;
    private long proveedor;
    private int unidadesVendidas;
    private int unidadesDisponibles;

    


    


    public Promociones() {
        this.num_promocion = 0;
        this.tipo_promocion = "";
        this.fecha_inicio = new Timestamp(0);
        this.fecha_final = new Timestamp(0);
        this.porcentaje = 0;
        this.estado = "";
        this.proveedor = 0;
        this.unidadesVendidas = 0;
        this.unidadesDisponibles = 0;
    }
    

    public Promociones(long num_promocion, String tipo_promocion, Timestamp fecha_inicio, Timestamp fecha_final, int porcentaje, String estado, long proveedor, int unidadesVendidas, int unidadesDisponibles ) {
        this.num_promocion = num_promocion;
        this.tipo_promocion = tipo_promocion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.porcentaje = porcentaje;
        this.estado = estado;
        this.proveedor = proveedor;
        this.unidadesVendidas = unidadesVendidas;
        this.unidadesDisponibles = unidadesDisponibles;
    }
    public long getNum_promocion() {
        return num_promocion;
    }
    public void setNum_promocion(long num_promocion) {
        this.num_promocion = num_promocion;
    }
    public String getTipo_promocion() {
        return tipo_promocion;
    }
    public void setTipo_promocion(String tipo_promocion) {
        this.tipo_promocion = tipo_promocion;
    }
    public Timestamp getFecha_inicio() {
        return fecha_inicio;
    }
    public void setFecha_inicio(Timestamp fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    public Timestamp getFecha_final() {
        return fecha_final;
    }
    public void setFecha_final(Timestamp fecha_final) {
        this.fecha_final = fecha_final;
    }
    public int getPorcentaje() {
        return porcentaje;
    }
    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long getProveedor() {
        return proveedor;
    }
    
    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }


    public void setUnidadesVendidas(int unidadesVendidas) {
        unidadesVendidas = unidadesVendidas;
    }


    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }


    public void setUnidadesDisponibles(int unidadesDisponibles) {
        unidadesDisponibles = unidadesDisponibles;
    }

    public void setProveedor(long proveedor) {
        this.proveedor = proveedor;
    }
    @Override
    public String toString() {
        return "Promociones [num_promocion=" + num_promocion + ", tipo_promocion=" + tipo_promocion + ", fecha_inicio="
                + fecha_inicio + ", fecha_final=" + fecha_final + ", porcentaje=" + porcentaje + ", estado=" + estado
                + ", proveedor=" + proveedor + ", UnidadesVendidas=" + unidadesVendidas + ", UnidadesDisponibles="
                + unidadesDisponibles + "]";
    }
}

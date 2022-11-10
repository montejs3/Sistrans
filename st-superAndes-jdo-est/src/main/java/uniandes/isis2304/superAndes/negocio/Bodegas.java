package uniandes.isis2304.superAndes.negocio;

public class Bodegas implements VOBodegas{
    
    private long id_bodega;

    private double capacidad;

    private int ocupacion;

    private long tipoProducto;

    private long sucursal;

    public Bodegas() {
        this.id_bodega = 0;
        this.capacidad = 0;
        this.ocupacion = 0;
        this.tipoProducto = 0;
        this.sucursal = 0;
    }

    public Bodegas(long id_bodega, double capacidad, int ocupacion, long tipoProducto, long sucursal) {
        this.id_bodega = id_bodega;
        this.capacidad = capacidad;
        this.ocupacion = ocupacion;
        this.tipoProducto = tipoProducto;
        this.sucursal = sucursal;
    }

    public long getId_bodega() {
        return id_bodega;
    }

    public void setId_bodega(long id_bodega) {
        this.id_bodega = id_bodega;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    public int getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(int ocupacion) {
        this.ocupacion = ocupacion;
    }

    public long getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(long tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public long getSucursal() {
        return sucursal;
    }

    public void setSucursal(long sucursal) {
        this.sucursal = sucursal;
    }
    
    @Override
    public String toString() {
        return "Bodegas [id_bodega=" + id_bodega + ", capacidad=" + capacidad + ", ocupacion=" + ocupacion
                + ", tipoProducto=" + tipoProducto + ", sucursal=" + sucursal + "]";
    }

}

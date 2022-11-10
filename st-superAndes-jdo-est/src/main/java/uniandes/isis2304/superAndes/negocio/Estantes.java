package uniandes.isis2304.superAndes.negocio;

public class Estantes implements VOEstantes{
    private long id_estantes;

    private int cant_productos;

    private long tipoProducto;

    private double capacidad;

    private int ocupacion;

    private long sucursal;

    public Estantes() {
        this.id_estantes = 0;
        this.cant_productos = 0;
        this.tipoProducto = 0;
        this.capacidad = 0;
        this.ocupacion = 0;
        this.sucursal = 0;
    }
    public Estantes(long id_estantes, int cant_productos, long tipoProducto, double capacidad, int ocupacion,
            long sucursal) {
        this.id_estantes = id_estantes;
        this.cant_productos = cant_productos;
        this.tipoProducto = tipoProducto;
        this.capacidad = capacidad;
        this.ocupacion = ocupacion;
        this.sucursal = sucursal;
    }

    public long getId_estantes() {
        return id_estantes;
    }

    public void setId_estantes(long id_estantes) {
        this.id_estantes = id_estantes;
    }

    public int getCant_productos() {
        return cant_productos;
    }

    public void setCant_productos(int cant_productos) {
        this.cant_productos = cant_productos;
    }

    public long getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(long tipoProducto) {
        this.tipoProducto = tipoProducto;
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

    public long getSucursal() {
        return sucursal;
    }

    public void setSucursal(long sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public String toString() {
        return "Estantes [id_estantes=" + id_estantes + ", cant_productos=" + cant_productos + ", tipoProducto="
                + tipoProducto + ", capacidad=" + capacidad + ", ocupacion=" + ocupacion + ", sucursal=" + sucursal
                + "]";
    }
}

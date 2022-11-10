package uniandes.isis2304.superAndes.negocio;

public class Sucursales implements VOSucursales{
    
    private String ciudad;

    private String direccion;

    private String nombre;

    private long id_sucursal;

    private int nivel_reorden;

    private int recompra;

    private long id_supermercado;

    public Sucursales(){
        this.ciudad = "";
        this.direccion = "";
        this.nombre = "";
        this.id_sucursal = 0;
        this.nivel_reorden = 0;
        this.recompra = 0;
        this.id_supermercado = 0;
    }

    public Sucursales(String ciudad, String direccion, String nombre, long id_sucursal, int nivel_reorden, int recompra,
            long id_supermercado) {
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.nombre = nombre;
        this.id_sucursal = id_sucursal;
        this.nivel_reorden = nivel_reorden;
        this.recompra = recompra;
        this.id_supermercado = id_supermercado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(long id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public int getNivel_reorden() {
        return nivel_reorden;
    }

    public void setNivel_reorden(int nivel_reorden) {
        this.nivel_reorden = nivel_reorden;
    }

    public int getRecompra() {
        return recompra;
    }

    public void setRecompra(int recompra) {
        this.recompra = recompra;
    }

    public long getId_supermercado() {
        return id_supermercado;
    }

    public void setId_supermercado(long id_supermercado) {
        this.id_supermercado = id_supermercado;
    }

    @Override
    public String toString() {
        return "Sucursales [ciudad=" + ciudad + ", direccion=" + direccion + ", nombre=" + nombre + ", id_sucursal="
                + id_sucursal + ", nivel_reorden=" + nivel_reorden + ", recompra=" + recompra + ", id_supermercado="
                + id_supermercado + "]";
    }
}

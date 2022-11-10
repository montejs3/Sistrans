package uniandes.isis2304.superAndes.negocio;

public class Proveedores implements VOProveedores {
    private long nit;

    private String nombre;

    private int calidad;
    
    private boolean esExclusivo;

    public Proveedores() {
        this.nit = 0;
        this.nombre = "";
        this.calidad = 0;
        this.esExclusivo = false;
    }

    public Proveedores(long nit, String nombre, int calidad, boolean esExclusivo) {
        this.nit = nit;
        this.nombre = nombre;
        this.calidad = calidad;
        this.esExclusivo = esExclusivo;
    }

    public long getNit() {
        return nit;
    }

    public void setNit(long nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCalidad() {
        return calidad;
    }

    public void setCalidad(int calidad) {
        this.calidad = calidad;
    }

    public boolean isEsExclusivo() {
        return esExclusivo;
    }

    public void setEsExclusivo(boolean esExclusivo) {
        this.esExclusivo = esExclusivo;
    }

    @Override
    public String toString() {
        return "Proveedores [nit=" + nit + ", nombre=" + nombre + ", calidad=" + calidad + ", esExclusivo="
                + esExclusivo + "]";
    }
}

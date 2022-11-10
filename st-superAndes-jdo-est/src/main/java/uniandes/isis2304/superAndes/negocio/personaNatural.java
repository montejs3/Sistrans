package uniandes.isis2304.superAndes.negocio;

public class personaNatural implements VOPersonaNatural{
    private long cedula;

    public personaNatural() {
        this.cedula = 0;
    }

    public personaNatural(long cedula) {
        this.cedula = cedula;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }
    
    @Override
    public String toString() {
        return "personaNatural [cedula=" + cedula + "]";
    }
}

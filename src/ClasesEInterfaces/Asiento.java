package ClasesEInterfaces;

public class Asiento implements getId{
    private int id;
    private String descripcion;
    private boolean ocupado;
    private Viaje viaje;
    private Pasaje pasaje;

    public boolean isOcupado() {
        return ocupado;
    }

    public Asiento() {}

    public Asiento(int id, String descripcion, boolean ocupado, Viaje viaje, Pasaje pasaje) {
        this.id = id;
        this.descripcion = descripcion;
        this.ocupado = ocupado;
        this.viaje = viaje;
        this.pasaje = pasaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setPasaje(Pasaje pasaje) {
        this.pasaje = pasaje;
    }

    public Pasaje getPasaje() {
        return pasaje;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    
}

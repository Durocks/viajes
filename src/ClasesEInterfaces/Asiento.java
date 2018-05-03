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

    @Override
    public String toString() {
        return "Asiento " + id + "\tDescripcion:\t" + descripcion + "\tOcupado: " +
                new Validator().printSiNo(ocupado) + "\tViaje:\t" + viaje.getTrajecto() +
                "\t\tPasaje:\t" + this.pasajeToString();
    }
    
    public String toStringVendedor(){
        return "Asiento " + id + "\tDescripcion:\t" + descripcion + "\tPasajero: " +
                pasaje.getPasajero().getNombre() + " " + pasaje.getPasajero().getApellido() +
                "\tViaje:\t" + viaje.getTrajecto(); 
    }

    private String pasajeToString() {
        if (this.pasaje == null)
            return "Ninguno";
        else
            return this.pasaje.id + "";
    }
}

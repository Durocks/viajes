package ClasesEInterfaces;

public class Asiento implements getId{
    private int id;
    private String descripcion;
    private boolean ocupado;
    private Viaje viaje;
    private Escala vuelo;

    public boolean isOcupado() {
        return ocupado;
    }

    public Asiento() {}

    public Asiento(int id, String descripcion, boolean ocupado, Viaje viaje, Escala vuelo) {
        this.id = id;
        this.descripcion = descripcion;
        this.ocupado = ocupado;
        this.viaje = viaje;
        this.vuelo = vuelo;
    }
    
    public Asiento(int id, String descripcion, boolean ocupado, Viaje viaje) {
        this.id = id;
        this.descripcion = descripcion;
        this.ocupado = ocupado;
        this.viaje = viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public Escala getVuelo() {
        return vuelo;
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
                "\t\tPasaje:\t" + this.vueloToString();
    }
    
    public String toStringVendedor(){
        return "Asiento " + id + "\tDescripcion:\t" + descripcion + "\tPasajero: " +
                vuelo.getPasaje().getPasajero().getNombre() + " " + vuelo.getPasaje().getPasajero().getApellido() +
                "\tViaje:\t" + viaje.getTrajecto(); 
    }

    private String vueloToString() {
        if (this.vuelo == null)
            return "Ninguno";
        else
            return this.vuelo.getId() + "";
    }

    public void setVuelo(Escala vuelo) {
        this.vuelo = vuelo;
    }
}

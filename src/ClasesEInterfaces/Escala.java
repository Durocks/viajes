package ClasesEInterfaces;

public class Escala implements getId{
    private int id;
    private Viaje viaje;
    private Asiento asiento;
    private boolean tratamientoEspecial;
    private Pasaje pasaje;

    public void setTratamientoEspecial(boolean tratamientoEspecial) {
        this.tratamientoEspecial = tratamientoEspecial;
    }

    public void setPasaje(Pasaje pasaje) {
        this.pasaje = pasaje;
    }

    public boolean isTratamientoEspecial() {
        return tratamientoEspecial;
    }

    public Pasaje getPasaje() {
        return pasaje;
    }

    public Escala(int id, Viaje viaje, Asiento asiento, boolean tratamientoEspecial, Pasaje pasaje) {
        this.id = id;
        this.viaje = viaje;
        this.asiento = asiento;
        this.tratamientoEspecial = tratamientoEspecial;
        this.pasaje = pasaje;
    }

    public Escala() {
    }

    public int getId() {
        return id;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    @Override
    public String toString() {
        return "Vuelo " + this.id + ":\tViaje: " + this.viaje.getTrajecto() + "\tAsiento: " +
                this.asiento.getDescripcion() + "\tTratamientoEspecial: "
                + new Validator().printSiNo(this.tratamientoEspecial) + "\tPasaje: " + pasaje.getId();
    }
    
    public String toStringCompleto() {
        return "Vuelo " + this.id + "\tOrigen: " + this.viaje.getCiudadDePartida() + "\tDestino: " + viaje.getDestino() + 
                "\tEmpresa: " + viaje.getEmpresaDelViaje().getNombre() + ", CUIT " + viaje.getEmpresaDelViaje().getCuit() +
                "\tFecha de partida: " + new Validator().imprimirFecha(this.viaje.getFecha()) + "\tFranja horaria: " +
                viaje.getFranjaHoraria() + "\tNumero de asiento: " + asiento.getDescripcion();
    }
    
    public String toStringCompletoPuntoYComa() {
        return "Vuelo " + id + ";Origen: " + viaje.getCiudadDePartida() + ";Destino: " + viaje.getDestino() + 
                ";Empresa: " + viaje.getEmpresaDelViaje().getNombre() + ", CUIT " + viaje.getEmpresaDelViaje().getCuit() +
                ";Fecha de partida: " + new Validator().imprimirFecha(viaje.getFecha()) + "\tFranja horaria: " +
                viaje.getFranjaHoraria() + "\tNumero de asiento: " + asiento.getDescripcion();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }
    
    
}





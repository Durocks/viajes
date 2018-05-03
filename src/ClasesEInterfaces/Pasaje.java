package ClasesEInterfaces;

public abstract class Pasaje implements Calculos, getId{
    protected int id;
    protected Asiento asiento;
    protected Pasajero pasajero;
    protected Viaje viaje;
    protected boolean tratamientoEspecial;
    
    public Pasaje(){super();}

    public Pasaje(int id, Asiento asiento, Pasajero pasajero, Viaje viaje, boolean tratamientoEspecial) {
        this.id = id;
        this.asiento = asiento;
        this.pasajero = pasajero;
        this.viaje = viaje;
        this.tratamientoEspecial = tratamientoEspecial;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public Asiento getAsiento() {
        return asiento;
    }
    
    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }    

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Viaje getViaje() {
        return viaje;
    }

    @Override
    public String toString() {
        return "Pasaje " + id + "\tAsiento:\t" + asiento + "\tPasajero:\t" + pasajero.getNombre() +
                " "+ pasajero.getApellido() + "\tViaje:\t" + viaje.getTrajecto() + "\tTratamientos Especiales:\t"
                + new Validator().printSiNo(tratamientoEspecial);
    }

    public void setTratamientoEspecial(boolean tratamientoEspecial) {
        this.tratamientoEspecial = tratamientoEspecial;
    }

    public boolean isTratamientoEspecial() {
        return tratamientoEspecial;
    }

    public String toStringCompleto() {
        return "Pasaje " + id + "\tOrigen: " + viaje.getCiudadDePartida() + "\tDestino: " + viaje.getDestino() + 
                "\tEmpresa: " + viaje.getEmpresaDelViaje().getNombre() + ", CUIT " + viaje.getEmpresaDelViaje().getCuit() +
                "\tFecha de partida: " + new Validator().imprimirFecha(viaje.getFecha()) + "\tFranja horaria: " +
                viaje.getFranjaHoraria() + "\tNumero de asiento: " + asiento.getDescripcion() + "\t" + 
                new Validator().align("Pasajero: " + pasajero.getNombre() + " " + pasajero.getApellido(), 3) +
                "\tDNI: " + pasajero.getDocumento() + 
                "\tMenor de 18: " + pasajero.calcularMenor();
    }
    
    public String toStringCompletoPuntoYComa() {
        return "Pasaje " + id + "; Origen: " + viaje.getCiudadDePartida() + "; Destino: " + viaje.getDestino() + 
                "; Empresa: " + viaje.getEmpresaDelViaje().getNombre() + ", CUIT " + viaje.getEmpresaDelViaje().getCuit() +
                "; Fecha de partida: " + new Validator().imprimirFecha(viaje.getFecha()) + "; Franja horaria: " +
                viaje.getFranjaHoraria() + "; Numero de asiento: " + asiento.getDescripcion() + "; " + 
                new Validator().align("Pasajero: " + pasajero.getNombre() + " " + pasajero.getApellido(), 3) +
                "; DNI: " + pasajero.getDocumento() + 
                "; Menor de 18: " + pasajero.calcularMenor();
    }
    
    
}

package ClasesEInterfaces;

import java.util.ArrayList;
import java.util.List;

public abstract class Pasaje implements Calculos, getId{
    protected int id;
    protected Asiento asiento;
    protected int cantidad;
    protected Pasajero pasajero;
    protected Viaje viaje;
    protected final List <String> tratamientosEspeciales;
    
    public Pasaje(){super();this.tratamientosEspeciales = new ArrayList();
}

    public Pasaje(int id, Asiento asiento, int cantidad, Pasajero pasajero, Viaje viaje, List<String> tratamientosEspeciales) {
        this.id = id;
        this.asiento = asiento;
        this.cantidad = cantidad;
        this.pasajero = pasajero;
        this.viaje = viaje;
        this.tratamientosEspeciales = tratamientosEspeciales;
    }

    public List<String> getTratamientosEspeciales() {
        return tratamientosEspeciales;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }    

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Viaje getViaje() {
        return viaje;
    }
}

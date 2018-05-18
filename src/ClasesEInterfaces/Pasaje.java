package ClasesEInterfaces;

import java.util.ArrayList;
import java.util.List;

public abstract class Pasaje implements getId{
    protected int id;
    protected Pasajero pasajero;
    protected List<Escala> vuelos;
    
    public Pasaje(){
        super();
        this.vuelos = new ArrayList();
    }

    public Pasaje(int id, Pasajero pasajero) {
        this.vuelos = new ArrayList();
        this.id = id;
        this.pasajero = pasajero;
    }
    
    public Pasaje(int id, Pasajero pasajero, List<Escala> vuelos) {
        this.id = id;
        this.pasajero = pasajero;
        this.vuelos = vuelos;
    }

    public void setVuelos(List<Escala> vuelos) {
        this.vuelos = vuelos;
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

    public Pasajero getPasajero() {
        return pasajero;
    }

    public List<Escala> getVuelos() {
        return vuelos;
    }

    @Override
    public String toString() {
        String text = "Pasaje " + id + "\tPasajero:\t" + pasajero.getNombre() +
                " "+ pasajero.getApellido() + "\n";
        for (Escala e:this.vuelos)
            text += "\t" + e.toString() + "\n";
        return text;
    }

    public String toStringCompleto() {
        String text = "Pasaje " + id +  "\t" + 
                new Validator().align("Pasajero: " + pasajero.getNombre() + " " + pasajero.getApellido(), 3) +
                "\tDNI: " + pasajero.getDocumento() + "\tMenor de 18: " + pasajero.calcularMenor();
        for (Escala e:this.vuelos)
            text += "\t" + e.toStringCompleto() + "\n";
        return text;
    }
    
    public String toStringCompletoPuntoYComa() {
        String text = "Pasaje " + id +  ";" + 
                new Validator().align("Pasajero: " + pasajero.getNombre() + " " + pasajero.getApellido(), 3) +
                ";DNI: " + pasajero.getDocumento() + ";Menor de 18: " + pasajero.calcularMenor();
        for (Escala e:this.vuelos)
            text += ";" + e.toStringCompleto() + "\n";
        return text;
    }

    public double getPrecioPasaje() {
        double total = 0;
        for (Escala v:this.vuelos)
            total += v.getViaje().getPrecioPasaje();
        return total;
    }
    
    
}

package ClasesEInterfaces;

import java.util.List;

public class PasajeAereo extends Pasaje{

    public PasajeAereo(){super();}

    public PasajeAereo(int id, Asiento asiento, Pasajero pasajero, Viaje viaje, boolean tratamientoEspecial) {
        super(id, asiento, pasajero, viaje, tratamientoEspecial);
    }

    @Override
    public double calcularPrecio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toStringCompleto() {
        return "Pasaje " + id + "\tOrigen: " + viaje.getCiudadDePartida() + "\tDestino: " + viaje.getDestino() +
                "\tNumero de vuelo: " + ((ViajeAereo)viaje).getNroDeVuelo() +
                "\t\tEmpresa: " + viaje.getEmpresaDelViaje().getNombre() + " ,CUIT " + viaje.getEmpresaDelViaje().getCuit() +
                "\tFecha de partida: " + new Validator().imprimirFecha(viaje.getFecha()) + "\tFranja horaria: " +
                viaje.getFranjaHoraria() + "\tNumero de asiento: " + asiento.getDescripcion() + "\t"+ 
                new Validator().align("Pasajero: " + 
                pasajero.getNombre() + " " + pasajero.getApellido(), 3) + "\t\tDNI: " + pasajero.getDocumento() + 
                "\tMenor de 18: " + pasajero.calcularMenor();
    }
}

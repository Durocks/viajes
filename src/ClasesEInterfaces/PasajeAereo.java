package ClasesEInterfaces;

import java.util.List;

public class PasajeAereo extends Pasaje{

    public PasajeAereo(){super();}

    public PasajeAereo(int id, Pasajero pasajero) {
        super(id, pasajero);
    }
    
//    @Override
//    public String toStringCompleto() {
//        return "Pasaje " + id + "\tOrigen: " + viaje.getCiudadDePartida() + "\tDestino: " + viaje.getDestino() +
//                "\tNumero de vuelo: " + ((ViajeAereo)viaje).getNroDeVuelo() +
//                "\t\tEmpresa: " + viaje.getEmpresaDelViaje().getNombre() + " ,CUIT " + viaje.getEmpresaDelViaje().getCuit() +
//                "\tFecha de partida: " + new Validator().imprimirFecha(viaje.getFecha()) + "\tFranja horaria: " +
//                viaje.getFranjaHoraria() + "\tNumero de asiento: " + asiento.getDescripcion() + "\t"+ 
//                new Validator().align("Pasajero: " + 
//                pasajero.getNombre() + " " + pasajero.getApellido(), 3) + "\t\tDNI: " + pasajero.getDocumento() + 
//                "\tMenor de 18: " + pasajero.calcularMenor();
//    }
}

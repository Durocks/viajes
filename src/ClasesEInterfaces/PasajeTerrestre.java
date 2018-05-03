package ClasesEInterfaces;

import java.util.List;

/**
 *
 * @author cristolagos
 */
public class PasajeTerrestre extends Pasaje{

    public PasajeTerrestre(){super();}

    public PasajeTerrestre(int id, Asiento asiento, Pasajero pasajero, Viaje viaje, boolean tratamientoEspecial) {
        super(id, asiento, pasajero, viaje, tratamientoEspecial);
    }
    
}

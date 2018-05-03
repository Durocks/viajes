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

    @Override
    public double calcularPrecio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

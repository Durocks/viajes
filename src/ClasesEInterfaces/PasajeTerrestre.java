package ClasesEInterfaces;

import java.util.List;

/**
 *
 * @author cristolagos
 */
public class PasajeTerrestre extends Pasaje{

    public PasajeTerrestre(){super();}

    public PasajeTerrestre(int id, Asiento asiento, int cantidad, Pasajero pasajero, Viaje viaje, List<String> tratamientosEspeciales) {
        super(id, asiento, cantidad, pasajero, viaje, tratamientosEspeciales);
    }

    @Override
    public double calcularPrecio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

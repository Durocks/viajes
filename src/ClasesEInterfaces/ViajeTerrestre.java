/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesEInterfaces;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author cristolagos
 */
public class ViajeTerrestre extends Viaje {

    public ViajeTerrestre() {}

    public ViajeTerrestre(int id, String categoria, String ciudadDePartida,String destino,Calendar fecha,
            String franjaHoraria, List<Pasajero> pasajeros, EmpresaDeViajes empresaDelViaje, double precioPasaje) {
        super(id, categoria, ciudadDePartida, destino, fecha, franjaHoraria, pasajeros, empresaDelViaje, precioPasaje);
    }
    
    @Override
    public String toString() {
        return "Viaje\t" + this.id + "\tTipo:\tTerrestre" + "\tCategoria:\t" +
                new Validator().align(this.categoria, 2) + "\tCiudad de Partida:\t" + 
                this.ciudadDePartida + "\tCiudad de Destino:\t" +
                new Validator().align(this.destino, 2) + "\tFecha:\t" + 
                new Validator().imprimirFecha(this.fecha) + "\tFranja horaria:\t" + 
                new Validator().align(getFranjaHoraria(), 2) +
                "Empresa del viaje:\t" + new Validator().align(this.empresaDelViaje.getNombre(), 2)
                 + "\tPrecio por pasaje\t" + this.getPrecioPasaje() + " $";
    }

    @Override
    protected void agregadorDeAsientos(){
        int idAsiento = 0, num = 1;
        for (int i = 1; i <= 20; i++){
            for (char letra = 'A'; letra <= 'D'; letra++){
                this.asientos.add(new Asiento(idAsiento, num + "" + letra, false, this, null));
                idAsiento++;
            }
            num ++;
        }
    }
    
}

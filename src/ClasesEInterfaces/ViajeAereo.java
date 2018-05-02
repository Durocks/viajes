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
public class ViajeAereo extends Viaje {

    public ViajeAereo() {
    }

    public ViajeAereo(int id, String categoria, String ciudadDePartida, String destino, Calendar fecha,
                String franjaHoraria, List<Pasajero> pasajeros, EmpresaDeViajes empresaDelViaje) {
        super(id, categoria, ciudadDePartida, destino, fecha, franjaHoraria, pasajeros, empresaDelViaje);
    }

    @Override
    public String toString() {
        return "Viaje\t" + this.id + "\tTipo:\tAereo\t" + "\tCategoria:\t" + this.categoria + "\tCiudad de Partida:\t" + 
                this.ciudadDePartida + "\tCiudad de Destino:\t" + this.destino + "\tFecha:\t" + 
                new Validator().imprimirFecha(this.fecha) + "\tFranja horaria:\t" + this.franjaHoraria +
                "\tEmpresa del viaje:\t" + this.empresaDelViaje;
    }
    
    
    
    
    
}

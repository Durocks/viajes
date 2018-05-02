/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesEInterfaces;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cristolagos
 */
public class AgregadorDeViajes {
    
    public static List<Viaje> agregadorDeViajes(List<Viaje> viajes, List<EmpresaDeViajes> empresasDeViajes){
        viajes.add(new ViajeAereo(0, "First class", "Buenos Aires", "New York",
                new Validator().nuevaFecha("01/20/2018"), "Tarde", new ArrayList(), empresasDeViajes.get(2)));
        viajes.add(new ViajeTerrestre(1, "Semicama", "Buenos Aires", "Rosario",
                new Validator().nuevaFecha("02/25/2018"), "Noche", new ArrayList(), empresasDeViajes.get(0)));
        viajes.add(new ViajeAereo(2, "Economy", "Buenos Aires", "Hong Kong",
                new Validator().nuevaFecha("03/26/2019"), "Noche", new ArrayList(), empresasDeViajes.get(3)));
        viajes.add(new ViajeTerrestre(3, "Cama", "Buenos Aires", "Mar del Plata",
                new Validator().nuevaFecha("01/15/2018"), "Maniana", new ArrayList(), empresasDeViajes.get(1)));
        viajes.add(new ViajeTerrestre(4, "Cama", "Buenos Aires", "Bariloche",
                new Validator().nuevaFecha("01/25/2018"), "Noche", new ArrayList(), empresasDeViajes.get(1)));
        viajes.add(new ViajeAereo(5, "First class", "Buenos Aires", "Rio de Janeiro",
                new Validator().nuevaFecha("01/20/2018"), "Tarde", new ArrayList(), empresasDeViajes.get(2)));
        return viajes;
    }
    
}

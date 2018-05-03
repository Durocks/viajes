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
public class AgregadorDeObjetos {
    
    private static Validator validator = new Validator();
    
    public void agregadorDeViajes(List<Viaje> viajes, List<EmpresaDeViajes> empresasDeViajes){
        viajes.add(new ViajeAereo(0, "First class", "Buenos Aires", "New York",
                validator.nuevaFecha("01/20/2018"), "Tarde", new ArrayList(), empresasDeViajes.get(2), 0, "AE150"));
        empresasDeViajes.get(2).getViajes().add(viajes.get(viajes.size()-1));
        viajes.add(new ViajeTerrestre(1, "Semicama", "Buenos Aires", "Rosario",
                validator.nuevaFecha("02/25/2018"), "Noche", new ArrayList(), empresasDeViajes.get(0), 0));
        empresasDeViajes.get(0).getViajes().add(viajes.get(viajes.size()-1));
        viajes.add(new ViajeAereo(2, "Economy", "Buenos Aires", "Hong Kong",
                validator.nuevaFecha("03/26/2019"), "Noche", new ArrayList(), empresasDeViajes.get(3), 0, "AL930"));
        empresasDeViajes.get(3).getViajes().add(viajes.get(viajes.size()-1));
        viajes.add(new ViajeTerrestre(3, "Cama", "Buenos Aires", "Mar del Plata",
                validator.nuevaFecha("01/15/2018"), "Maniana", new ArrayList(), empresasDeViajes.get(1), 0));
        empresasDeViajes.get(1).getViajes().add(viajes.get(viajes.size()-1));
        viajes.add(new ViajeTerrestre(4, "Cama", "Buenos Aires", "Bariloche",
                validator.nuevaFecha("01/25/2018"), "Noche", new ArrayList(), empresasDeViajes.get(1), 0));
        empresasDeViajes.get(1).getViajes().add(viajes.get(viajes.size()-1));
        viajes.add(new ViajeAereo(5, "First class", "Buenos Aires", "Rio de Janeiro",
                validator.nuevaFecha("01/20/2018"), "Tarde", new ArrayList(), empresasDeViajes.get(2), 0, "PA250"));
        empresasDeViajes.get(2).getViajes().add(viajes.get(viajes.size()-1));
        
        for (Viaje v:viajes){
            v.setPrecioPasaje(validator.calcularPrecio(v.getCategoria(), v.getDestino(), validator.getTipoPasaje(v)));
        }
    }
    
    public void agregadorDePasajeros(List<Pasajero> pasajeros){
        pasajeros.add(new Pasajero(0, 35321542, new Validator().nuevaFecha("01/15/1990"),
                "Julian", "Lopez", 50, "Masculino", new ArrayList()));
        pasajeros.add(new Pasajero(1, 3759374, new Validator().nuevaFecha("05/10/1985"),
                "Jorge", "Perez", 100, "Masculino", new ArrayList()));
        pasajeros.add(new Pasajero(2, 2838475, new Validator().nuevaFecha("10/25/1970"),
                "Marcos", "Atros", 20, "Masculino", new ArrayList()));
        pasajeros.add(new Pasajero(3, 1029384, new Validator().nuevaFecha("03/12/1950"),
                "Roberto", "Velardez", 75, "Masculino", new ArrayList()));
        pasajeros.add(new Pasajero(4, 34938494, new Validator().nuevaFecha("02/23/1988"),
                "Maria", "Pastor", 80, "Femenino", new ArrayList()));
        pasajeros.add(new Pasajero(5, 36828394, new Validator().nuevaFecha("09/28/1992"),
                "Jimena", "Gonzales", 200, "Masculino", new ArrayList()));
    }

    
}

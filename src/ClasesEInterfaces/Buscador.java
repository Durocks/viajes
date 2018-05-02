/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesEInterfaces;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author cristolagos
 */
public class Buscador {
    
    public List<Viaje> buscarViajesAereos (List<Viaje> viajes){
        List<Viaje> viajesAereosEncontrados = new ArrayList();
        for (Viaje v:viajes)
            if (v instanceof ViajeAereo)
                viajesAereosEncontrados.add((ViajeAereo) v);
        return viajesAereosEncontrados;
    }
    
    public List<Viaje> buscarViajesTerrestres (List<Viaje> viajes){
        List<Viaje> viajesTerrestresEncontrados = new ArrayList();
        for (Viaje v:viajes)
            if (v instanceof ViajeTerrestre)
                viajesTerrestresEncontrados.add((ViajeTerrestre) v);
        return viajesTerrestresEncontrados;
    }
    
    public List<Viaje> buscarViajesPorFecha (List<Viaje> viajes, Calendar fecha){
        List<Viaje> viajesDeEsaFecha = new ArrayList();
        for (Viaje v:viajes)
            if (new Validator().compararFechas(v.getFecha(), fecha))
                viajesDeEsaFecha.add(v);
        return viajesDeEsaFecha;
    }
    
    public List<Viaje> buscarViajesPorCiudadDePartida (List<Viaje> viajes, String ciudadDePartida){
        List<Viaje> viajesPorCiudadDePartidaEncontrados = new ArrayList();
        for (Viaje v:viajes)
            if (v.getCiudadDePartida().equals(ciudadDePartida))
                viajesPorCiudadDePartidaEncontrados.add(v);
        return viajesPorCiudadDePartidaEncontrados;
    }
    
    public List<Viaje> buscarViajesPorCiudadDeDestino (List<Viaje> viajes, String ciudadDeDestino){
        List<Viaje> viajesPorCiudadDeDestinoEncontrados = new ArrayList();
        for (Viaje v:viajes)
            if (v.getDestino().equals(ciudadDeDestino))
                viajesPorCiudadDeDestinoEncontrados.add(v);
        return viajesPorCiudadDeDestinoEncontrados;
    }
    
    public List<Viaje> buscarViajesPorCategoria (List<Viaje> viajes, String categoria){
        List<Viaje> viajesPorCategoria = new ArrayList();
        for (Viaje v:viajes)
            if (v.getCategoria().equals(categoria))
                viajesPorCategoria.add(v);
        return viajesPorCategoria;
    }
    
    public List<Viaje> buscarViajesPorFranjaHoraria (List<Viaje> viajes, String franjaHoraria){
        List<Viaje> viajesPorFranjaHoraria = new ArrayList();
        for (Viaje v:viajes)
            if (v.getFranjaHoraria().equals(franjaHoraria))
                viajesPorFranjaHoraria.add(v);
        return viajesPorFranjaHoraria;
    }
    
    public List<Viaje> buscarViajesPorCantidadDePasajes (List<Viaje> viajes, int cantidadDeseada){
        List<Viaje> viajesSegunCantidadDePasajes = new ArrayList();
        for (Viaje v:viajes)
            if (v.lugaresDisponibles() >= cantidadDeseada)
                viajesSegunCantidadDePasajes.add(v);
        return viajesSegunCantidadDePasajes;
    }
}

/*
 * To change v license header, choose License Headers in Project Properties.
 * To change v template file, choose Tools | Templates
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
            if (v.getCiudadDePartida().toLowerCase().equals(ciudadDePartida.toLowerCase()))
                viajesPorCiudadDePartidaEncontrados.add(v);
        return viajesPorCiudadDePartidaEncontrados;
    }
    
    public List<Viaje> buscarViajesPorCiudadDeDestino (List<Viaje> viajes, String ciudadDeDestino){
        List<Viaje> viajesPorCiudadDeDestinoEncontrados = new ArrayList();
        for (Viaje v:viajes)
            if (v.getDestino().toLowerCase().equals(ciudadDeDestino.toLowerCase()))
                viajesPorCiudadDeDestinoEncontrados.add(v);
        return viajesPorCiudadDeDestinoEncontrados;
    }
    
    public List<Viaje> buscarViajesPorCategoria (List<Viaje> viajes, String categoria){
        List<Viaje> viajesPorCategoria = new ArrayList();
        for (Viaje v:viajes)
            if (v.getCategoria().toLowerCase().equals(categoria.toLowerCase()))
                viajesPorCategoria.add(v);
        return viajesPorCategoria;
    }
    
    public List<Viaje> buscarViajesPorFranjaHoraria (List<Viaje> viajes, String franjaHoraria){
        List<Viaje> viajesPorFranjaHoraria = new ArrayList();
        for (Viaje v:viajes)
            if (v.getFranjaHoraria().toLowerCase().equals(franjaHoraria.toLowerCase()))
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
    
    public List<Viaje> buscarViajesPorEmpresaDelViaje (List<Viaje> viajes, EmpresaDeViajes empresaDeViajes){
        List<Viaje> viajesSegunEmpresaDelViaje = new ArrayList();
        for (Viaje v:viajes)
            if (v.getEmpresaDelViaje() == empresaDeViajes)
                viajesSegunEmpresaDelViaje.add(v);
        return viajesSegunEmpresaDelViaje;
    }
    
    public void printViajesCompatibles(List<Viaje> viajes){
        for (Viaje v:viajes)
            System.out.println(v.toString());
            System.out.println("-------------------------------------");
    }

    public void printShortViajesCompatibles(List<Viaje> viajes) {
        for (Viaje v:viajes)
            if (v.lugaresDisponibles() > 0){
                System.out.println("Viaje\t" + v.id + "\tTrajecto: " + v.getTrajecto() +
                        "\tPrecio del pasaje: " + v.getPrecioPasaje() + " $" +
                                "\tEmpresa del viaje: " + v.empresaDelViaje.getNombre());
            }
            else
                System.out.println("El viaje consultado no posee lugares disponibles.");
            System.out.println("-------------------------------------");
    }

    public List<Viaje> buscarPorAsiento(List<Viaje> viajes, int idAsiento) {
        List<Viaje> viajesSegunAsiento = new ArrayList();
        for (Viaje v:viajes)
            if (v.getAsientos().get(idAsiento).isOcupado() == false)
                viajesSegunAsiento.add(v);
        return viajesSegunAsiento;
    }

    public List<Viaje> buscarPorId(List<Viaje> viajes, int id) {
        List<Viaje> viajesSegunId = new ArrayList();
        for (Viaje v:viajes)
            if (v.getId() == id)
                viajesSegunId.add(v);
        return viajesSegunId;
    }
}

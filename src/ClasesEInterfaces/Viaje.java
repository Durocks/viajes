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
public abstract class Viaje  implements getId{
    protected int id;
    protected String categoria;
    protected String ciudadDePartida;
    protected String destino;
    protected Calendar fecha;
    protected String franjaHoraria;
    protected final List<Pasajero> pasajeros;
    protected final List<Asiento> asientos;
    protected EmpresaDeViajes empresaDelViaje;
    protected double precioPasaje;

    public Viaje() {
        this.asientos = new ArrayList();
        this.pasajeros = new ArrayList();
        this.agregadorDeAsientos();
    }
    
    protected abstract void agregadorDeAsientos();

    @Override
    public abstract String toString();
    
    public int lugaresDisponibles(){
        int lugaresDisponibles = 0;
        for (Asiento a:this.getAsientos())
            if (a.isOcupado() == false)
                lugaresDisponibles ++;
        return lugaresDisponibles;
    }
    
    public Viaje(int id, String categoria, String ciudadDePartida, String destino, Calendar fecha, String franjaHoraria,
        List<Pasajero> pasajeros, EmpresaDeViajes empresaDelViaje, double precioPasaje) {
        this.asientos = new ArrayList();
        this.pasajeros = pasajeros;
        this.id = id;
        this.categoria = categoria;
        this.ciudadDePartida = ciudadDePartida;
        this.destino = destino;
        this.fecha = fecha;
        this.franjaHoraria = franjaHoraria;
        this.empresaDelViaje = empresaDelViaje;
        this.precioPasaje = precioPasaje;
        this.agregadorDeAsientos();
    }

    public EmpresaDeViajes getEmpresaDelViaje() {
        return empresaDelViaje;
    }

    public void setEmpresaDelViaje(EmpresaDeViajes empresaDelViaje) {
        this.empresaDelViaje = empresaDelViaje;
    }
    

    public int getId() {
        return id;
    }

    public void setPrecioPasaje(double precioPasaje) {
        this.precioPasaje = precioPasaje;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCiudadDePartida() {
        return ciudadDePartida;
    }

    public String getDestino() {
        return destino;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public String getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCiudadDePartida(String ciudadDePartida) {
        this.ciudadDePartida = ciudadDePartida;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public void setFranjaHoraria(String franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }

    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public String getTrajecto() {
        return this.ciudadDePartida + " a " + this.destino;
    }

    public double getPrecioPasaje() {
        return precioPasaje;
    }
    
    
}

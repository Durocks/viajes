package guia.pkg5.cristobal.lagos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ClasesEInterfaces.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Cristobal Lagos
 */
public class Main {

    private static final Scanner std = new Scanner(System.in);
    private static final List<Viaje> viajes = new ArrayList();
    private static final List<Pasaje> pasajes = new ArrayList();
    private static final List<Pasajero> pasajeros = new ArrayList();
    private static final List<EmpresaDeViajes> empresasDeViajes = new ArrayList();
    private static final List<Asiento> asientos = new ArrayList();

    public static void main(String[] args) {
        initValues();
        int opcion = -1;
        while (opcion != 0){
            System.out.println("Ingrese la opcion:\n"
                    + "1 - Agregar los detalles de una venta");
            opcion = new Validator().validacionInt();
            switch (opcion){
                case 1:
                    agregarVenta();
                    break;
                default:
                    break;
            }
        }
    }

    private static void initValues() {
        empresasDeViajes.add(new EmpresaDeViajes(0, "FlechaBus", 381461327));
        empresasDeViajes.add(new EmpresaDeViajes(1, "Niandu del Sur", 2147389247));
        empresasDeViajes.add(new EmpresaDeViajes(2, "American Airlines", 738921393));
        empresasDeViajes.add(new EmpresaDeViajes(3, "LAN", 478232193));
        empresasDeViajes.add(new EmpresaDeViajes(4, "Abeja Bus", 917223493));

        new AgregadorDeViajes().agregadorDeViajes(viajes, empresasDeViajes);
    }

    private static void agregarVenta() {
        Buscador buscador = new Buscador();
        List <Viaje> viajesCompatibles = viajes;
        System.out.println("Pasajes disponibles: ");
        buscador.printViajesCompatibles(viajesCompatibles);
        String tipoDePasaje = "";
        while (viajesCompatibles.size() > 1 && 
                !tipoDePasaje.toLowerCase().equals("aereo") && !tipoDePasaje.toLowerCase().equals("terrestre")){
            System.out.print("Se trata de un pasaje aereo o terrestre?: ");
            tipoDePasaje = std.nextLine();
        }
        if (viajesCompatibles.size() > 1 && tipoDePasaje.toLowerCase().equals("aereo")){
            viajesCompatibles = buscador.buscarViajesAereos(viajesCompatibles);
            buscador.printViajesCompatibles(viajesCompatibles);
        }
        if (viajesCompatibles.size() > 1 && tipoDePasaje.toLowerCase().equals("terrestre")){
            viajesCompatibles = buscador.buscarViajesTerrestres(viajesCompatibles);
            buscador.printViajesCompatibles(viajesCompatibles);
        }
        Calendar fechaPartida;
        if (viajesCompatibles.size() > 1){
            System.out.println("Ingrese la fecha de partida: ");
            fechaPartida = new Validator().validacionFecha();
            viajesCompatibles = buscador.buscarViajesPorFecha(viajesCompatibles, fechaPartida);
            buscador.printViajesCompatibles(viajesCompatibles);
        }

        if (viajesCompatibles.size() > 1){
            System.out.print("Ingrese la ciudad de partida: ");
            String ciudadDePartida = std.nextLine();
            viajesCompatibles = buscador.buscarViajesPorCiudadDePartida(viajesCompatibles, ciudadDePartida);
            buscador.printViajesCompatibles(viajesCompatibles);
        }
        if (viajesCompatibles.size() > 1){
            System.out.print("Ingrese la ciudad de destino: ");
            String ciudadDeDestino = std.nextLine();
            viajesCompatibles = buscador.buscarViajesPorCiudadDeDestino(viajesCompatibles, ciudadDeDestino);
            buscador.printViajesCompatibles(viajesCompatibles);
        }
        if (viajesCompatibles.size() > 1){
            System.out.print("Ingrese la categoria deseada: ");
            String categoria = std.nextLine();
            viajesCompatibles = buscador.buscarViajesPorCategoria(viajesCompatibles, categoria);
            buscador.printViajesCompatibles(viajesCompatibles);
        }
        String franjaHoraria = "";
        if (viajesCompatibles.size() > 1){
            while (!franjaHoraria.toLowerCase().equals("maniana") && !franjaHoraria.toLowerCase().equals("tarde")
            && !franjaHoraria.toLowerCase().equals("noche")){
                System.out.print("Ingrese la franja horaria (Maniana/Tarde/Noche): ");
                franjaHoraria = std.nextLine();
            }
            viajesCompatibles = buscador.buscarViajesPorFranjaHoraria(viajesCompatibles, franjaHoraria);
            buscador.printViajesCompatibles(viajesCompatibles);
        }
        if (viajesCompatibles.isEmpty()){
            System.out.println("No hay ningun viaje que coincida con esos requisitos.");
            System.out.println("-----------------------------------------------------");
        }
        if (viajesCompatibles.size() > 1){
            while (viajesCompatibles.size() > 1){
                System.out.println("Hay mas de un viaje que coincide con esas caracteristicas.\n"
                        + "Por favor ingrese el ID del viaje que mejor se adecue a su pasaje");
                int id = new Validator().validacionInt();
                for (Viaje v:viajesCompatibles){
                    if (id == v.getId()){
                        Viaje viaje = v;
                        viajesCompatibles.clear();
                        viajesCompatibles.add(viaje);
                    }
                }
            }
        }
        if (viajesCompatibles.size() == 1){
            System.out.print("La cantidad de opciones se ha reducido a una sola. Desea tomarla? (Si/No): ");
            if (new Validator().validacionSiNo()){
                int cantidadDePasajes = 0;
                while (cantidadDePasajes < 1 || cantidadDePasajes > viajesCompatibles.get(0).lugaresDisponibles()){
                    System.out.print("Ingrese la cantidad de pasajes: ");
                    cantidadDePasajes = new Validator().validacionInt();
                }
                if (viajesCompatibles.get(0) instanceof ViajeAereo)
                    pasajes.add(new PasajeAereo(maxIdMasUno((List<Object>)(Object)pasajes), seleccionarAsiento(viajesCompatibles.get(0)), 1, null, viajesCompatibles.get(0), null));
                if (viajesCompatibles.get(0) instanceof ViajeTerrestre)
                    pasajes.add(new PasajeTerrestre(maxIdMasUno((List<Object>)(Object)pasajes), seleccionarAsiento(viajesCompatibles.get(0)), 1, null, viajesCompatibles.get(0), null));
            }
            else
                System.out.println("La reserva del pasaje ha sido cancelada.");
        }
        


    }

    /**
     * 
     *  Metodo razonado por Cristobal Lagos.
     *  Funciona de forma generica para cualquier objeto que implemente a la interfaz "getId".
     */

    private static int maxIdMasUno(List<Object> object){
        int max = 0;
        for (Object o : object) {
            if (o instanceof getId)
                if (((getId)o).getId() >= max)
                    max = ((getId)o).getId() + 1;
        }
        return max;
    }

    private static Asiento seleccionarAsiento(Viaje viaje) {
        Asiento asientoDeseado = null;
        System.out.println("Los asientos disponibles son: ");
        for (Asiento a:viaje.getAsientos()){
            if (a.isOcupado() == false);
                System.out.println(a.toString());
        }
        while (asientoDeseado == null){
            System.out.print("Ingrese el nombre o descripcion del asiento deseado: ");
            String descripcion = std.nextLine().toLowerCase();
            for (Asiento a:viaje.getAsientos()){
                if (a.getDescripcion().toLowerCase().equals(descripcion) && a.isOcupado() == false){
                    asientoDeseado = a;
                    a.setOcupado(true);
                }
                if (a.getDescripcion().toLowerCase().equals(descripcion) && a.isOcupado() == true){
                    System.out.println("Ese asiento esta ocupado.");
                }
            }
        }
        return asientoDeseado;
    }

}

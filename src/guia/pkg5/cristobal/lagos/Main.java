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
        
        viajes.addAll(AgregadorDeViajes.agregadorDeViajes(viajes, empresasDeViajes));
        for (Viaje v:viajes)
            System.out.println(v.toString());
    }

    private static void agregarVenta() {
        Buscador buscador = new Buscador();
        List <Viaje> viajesCompatibles = new ArrayList();
        for (Viaje v:viajes) System.out.println(v.toString());
        System.out.println("-------------------------------------");
        String tipoDePasaje = "";
        while (!tipoDePasaje.toLowerCase().equals("aereo") && !tipoDePasaje.toLowerCase().equals("terrestre")){
            System.out.print("Se trata de un pasaje aereo o terrestre?: ");
            tipoDePasaje = std.nextLine();
        }
        if (tipoDePasaje.toLowerCase().equals("aereo")){
            viajesCompatibles = buscador.buscarViajesAereos(viajes);
            for (Viaje v:viajesCompatibles) System.out.println(v.toString());
            System.out.println("-------------------------------------");
        }
        if (tipoDePasaje.toLowerCase().equals("terrestre")){
            viajesCompatibles = buscador.buscarViajesTerrestres(viajes);
            for (Viaje v:viajesCompatibles) System.out.println(v.toString());
            System.out.println("-------------------------------------");
        }
        Calendar fechaPartida = GregorianCalendar.getInstance();
        System.out.println("Ingrese la fecha de partida: ");
        fechaPartida = new Validator().validacionFecha();
        viajesCompatibles = buscador.buscarViajesPorFecha(viajesCompatibles, fechaPartida);
        for (Viaje v:viajesCompatibles) System.out.println(v.toString());
        System.out.println("-------------------------------------");
        System.out.print("Ingrese la ciudad de partida: ");
        String ciudadDePartida = std.nextLine();
        viajesCompatibles = buscador.buscarViajesPorCiudadDePartida(viajesCompatibles, ciudadDePartida);
        for (Viaje v:viajesCompatibles) System.out.println(v.toString());
        System.out.println("-------------------------------------");
        System.out.print("Ingrese la ciudad de destino: ");
        String ciudadDeDestino = std.nextLine();
        viajesCompatibles = buscador.buscarViajesPorCiudadDeDestino(viajesCompatibles, ciudadDeDestino);
        for (Viaje v:viajesCompatibles) System.out.println(v.toString());
        System.out.println("-------------------------------------");
        System.out.print("Ingrese la categoria deseada: ");
        String categoria = std.nextLine();
        viajesCompatibles = buscador.buscarViajesPorCategoria(viajesCompatibles, categoria);
        for (Viaje v:viajesCompatibles) System.out.println(v.toString());
        System.out.println("-------------------------------------");
        String franjaHoraria = "";
        while (!franjaHoraria.toLowerCase().equals("maniana") && !franjaHoraria.toLowerCase().equals("tarde")
        && !franjaHoraria.toLowerCase().equals("noche")){
            System.out.print("Ingrese la franja horaria (Maniana/Tarde/Noche): ");
            franjaHoraria = std.nextLine();
        }
        viajesCompatibles = buscador.buscarViajesPorFranjaHoraria(viajesCompatibles, franjaHoraria);
        for (Viaje v:viajesCompatibles) System.out.println(v.toString());
        System.out.println("-------------------------------------");
        System.out.print("Ingrese la cantidad de pasajes: ");
        int cantidadDePasajes = new Validator().validacionInt();
        viajesCompatibles = buscador.buscarViajesPorCantidadDePasajes(viajesCompatibles, cantidadDePasajes);
        for (Viaje v:viajesCompatibles) System.out.println(v.toString());
        System.out.println("-------------------------------------");
        
        
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
    
//    private static Asiento agregarAsiento(){
//        System.out.print("Ingrese la descripcion del asiento (Ejemplo: 3B): ");
//        String descripcion = std.nextLine();
//        boolean agregarTratamientoEspecial = true;
//        List <String> tratamientosEspeciales = new ArrayList();
//        while (agregarTratamientoEspecial == true){
//            System.out.print("Desea agregar un tratamiento especial (Si/No): ");
//            agregarTratamientoEspecial = new Validator().validacionSiNo();
//            if (agregarTratamientoEspecial == true){
//                System.out.print("Ingrese la descripcion del tratamiento especial: ");
//                tratamientosEspeciales.add(std.nextLine());
//            }
//        }
//        asientos.add(new Asiento(maxIdMasUno((List<Object>)(Object)asientos), descripcion, tratamientosEspeciales));
//        return asientos.get(asientos.size()-1);
//        
//    }
    
}

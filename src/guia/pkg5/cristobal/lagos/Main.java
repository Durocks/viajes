package guia.pkg5.cristobal.lagos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ClasesEInterfaces.*;
import java.text.DecimalFormat;
import java.util.Calendar;

/**
 *
 * @author Cristobal Lagos
 */
public class Main {

    private static final Buscador buscador = new Buscador();
    private static final Validator validator = new Validator();
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
            System.out.println("-------------------------------------------------------");
            System.out.println("Ingrese la opcion:\n"
                    + "1 - Agregar los detalles de una venta\n"
                    + "2 - Devolver o modificar un pasaje\n"
                    + "0 - Terminar");
            opcion = validator.validacionInt();
            switch (opcion){
                case 1:
                    agregarVenta();
                    break;
                case 2:
                    modificarPasaje();
                case 0:
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

        new AgregadorDeObjetos().agregadorDeViajes(viajes, empresasDeViajes);
        new AgregadorDeObjetos().agregadorDePasajeros(pasajeros);
    }

    private static void agregarVenta() {
        System.out.println("-------------------------------------------------------");
        List <Viaje> viajesCompatibles = viajes;
        List<Viaje> backUpViajesCompatibles = new ArrayList();
        System.out.println("Viajes disponibles: ");
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
            fechaPartida = validator.validacionFecha();
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
            backUpViajesCompatibles = viajesCompatibles;
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
            System.out.println("No hay ningun viaje disponible actualmente que coincida con esos requisitos.");
            System.out.println("-------------------------------------------------------");
            if (!backUpViajesCompatibles.isEmpty()){
                System.out.print("Desea seleccionar un viaje similar de categoria o franja horaria distinta? (Si/No): ");
                if (validator.validacionSiNo())
                    while (viajesCompatibles.size() != 1){
                        buscador.printViajesCompatibles(backUpViajesCompatibles);
                        int id = validator.validacionInt();
                        for (Viaje v:backUpViajesCompatibles)
                            if (v.getId() == id)
                                viajesCompatibles.add(v);
                    }
            }
        }
        if (viajesCompatibles.size() > 1){
            while (viajesCompatibles.size() > 1){
                buscador.printShortViajesCompatibles(viajesCompatibles);
                System.out.println("Hay mas de un viaje que coincide con esas caracteristicas.\n"
                        + "Por favor ingrese el ID del viaje que mejor se adecue a su pasaje");
                int id = validator.validacionInt();
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
            if (validator.validacionSiNo()){
                int cantidadDePasajes = 0;
                while (cantidadDePasajes < 1 || cantidadDePasajes > viajesCompatibles.get(0).lugaresDisponibles()){
                    System.out.print("Ingrese la cantidad de pasajes: ");
                    cantidadDePasajes = validator.validacionInt();
                }
                for (int i = 0; i<cantidadDePasajes; i++){
                if (viajesCompatibles.get(0) instanceof ViajeAereo)
                    pasajes.add(new PasajeAereo(maxIdMasUno((List<Object>)(Object)pasajes), seleccionarAsiento(viajesCompatibles.get(0)), seleccionarPasajero(), viajesCompatibles.get(0), seleccionarTratamientoEspecial()));
                if (viajesCompatibles.get(0) instanceof ViajeTerrestre)
                    pasajes.add(new PasajeTerrestre(maxIdMasUno((List<Object>)(Object)pasajes), seleccionarAsiento(viajesCompatibles.get(0)), seleccionarPasajero(), viajesCompatibles.get(0), seleccionarTratamientoEspecial()));
                System.out.println("\nPasaje confirmado: ");
                buscador.printShortViajesCompatibles(viajesCompatibles);
                }
                pagarPasaje(pasajes.get(pasajes.size()-1), cantidadDePasajes);
                for (int i = cantidadDePasajes; i > 0; i--){
                    System.out.println(pasajes.get(pasajes.size()-i).toStringCompleto());
                }
            }
            else
                System.out.println("La reserva del pasaje ha sido cancelada.");
        }
    }
    
    private static Pasajero seleccionarPasajero(){
        String nombre;
        String apellido;
        int dni;
        System.out.print("Ingrese el nombre y apellido, o DNI del pasajero: ");
        String valorIngresado = std.nextLine();
        Pasajero pasajeroBuscado = null;
        for (Pasajero p:pasajeros){
            if ((p.getNombre() + " "+ p.getApellido()).toLowerCase().matches(valorIngresado.toLowerCase())
                    || (p.getDocumento()+"").matches(valorIngresado))
                pasajeroBuscado = p;
        }
        if (pasajeroBuscado != null){
            System.out.println(pasajeroBuscado.toString());
        }
        else{
            System.out.println("\nEl pasajero no se encuentra en la nomina de pasajeros.\n"
                    + "Por favor agregue los datos restantes del pasajero.\n");
            System.out.print("Ingrese el DNI: ");
            dni = validator.validacionInt();
            System.out.print("Ingrese el nombre: ");
            nombre = std.nextLine();
            System.out.print("Ingrese el Apellido: ");
            apellido = std.nextLine();
            System.out.print("Ingrese el sexo: ");
            String sexo = std.nextLine();
            System.out.println("Ingrese la fecha de nacimiento: ");
            Calendar fechaNacimiento = validator.validacionFecha();
            pasajeroBuscado = new Pasajero(maxIdMasUno((List<Object>)(Object)pasajeros), dni, fechaNacimiento, nombre, apellido, 0, sexo, pasajes, false);            
            System.out.println(pasajeroBuscado.toString());
        }
        return pasajeroBuscado;
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
                if (a.getDescripcion().toLowerCase().equals(descripcion) && a.isOcupado() == true){
                    System.out.println("Ese asiento esta ocupado.");
                }
                if (a.getDescripcion().toLowerCase().equals(descripcion) && a.isOcupado() == false){
                    asientoDeseado = a;
                    a.setOcupado(true);
                }
            }
        }
        return asientoDeseado;
    }

    private static boolean seleccionarTratamientoEspecial() {
        System.out.print("Requiere tratamiento especial? (Si/No): ");
        return validator.validacionSiNo();
    }

    private static void pagarPasaje(Pasaje pasaje, int cantidad) {
        double totalAPagar = pasaje.getViaje().getPrecioPasaje() * cantidad;
        String medioDePago = validator.validarMedioDePago();
        System.out.print("Desea usar puntos? (Si/No): ");
        boolean usarPuntos = validator.validacionSiNo();
        if (medioDePago.toLowerCase().matches("tarjeta"))
            totalAPagar = totalAPagar * 1.15;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        if (usarPuntos == true){
            String fraccion = "";
            while (!fraccion.toLowerCase().matches("mitad") && !fraccion.toLowerCase().matches("todo")){
                System.out.print("Desea pagar todo con puntos o solo la mitad? (Mitad/Todo): ");
                fraccion = std.nextLine();
            }
            // Suponiendo que cada punto sean 50 pesos
            int puntosGastados = 0, totalMinimo = 0;
            if (fraccion.matches("todo"))
                totalMinimo = 0;
            else
                totalMinimo = (int) (totalAPagar/2);
            while (pasaje.getPasajero().getPuntos() > 0 && totalAPagar > totalMinimo){
                totalAPagar = totalAPagar - 50;
                pasaje.getPasajero().setPuntos(pasaje.getPasajero().getPuntos()-1);
                puntosGastados ++;
            }
            if (totalAPagar < 0)
                totalAPagar = 0;
            System.out.println("Se han usado " + puntosGastados + " puntos, y resta pagar " +
                df.format(totalAPagar) + " $.");
            System.out.println("Restan " + pasaje.getPasajero().getPuntos() + " puntos en la cuenta.");
        }
        System.out.println("Presione ENTER cuando el cliente haya hecho el pago...");
        std.nextLine();
        int puntos = 0;
        for (int i = (int)totalAPagar; i > 0; i = i-150)
            if ((i-150) > 0)
                puntos++;
        System.out.println("Se han pagado " + df.format(totalAPagar)+ " $, y se le han sumado "
                + puntos + " puntos.");
        System.out.println("-------------------------------------------------------");
    }

    private static void modificarPasaje() {
        System.out.println("-------------------------------------------------------");
        Pasaje pasaje = null;
        if (pasajes.isEmpty()){
            System.out.println("No hay pasajes vendidos aun.");
            return;
        }
        else
            for(Pasaje p:pasajes)
                System.out.println(p.toString());
        while (pasaje == null){
            System.out.print("Ingrese el numero de pasaje: ");
            int idPasaje = validator.validacionInt();
            for (Pasaje p:pasajes)
                if (p.getId() == idPasaje)
                    pasaje = p;
        }
        String opcion = "";
        while (!opcion.toLowerCase().matches("devolverlo") && !opcion.toLowerCase().matches("modificarlo")){
            System.out.print("Que desea hacer con el pasaje? (Devolverlo/Modificarlo): ");
            opcion = std.nextLine();
        }
        if (opcion.toLowerCase().matches("devolverlo")){
            System.out.print("Se le cobrara una multa del 50% del precio del pasaje.\nDesea continuar (Si/No): ");
            if (validator.validacionSiNo()){
                System.out.println("La multa a pagar es de " + pasaje.getViaje().getPrecioPasaje() * 0.5 +
                        " $\nPor favor pague ahora al cajero.");
                System.out.println("Presione ENTER cuando el cliente haya pagado.");
                std.nextLine();
                pasaje.getAsiento().setOcupado(false);
                pasajes.remove(pasaje);
                System.out.println("Su pasaje ha sido dado de baja. Muchas gracias.");
            }
        }
        if (opcion.toLowerCase().matches("modificarlo")){
            Viaje viajeOriginal = pasaje.getViaje();
            String modificar = "";
            while (!modificar.toLowerCase().matches("fecha") && !modificar.toLowerCase().matches("asiento")
                    && !modificar.toLowerCase().matches("franja horaria")){
                System.out.print("Que desea modificar? (Fecha/Asiento/Franja horaria): ");
                modificar = std.nextLine();
            }
            if (modificar.toLowerCase().matches("fecha")){
                System.out.print("Ingrese la fecha deseada: ");
                Calendar fecha = validator.validacionFecha();
                List<Viaje> viajesPosibles = viajes;
                    if (pasaje.getViaje() instanceof ViajeAereo)
                        viajesPosibles = buscador.buscarViajesAereos(viajesPosibles);
                    if (pasaje.getViaje() instanceof ViajeTerrestre)
                        viajesPosibles = buscador.buscarViajesTerrestres(viajesPosibles);
                    
                        viajesPosibles = buscador.buscarViajesPorCategoria(viajesPosibles, pasaje.getViaje().getCategoria());
                        viajesPosibles = buscador.buscarViajesPorCiudadDePartida(viajesPosibles, pasaje.getViaje().getCiudadDePartida());
                        viajesPosibles = buscador.buscarViajesPorCiudadDeDestino(viajesPosibles, pasaje.getViaje().getDestino());
                        viajesPosibles = buscador.buscarViajesPorEmpresaDelViaje(viajesPosibles, pasaje.getViaje().getEmpresaDelViaje());
                        viajesPosibles = buscador.buscarViajesPorFranjaHoraria(viajesPosibles, pasaje.getViaje().getFranjaHoraria());
                        viajesPosibles = buscador.buscarPorAsiento(viajesPosibles, pasaje.getAsiento().getId());
                        viajesPosibles = buscador.buscarViajesPorFecha(viajesPosibles, fecha);
                        if (viajesPosibles.isEmpty()){
                            System.out.println("No se encontro ningun otro viaje que coincida con todos los "
                                    + "parametros anteriormente agregados y con la fecha nueva.");
                            return;
                        }
                        else{
                            buscador.printViajesCompatibles(viajesPosibles);
                            System.out.print("Ingrese el id del viaje al que le gustaria cambiarse: ");
                            viajesPosibles = buscador.buscarPorId(viajesPosibles, validator.validacionInt());
                            if (viajesPosibles.isEmpty())
                                System.out.println("Ese id no coincide con el de ninguno de los viajes ofrecidos");
                            else{
                                int idAsiento = pasaje.getAsiento().getId();
                                pasaje.getAsiento().setOcupado(false);
                                pasaje.setViaje(viajesPosibles.get(0));
                                pasaje.setAsiento(pasaje.getViaje().getAsientos().get(idAsiento));
                                pasaje.getAsiento().setOcupado(true);
                            }
                        }
            
            if (pasaje.getViaje() == viajeOriginal)
                System.out.println("La fecha no pudo ser cambiada, ya que no hay pasajes disponibles que cumplan con sus"
                        + "condiciones. Intente nuevamente con otros parametros.");
            else
                System.out.println("La fecha ha sido cambiada exitosamente!");
            } // Fin del if de cambio segun fecha
            if (modificar.toLowerCase().matches("asiento")){
                pasaje.getAsiento().setOcupado(false);
                seleccionarAsiento(pasaje.getViaje());
            }
            if (modificar.toLowerCase().matches("franja horaria")){
                String franjaHoraria = "";
                while (!franjaHoraria.toLowerCase().matches("maniana") && !franjaHoraria.toLowerCase().matches("tarde") &&
                        !franjaHoraria.toLowerCase().matches("noche")){
                    System.out.print("Ingrese la franja horaria deseada (Maniana/Tarde/Noche): ");
                    franjaHoraria = std.nextLine();
                }
                
                List<Viaje> viajesPosibles = viajes;
                viajesPosibles = buscador.buscarPorAsiento(viajesPosibles, pasaje.getAsiento().getId());
                if (pasaje.getViaje() instanceof ViajeAereo)
                    viajesPosibles = buscador.buscarViajesAereos(viajesPosibles);
                if (pasaje.getViaje() instanceof ViajeTerrestre)
                    viajesPosibles = buscador.buscarViajesTerrestres(viajesPosibles);
                viajesPosibles = buscador.buscarViajesPorCategoria(viajesPosibles, pasaje.getViaje().getCategoria());
                viajesPosibles = buscador.buscarViajesPorCiudadDePartida(viajesPosibles, pasaje.getViaje().getCiudadDePartida());
                viajesPosibles = buscador.buscarViajesPorCiudadDeDestino(viajesPosibles, pasaje.getViaje().getDestino());
                viajesPosibles = buscador.buscarViajesPorEmpresaDelViaje(viajesPosibles, pasaje.getViaje().getEmpresaDelViaje());
                viajesPosibles = buscador.buscarViajesPorFecha(viajesPosibles, pasaje.getViaje().getFecha());
                viajesPosibles = buscador.buscarViajesPorFranjaHoraria(viajesPosibles, franjaHoraria);
                
                if (!viajesPosibles.isEmpty()){
                    buscador.printViajesCompatibles(viajesPosibles);
                            System.out.print("Ingrese el id del viaje al que le gustaria cambiarse: ");
                            viajesPosibles = buscador.buscarPorId(viajesPosibles, validator.validacionInt());
                            if (viajesPosibles.isEmpty())
                                System.out.println("Ese id no coincide con el de ninguno de los viajes ofrecidos");
                            else{
                                int idAsiento = pasaje.getAsiento().getId();
                                pasaje.getAsiento().setOcupado(false);
                                pasaje.setViaje(viajesPosibles.get(0));
                                pasaje.setAsiento(pasaje.getViaje().getAsientos().get(idAsiento));
                                pasaje.getAsiento().setOcupado(true);
                            }
                }
                else
                    System.out.println("Ninguno de los viajes disponibles coincide con sus parametros.");
            }
        }
    }
}

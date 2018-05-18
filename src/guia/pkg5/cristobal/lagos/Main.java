package guia.pkg5.cristobal.lagos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ClasesEInterfaces.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Cristobal Lagos
 */
public class Main {

    private static Vendedor vendedor = new Vendedor();
    private static final Buscador buscador = new Buscador();
    private static final Validator validator = new Validator();
    private static final Scanner std = new Scanner(System.in);
    private static final List<Viaje> viajes = new ArrayList();
    private static final List<Pasaje> pasajes = new ArrayList();
    private static final List<Pasajero> pasajeros = new ArrayList();
    private static final List<EmpresaDeViajes> empresasDeViajes = new ArrayList();
    private static final List<Asiento> asientos = new ArrayList();
    private static final List<Venta> ventas = new ArrayList();
    private static final List<Vendedor> vendedores = new ArrayList();

    public static void main(String[] args) {
        initValues();
        int opcion = -1;
        while (opcion != 0){
            System.out.println("-------------------------------------------------------");
            System.out.println("Ingrese la opcion:\n"
                    + "1 - Agregar los detalles de una venta\n"
                    + "2 - Devolver o modificar un pasaje\n"
                    + "3 - Generar archivo de empresas\n"
                    + "4 - Generar informe del año\n"
                    + "5 - Ventas segun vendedor, que superan el argumento\n"
                    + "6 - Cambiar de vendedor activo\n"
                    + "7 - Generar informe de puntos\n"
                    + "8 - Mostrar los números de ventas de los pasajes aéreos que no\n" +
                            "hayan realizado escala en alguna de las tres localidades\n"
                    + "0 - Terminar");
            opcion = validator.validacionInt();
            switch (opcion){
                case 1:
                    agregarVenta();
                    break;
                case 2:
                    modificarPasaje();
                    break;
                case 3:
                    generarArchivoEmpresas();
                    break;
                case 4:
                    generarInformeAnio();
                    break;
                case 5:
                    ventasArgumento(args);
                    break;
                case 6:
                    elegirVendedor();
                    break;
                case 7:
                    generarInformePuntos();
                    break;
            case 8:
                mostrarPorPantalla();
                break;
                case 0:
                    break;
                default:
                    break;
            }
        }
        std.close();
    }

    private static void initValues() {
        elegirVendedor();       
        
        empresasDeViajes.add(new EmpresaDeViajes(0, "FlechaBus", 381461327));
        empresasDeViajes.add(new EmpresaDeViajes(1, "Niandu del Sur", 2147389247));
        empresasDeViajes.add(new EmpresaDeViajes(2, "American Airlines", 738921393));
        empresasDeViajes.add(new EmpresaDeViajes(3, "LAN", 478232193));
        empresasDeViajes.add(new EmpresaDeViajes(4, "Abeja Bus", 917223493));

        new AgregadorDeObjetos().agregadorDeViajes(viajes, empresasDeViajes);
        new AgregadorDeObjetos().agregadorDePasajeros(pasajeros);
        actualizarArchivos();
    }

    private static void agregarVenta() {
        ventas.add(new Venta(maxIdMasUno((List<Object>)(Object)ventas), vendedor));
        System.out.println("-------------------------------------------------------");
        List <Viaje> viajesCompatibles = viajes;
        
        String tipoDePasaje = "";
        while (viajesCompatibles.size() > 1 && 
                !tipoDePasaje.toLowerCase().equals("aereo") && !tipoDePasaje.toLowerCase().equals("terrestre")){
            System.out.print("Se trata de un pasaje aereo o terrestre?: ");
            tipoDePasaje = std.nextLine();
        }
        
        int cantidadDePasajes = 0;
        while (cantidadDePasajes < 1 || cantidadDePasajes > viajesCompatibles.get(0).lugaresDisponibles()){
            System.out.print("Ingrese la cantidad de pasajes: ");
            cantidadDePasajes = validator.validacionInt();
        }
        for (int i = 0; i<cantidadDePasajes; i++){
            viajesCompatibles = viajes;
            if (cantidadDePasajes > 1)
                System.out.println("\nComenzaremos la adicion del pasaje " + (i+1) + ": ");
            if (tipoDePasaje.toLowerCase().matches("aereo")){         
                pasajes.add(new PasajeAereo(maxIdMasUno((List<Object>)(Object)pasajes), seleccionarPasajero()));
                viajesCompatibles = buscador.buscarViajesAereos(viajesCompatibles);
            }
            else {
                pasajes.add(new PasajeTerrestre(maxIdMasUno((List<Object>)(Object)pasajes), seleccionarPasajero()));
                viajesCompatibles = buscador.buscarViajesTerrestres(viajesCompatibles);
            }
        List<Viaje> viajesFiltradosPorTipo = viajesCompatibles;
        
        do{
            List<Viaje> backUpViajesCompatibles = new ArrayList();
            viajesCompatibles = viajesFiltradosPorTipo;
            System.out.println("Viajes disponibles: ");
            buscador.printViajesCompatibles(viajesCompatibles);
            
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
                    pasajes.get(pasajes.size()-1).getVuelos().add(new Escala(
                            maxIdMasUno((List<Object>)(Object)pasajes.get(pasajes.size()-1).getVuelos()),
                            viajesCompatibles.get(0), seleccionarAsiento(viajesCompatibles.get(0)),
                            seleccionarTratamientoEspecial(), pasajes.get(pasajes.size()-1)));
                    pasajes.get(pasajes.size()-1).getVuelos().get(pasajes.get(pasajes.size()-1).getVuelos().
                            size()-1).getAsiento().setVuelo(pasajes.get(pasajes.size()-1).getVuelos().
                                    get(pasajes.get(pasajes.size()-1).getVuelos().size()-1));
                    System.out.println("\nVuelo confirmado: ");
                    buscador.printShortViajesCompatibles(viajesCompatibles);
                    ventas.get(ventas.size()-1).getPasajes().add(pasajes.get(pasajes.size()-1));
                    ventas.get(ventas.size()-1).setPrecioTotal(ventas.get(ventas.size()-1).getPrecioTotal() +
                            pasajes.get(pasajes.size()-1).getVuelos().get(pasajes.get(pasajes.size()-1).
                                    getVuelos().size()-1).getViaje().getPrecioPasaje());
                    ventas.get(ventas.size()-1).setTotalPendiente(ventas.get(ventas.size()-1).getPrecioTotal());
                    viajes.get(viajes.indexOf(viajesCompatibles.get(0))).getPasajeros().add(pasajes.get(pasajes.size()-1).getPasajero());
                    pasajes.get(pasajes.size()-1).getPasajero().getPasajes().add(pasajes.get(pasajes.size()-1));
                }
                else{
                    System.out.println("La reserva del pasaje ha sido cancelada.");
                    ventas.remove(ventas.get(ventas.size()-1));
                }
            }
            System.out.print("Desea agregar una escala al pasaje? (Si/No): ");
            if (validator.validacionSiNo() == false)
                break;
            } while(true);
        }
        pagarPasaje(cantidadDePasajes);
        vendedor.getVentas().add(ventas.get(ventas.size()-1));
        

        for (int i = cantidadDePasajes; i > 0; i--)
            System.out.print(pasajes.get(pasajes.size()-i).toStringCompleto());
        
        actualizarArchivos();
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
            pasajeroBuscado = new Pasajero(maxIdMasUno((List<Object>)(Object)pasajeros), dni, fechaNacimiento, nombre, apellido, 0, sexo, pasajes);            
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

    private static void pagarPasaje(int cantidad) {
        double totalAPagar = 0;
        Pasajero pasajeroPuntos = null;
        List<Pasaje> pasajesAPagar = new ArrayList();
        for (int i = cantidad; i>0;i--)
            pasajesAPagar.add(pasajes.get(pasajes.size()-i));
        for (Pasaje p:pasajesAPagar)
            for (Escala e:p.getVuelos())
                totalAPagar += e.getViaje().getPrecioPasaje();
        DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
        int puntosGastados = 0;
        for (Pasaje p:pasajesAPagar){
            if (totalAPagar > 0){
                System.out.print("Desea usar puntos del pasajero " + (p.getPasajero().getId()+1) + "? (Si/No): ");
                boolean usarPuntos = validator.validacionSiNo();

                if (usarPuntos == true){
                    String fraccion = "";
                    while (!fraccion.toLowerCase().matches("mitad") && !fraccion.toLowerCase().matches("todo")){
                        System.out.print("Desea pagar todo con puntos o solo la mitad? (Mitad/Todo): ");
                        fraccion = std.nextLine();
                    }
                    // Suponiendo que cada punto sean 50 pesos
                    int totalMinimo = 0;
                    if (fraccion.matches("todo"))
                        totalMinimo = 0;
                    else
                        totalMinimo = (int) (totalAPagar/2);
                    while (p.getPasajero().getPuntos() > 0 && totalAPagar > totalMinimo){
                        totalAPagar = totalAPagar - 50;
                        p.getPasajero().setPuntos(p.getPasajero().getPuntos()-1);
                        puntosGastados ++;
                    }
                    if (totalAPagar < 0)
                        totalAPagar = 0;
                    
                    System.out.println("Se han usado " + puntosGastados + " puntos, y resta pagar " +
                        df.format(totalAPagar) + " $.");
                    System.out.println("Restan " + p.getPasajero().getPuntos() + " puntos en la cuenta.");
                }
                else{
                    System.out.println("Se han usado 0 puntos, y resta pagar " +
                    df.format(totalAPagar) + " $.");
                }
            }
        }
        String medioDePago = validator.validarMedioDePago();
        ventas.get(ventas.size()-1).setFormaDePago(medioDePago.substring(0,1).toUpperCase()
                + medioDePago.substring(1, medioDePago.length()).toLowerCase());
        if (medioDePago.toLowerCase().matches("tarjeta"))
            totalAPagar = totalAPagar * 1.15;
        
        System.out.println("Presione ENTER cuando el cliente haya hecho el pago...");
        std.nextLine();
        ventas.get(ventas.size()-1).setTotalPendiente(0);
        int puntos = 0;
        for (int i = (int)totalAPagar; i > 0; i = i-150)
            if ((i-150) > 0)
                puntos++;
        System.out.println("Se han pagado " + df.format(totalAPagar)+ " $, y se le han sumado "
                + puntos + " puntos.");
        ventas.get(ventas.size()-1).setPuntosCorrespondientes(puntos);
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
                System.out.println("La multa a pagar es de " + pasaje.getPrecioPasaje() * 0.5 +
                        " $\nPor favor pague ahora al cajero.");
                System.out.println("Presione ENTER cuando el cliente haya pagado.");
                std.nextLine();
                for (Escala e:pasaje.getVuelos())
                    e.getAsiento().setOcupado(false);
                pasajes.remove(pasaje);
                System.out.println("Su pasaje ha sido dado de baja. Muchas gracias.");
            }
        }
        if (opcion.toLowerCase().matches("modificarlo")){
            pasaje.toStringCompleto();
            Escala escala = null;
            while (escala == null){
                System.out.print("Ingrese el id del vuelo del pasaje que desea modificar: ");
                int inputIdVuelo = validator.validacionInt();
                for (Escala v:pasaje.getVuelos())
                    if (v.getId() == inputIdVuelo)
                        escala = v;
            }
            Viaje viajeOriginal = escala.getViaje();
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
                    if (escala.getViaje() instanceof ViajeAereo)
                        viajesPosibles = buscador.buscarViajesAereos(viajesPosibles);
                    if (escala.getViaje() instanceof ViajeTerrestre)
                        viajesPosibles = buscador.buscarViajesTerrestres(viajesPosibles);
                    
                        viajesPosibles = buscador.buscarViajesPorCategoria(viajesPosibles, escala.getViaje().getCategoria());
                        viajesPosibles = buscador.buscarViajesPorCiudadDePartida(viajesPosibles, escala.getViaje().getCiudadDePartida());
                        viajesPosibles = buscador.buscarViajesPorCiudadDeDestino(viajesPosibles, escala.getViaje().getDestino());
                        viajesPosibles = buscador.buscarViajesPorEmpresaDelViaje(viajesPosibles, escala.getViaje().getEmpresaDelViaje());
                        viajesPosibles = buscador.buscarViajesPorFranjaHoraria(viajesPosibles, escala.getViaje().getFranjaHoraria());
                        viajesPosibles = buscador.buscarPorAsiento(viajesPosibles, escala.getAsiento().getId());
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
                                int idAsiento = escala.getAsiento().getId();
                                escala.getAsiento().setOcupado(false);
                                escala.setViaje(viajesPosibles.get(0));
                                escala.setAsiento(escala.getViaje().getAsientos().get(idAsiento));
                                escala.getAsiento().setOcupado(true);
                            }
                        }
            
            if (escala.getViaje() == viajeOriginal)
                System.out.println("La fecha no pudo ser cambiada, ya que no hay pasajes disponibles que cumplan con sus"
                        + "condiciones. Intente nuevamente con otros parametros.");
            else
                System.out.println("La fecha ha sido cambiada exitosamente!");
            } // Fin del if de cambio segun fecha
            if (modificar.toLowerCase().matches("asiento")){
                escala.getAsiento().setOcupado(false);
                seleccionarAsiento(escala.getViaje());
            }
            if (modificar.toLowerCase().matches("franja horaria")){
                String franjaHoraria = "";
                while (!franjaHoraria.toLowerCase().matches("maniana") && !franjaHoraria.toLowerCase().matches("tarde") &&
                        !franjaHoraria.toLowerCase().matches("noche")){
                    System.out.print("Ingrese la franja horaria deseada (Maniana/Tarde/Noche): ");
                    franjaHoraria = std.nextLine();
                }
                
                List<Viaje> viajesPosibles = viajes;
                viajesPosibles = buscador.buscarPorAsiento(viajesPosibles, escala.getAsiento().getId());
                if (escala.getViaje() instanceof ViajeAereo)
                    viajesPosibles = buscador.buscarViajesAereos(viajesPosibles);
                if (escala.getViaje() instanceof ViajeTerrestre)
                    viajesPosibles = buscador.buscarViajesTerrestres(viajesPosibles);
                viajesPosibles = buscador.buscarViajesPorCategoria(viajesPosibles, escala.getViaje().getCategoria());
                viajesPosibles = buscador.buscarViajesPorCiudadDePartida(viajesPosibles, escala.getViaje().getCiudadDePartida());
                viajesPosibles = buscador.buscarViajesPorCiudadDeDestino(viajesPosibles, escala.getViaje().getDestino());
                viajesPosibles = buscador.buscarViajesPorEmpresaDelViaje(viajesPosibles, escala.getViaje().getEmpresaDelViaje());
                viajesPosibles = buscador.buscarViajesPorFecha(viajesPosibles, escala.getViaje().getFecha());
                viajesPosibles = buscador.buscarViajesPorFranjaHoraria(viajesPosibles, franjaHoraria);
                
                if (!viajesPosibles.isEmpty()){
                    buscador.printViajesCompatibles(viajesPosibles);
                            System.out.print("Ingrese el id del viaje al que le gustaria cambiarse: ");
                            viajesPosibles = buscador.buscarPorId(viajesPosibles, validator.validacionInt());
                            if (viajesPosibles.isEmpty())
                                System.out.println("Ese id no coincide con el de ninguno de los viajes ofrecidos");
                            else{
                                int idAsiento = escala.getAsiento().getId();
                                escala.getAsiento().setOcupado(false);
                                escala.setViaje(viajesPosibles.get(0));
                                escala.setAsiento(escala.getViaje().getAsientos().get(idAsiento));
                                escala.getAsiento().setOcupado(true);
                            }
                }
                else
                    System.out.println("Ninguno de los viajes disponibles coincide con sus parametros.");
            }
        }
        actualizarArchivos();
    }
    
    private static void actualizarArchivos(){
        if (!new File("res").exists())
            new File("res").mkdirs();
        File ventasFile = new File("res/Ventas.txt");
        File pasajesVendidosFile = new File("res/Pasajes Vendidos.txt");
        File AsientosAsignadosFile = new File("res/Asientos Asignados.txt");
        File pasajerosFile = new File("res/Pasajeros.txt");
        try {
            if (!ventasFile.exists())
                ventasFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(ventasFile, false));
            for (Venta v:ventas){
                // No se que es ancho fijo, asi que lo dejo en tab.
                writer.write(validator.inicioHora(v.getFecha()) + v.toString() + "\n");
            }
            writer.flush();
            writer = new BufferedWriter(new FileWriter(pasajesVendidosFile, false));
            for (Pasaje p:pasajes){
                writer.write(p.toStringCompletoPuntoYComa()+ "\n");
            }
            writer.flush();
            writer = new BufferedWriter(new FileWriter(AsientosAsignadosFile, false));
            for (Pasaje p:pasajes){
                for (Escala e:p.getVuelos())
                writer.write(e.getAsiento().toStringVendedor());
            }
            writer.flush();
            new FileWriter(pasajerosFile, false).write("");
            writer = new BufferedWriter(new FileWriter(pasajerosFile, true));
            for (Pasajero p:pasajeros){
                writer.write(p.toWrite() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void elegirVendedor() {
        String nombreYApellido = "";
        while (!nombreYApellido.contains(" ")){
            System.out.print("Ingrese el nombre y apellido del vendedor: ");
            nombreYApellido = std.nextLine();
        }
        String[] palabras = nombreYApellido.split(" ");
        for (Vendedor v:vendedores)
            if (v.getNombre().toLowerCase().equals(palabras[0].toLowerCase()) &&
                    v.getApellido().toLowerCase().equals(palabras[1].toLowerCase())){
                vendedor = v;
                return;
            }
        vendedor = new Vendedor(maxIdMasUno((List<Object>)(Object)vendedores),
                (palabras[0].substring(0,1).toUpperCase()+palabras[0].substring(1, palabras[0].length()).toLowerCase()),
                (palabras[1].substring(0,1).toUpperCase()+palabras[1].substring(1, palabras[1].length()).toLowerCase()));
        vendedores.add(vendedor);
    }

    private static void generarArchivoEmpresas() {
        File empresasFolder = new File("res/Empresas");
        if (!empresasFolder.exists())
            empresasFolder.mkdirs();
        for (EmpresaDeViajes edv:empresasDeViajes){
            String nombreEmpresa;
            if (edv.getNombre().length() >= 4)
                nombreEmpresa = edv.getNombre().substring(0, 4) + "-" +
                    validator.imprimirFecha2(GregorianCalendar.getInstance()) + ".txt";
            else
                nombreEmpresa = edv.getNombre();
            File empresasFile = new File("res/Empresas/" + nombreEmpresa);
            try {
                if (!empresasFile.exists())
                empresasFile.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(empresasFile));
                writer.write("Empresa " + (edv.getId()+1) + ": " + edv.getNombre() + "\n");
                writer.flush();
                writer = new BufferedWriter(new FileWriter(empresasFile, true));
                for (Viaje v:edv.getViajes()){
                    for (Pasajero p1:v.getPasajeros()){
                        for (Pasaje p2:p1.getPasajes()){
                            for (Escala escala:p2.getVuelos()){
                            writer.write("P\t" + "Fecha: " + escala.getId() + "\t" +
                                    validator.imprimirFecha3(escala.getViaje().getFecha()) +
                                    "\tOrigen: " + escala.getViaje().getCiudadDePartida() +
                                    "\tDestino: " + escala.getViaje().getDestino() +
                                    "\tCategoria: " + escala.getViaje().getCategoria() +
                                    "Lugares disponibles: " + validator.disponibleSiNo(escala.getViaje().lugaresDisponibles()) + "\n");
                            writer.write("A" + "\tPasaje: " + escala.getId() +
                                    "\tAsientos Disponibles: " + escala.getViaje().lugaresDisponibles() + "\n\n");
                            }
                        }
                    }
                }
                writer.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        
    }

    private static void generarInformeAnio() {
        System.out.print("Ingrese el mes que desea conocer: ");
        int mes = validator.validacionInt()-1;
        for (Venta v:ventas){
            if (v.getFecha().get(Calendar.MONTH) == mes &&
                    v.getFecha().get(Calendar.YEAR) == GregorianCalendar.getInstance().get(Calendar.YEAR)){
                String informe = v.toString();
                for (Pasaje p:v.getPasajes()){
                    informe +=  "\n\t- " + p.getPasajero().toString() + 
                            "\n\t- " + "Forma de pago: " + v.getFormaDePago() +
                            "\n\t- " + "Puntos correspondientes: " + (v.getPuntosCorrespondientes()/v.getPasajes().size()) + "\n";
                    for (Escala escala:p.getVuelos()){
                        informe += "\t\t" + "Vuelo " + v.getId() + "\tAsiento: "+ escala.getAsiento();
                    }
                }
                System.out.println("-------------------------------------------------------");
                System.out.println(informe);
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("res/Informe.txt"));
                    writer.write(informe);
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
                
            }
        }
    }

    private static void ventasArgumento(String[] args) {
        try {
            double importe = Double.parseDouble(args[0]);
            for (Vendedor v1:vendedores){
                System.out.println(v1.toString() +
                        "\n\t- Cantidad de ventas que superan al argumento: " +
                        validator.ventasQueSuperanImporte(importe, v1.getVentas()) +
                        "\n");
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    private static void generarInformePuntos() {
        System.out.println("---------------------------------------------------------");
        if (!pasajeros.isEmpty()){
            File informeFolder = new File("res/Informe de puntos/");
            if (!informeFolder.exists())
                informeFolder.mkdirs();
        }
        else{
            System.out.println("Aun no hay pasajeros registrados.");
            return;
        }
        for (Pasajero p:pasajeros){
            if (p.getPuntos() > 0){
                File informeFile = new File ("res/Informe de puntos/" + p.getNombre() + p.getApellido());
                try {
                    if (!informeFile.exists())
                        informeFile.createNewFile();
                    else
                        new FileWriter(informeFile, false).write("");
                    FileWriter writer = new FileWriter(informeFile, true);
                    writer.write(p.toString() + "\n" + 
                            "\t- Cantidad de puntos: " + p.getPuntos());
                    writer.write("\nViajes para los que alcanza la cantidad de puntos: \n");
                    writer.flush();
                    for (Viaje v:viajes)
                        if (v.getPrecioPasaje() < (p.getPuntos()*50))
                            writer.write(v.toString() + "\n");
                    writer.flush();
                } catch (IOException iOException) {
                    System.out.println(iOException);
                }
            }
        }
        System.out.println("El informe ha sido generado.");
    }

    private static void mostrarPorPantalla() {
        if (ventas.size() == 0){
            System.out.println("Aun no hay ventas");
            return;
        }
        List<String> localidades = new ArrayList();
        for (int i=0; i<3;i++){
            System.out.print("Ingrese el nombre de la localidad " + (i+1) + ": ");
            localidades.add(std.nextLine());
        }
        
        for (Venta v:ventas){
            boolean pasoPorLocalidades = false;
            for (Pasaje p:v.getPasajes()){
                for (Escala escala:p.getVuelos()){
                    for (String localidad:localidades){
                        if (escala.getViaje().getDestino().matches(localidad))
                            pasoPorLocalidades = true;
                    }
                }
            }
            if (pasoPorLocalidades == false)
                System.out.println("La venta" + v.getId() + "no paso por esas localidades.");
        }
    }
}

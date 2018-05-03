package ClasesEInterfaces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class Validator {

    public Calendar nuevaFecha(String string) {
        Calendar fecha = GregorianCalendar.getInstance();
        String[] datos = string.split("/");
        fecha.set(Calendar.MONTH, Integer.parseInt(datos[0]) - 1);
        fecha.set(Calendar.DAY_OF_MONTH, Integer.parseInt(datos[1]));
        fecha.set(Calendar.YEAR, Integer.parseInt(datos[2]));
        return fecha;
    }

    public String imprimirFecha(Calendar fecha) {
        return fecha.get(Calendar.MONTH)+1 + "/" + fecha.get(Calendar.DAY_OF_MONTH) + "/" + fecha.get(Calendar.YEAR);
    }

    public boolean compararFechas(Calendar fecha1, Calendar fecha2) {
        if (fecha1.get(Calendar.YEAR) == fecha2.get(Calendar.YEAR) &&
                fecha1.get(Calendar.MONTH) == fecha2.get(Calendar.MONTH) &&
                    fecha1.get(Calendar.DAY_OF_MONTH) == fecha2.get(Calendar.DAY_OF_MONTH))
            return true;
        else
            return false;
    }

    public Validator() {}
    
    private static final Scanner std = new Scanner (System.in);

    public Calendar validacionFecha() {
        Calendar fecha = GregorianCalendar.getInstance();
        int dia = -1, mes = -1, anio = -1;
        System.out.print("Ingrese el anio:\t");
        anio = validacionInt();
        fecha.set(Calendar.YEAR, anio);
        while (mes < 1 || mes > 12){
            System.out.print("Ingrese el mes:\t\t");
            mes = validacionInt();
        }
        fecha.set(Calendar.MONTH, mes-1);
        while (dia < fecha.getActualMinimum(Calendar.DAY_OF_MONTH) ||
                dia > fecha.getActualMaximum(Calendar.DAY_OF_MONTH)){
            System.out.print("Ingrese el dia del mes:\t");
            dia = validacionInt();
        }
        fecha.set(Calendar.DAY_OF_MONTH, dia);
        return fecha;
    }
    
    public int validacionInt(){
		int num;
		while(std.hasNextInt()==false){
			System.out.print("El valor ingresado no es numerico. Ingrese nuevamente: ");
			std.nextLine();}
		num=std.nextInt();
		std.nextLine();
		while (num<0){
			System.out.print("El valor ingresado es menor a cero. Ingrese nuevamente: ");
			num=validacionInt();
		}
		return num;
    }
    
    public boolean validacionSiNo(){
		boolean bool=false;
		String x=std.nextLine();
		while(x.toLowerCase().matches("si")==false && x.toLowerCase().matches("no")==false){
			System.out.print("El valor ingresado no es valido. Ingrese nuevamente: ");
			x=std.nextLine();}
		if (x.toLowerCase().matches("si"))
			bool=true;
		if (x.toLowerCase().matches("no"))
			bool=false;
		return bool;
    }

    public String align(String palabra, int cantidad) {
        int largo = palabra.length();
        String adicional = "";
        for (int i = cantidad; i > 0; i--)
            adicional = adicional + "\t";
        while (largo >= 8 && adicional.length() >= 1){
            largo -= 8;
            adicional = adicional.substring(0, adicional.length()-1);
        }
        return palabra + adicional;
    }

    String printSiNo(boolean ocupado) {
        if (ocupado == true)
            return "Si";
        else
            return "No";
    }

    public String validarMedioDePago() {
        String medioDePago = "";
        while (!medioDePago.toLowerCase().matches("tarjeta") && !medioDePago.toLowerCase().matches("efectivo")){
            System.out.print("Como desea pagar el pasaje? (Tarjeta/Efectivo): ");
            medioDePago = std.nextLine();
        }
        return medioDePago;
    }
    
    public String inicioHora(Calendar time){
        String ampm;
        if (time.get(Calendar.AM_PM) == Calendar.AM)
            ampm = "am";
        else
            ampm = "pm";
        return time.get(Calendar.HOUR) + ":" + time.get(Calendar.MINUTE) + ":" + time.get(Calendar.SECOND)
                + " " + ampm + ":\t";
    }
    
    public String getTipoPasaje(Viaje viaje){
        if (viaje instanceof ViajeAereo)
            return "aereo";
        else
            return "terrestre";
    }

    double calcularPrecio(String categoria, String destino, String tipoViaje) {
        double precioTotal = 0;
        File preciosFile = new File("res/Precios.txt");
        if (!preciosFile.exists()){
            System.out.println("El archivo de precios no fue encontrado.");
            return 0;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(preciosFile));
            String line = "";
            while (reader.ready()){
                line = reader.readLine();
                if (line.toLowerCase().contains(categoria.toLowerCase())){
                    String[] palabras = line.split(";");
                    precioTotal += Double.parseDouble(palabras[palabras.length-1].substring(1, palabras[palabras.length-1].length()-1));
                }
                if (line.toLowerCase().contains(destino.toLowerCase())){
                    String[] palabras = line.split(";");
                    precioTotal += Double.parseDouble(palabras[palabras.length-1].substring(1, palabras[palabras.length-1].length()-1));
                }
                if (line.toLowerCase().contains(tipoViaje.toLowerCase())){
                    String[] palabras = line.split(";");
                    precioTotal += Double.parseDouble(palabras[palabras.length-1].substring(1, palabras[palabras.length-1].length()-1));
                }
            }
                reader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return precioTotal;
    }

    public String imprimirFecha2(Calendar fecha) {
        DateFormat df = new SimpleDateFormat("yy");
        String anio = df.format(fecha.get(Calendar.YEAR));
        return fecha.get(Calendar.DAY_OF_MONTH) + fecha.get(Calendar.MONTH+1) + anio + "";
    }

    public String imprimirFecha3(Calendar fecha) {
        return "" + fecha.get(Calendar.YEAR) + fecha.get(Calendar.MONTH)+1 + fecha.get(Calendar.DAY_OF_MONTH);
    }

    public String disponibleSiNo(int lugaresDisponibles) {
        if (lugaresDisponibles > 0)
            return "Si";
        else
            return "No";
    }

    public int ventasQueSuperanImporte(double importe, List<Venta> ventas) {
        int cantidad = 0;
        for (Venta v:ventas){
            if (v.getPrecioTotal() > importe)
                cantidad++;
        }
        return cantidad;
    }

    
}

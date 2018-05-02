package ClasesEInterfaces;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
        System.out.print("Ingrese el anio: ");
        anio = validacionInt();
        fecha.set(Calendar.YEAR, anio);
        while (mes < 1 || mes > 12){
            System.out.print("Ingrese el mes: ");
            mes = validacionInt();
        }
        fecha.set(Calendar.MONTH, mes-1);
        while (dia < fecha.getActualMinimum(Calendar.DAY_OF_MONTH) ||
                dia > fecha.getActualMaximum(Calendar.DAY_OF_MONTH)){
            System.out.print("Ingrese el dia del mes: ");
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
}

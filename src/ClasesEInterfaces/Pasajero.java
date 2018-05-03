package ClasesEInterfaces;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author cristolagos
 */
public class Pasajero {
    private int id;
    private int documento;
    private Calendar nacimiento;
    private String nombre;
    private String apellido;
    private int puntos;
    private String sexo;
    private List<Pasaje> pasajes;
    
    public Pasajero(){
        this.pasajes = new ArrayList();
    }

    public Pasajero(int id, int documento, Calendar nacimiento, String nombre,
            String apellido, int puntos, String sexo, List<Pasaje> pasajes) {
        this.pasajes = pasajes;
        this.id = id;
        this.documento = documento;
        this.nacimiento = nacimiento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puntos = puntos;
        this.sexo = sexo;
    }
    
    public int getEdad(){
        Calendar fecha = Calendar.getInstance();

        int anios = fecha.get(Calendar.YEAR) -  this.nacimiento.get(Calendar.YEAR);
        int meses = fecha.get(Calendar.MONTH) - this.nacimiento.get(Calendar.MONTH);
        int dias = fecha.get(Calendar.DAY_OF_MONTH) - this.nacimiento.get(Calendar.DAY_OF_MONTH);

        if (meses < 0 || (meses == 0 && dias < 0)) {
            anios = anios - 1;
        }
        return anios;
    }

    @Override
    public String toString() {
        return "Pasajero: " + this.nombre + " " + this.apellido + "\t\tFecha de Nacimiento: " +
                new Validator().imprimirFecha(this.nacimiento) + "\t\tEdad: " + this.getEdad() + "\tSexo: " + this.sexo;
    }
    
    public String toWrite() {
        return "Pasajero " + id + ": " + this.nombre + " " + this.apellido + "|Fecha de Nacimiento: " +
                new Validator().imprimirFecha(this.nacimiento) + "|Edad: " + this.getEdad() + "|Sexo: " + this.sexo;
    }

    public int getId() {
        return id;
    }

    public int getDocumento() {
        return documento;
    }

    public Calendar getNacimiento() {
        return nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getSexo() {
        return sexo;
    }

    public List<Pasaje> getPasajes() {
        return pasajes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public void setNacimiento(Calendar nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setPasajes(List<Pasaje> pasajes) {
        this.pasajes = pasajes;
    }

    public String calcularMenor() {
        if (getEdad() < 18)
            return "Si";
        else
            return "No";
    }
    
    
    
    
}
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
    private int edad;
    private Calendar nacimiento;
    private String nombre;
    private String apellido;
    private int puntos;
    private String sexo;
    private List<Pasaje> pasajes;
    
    public Pasajero(){
        this.pasajes = new ArrayList();
    }

    public Pasajero(int id, int documento, int edad, Calendar nacimiento, String nombre,
            String apellido, int puntos, String sexo, List<Pasaje> pasajes) {
        this.pasajes = pasajes;
        this.id = id;
        this.documento = documento;
        this.edad = edad;
        this.nacimiento = nacimiento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puntos = puntos;
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }

    public int getDocumento() {
        return documento;
    }

    public int getEdad() {
        return edad;
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

    public void setEdad(int edad) {
        this.edad = edad;
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
    
    
    
    
}
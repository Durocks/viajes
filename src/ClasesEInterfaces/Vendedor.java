package ClasesEInterfaces;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cristolagos
 */
public class Vendedor  implements getId{
    private int id;
    private String nombre;
    private String apellido;
    private List<Venta> ventas;

    public Vendedor(int id, String nombre, String apellido) {
        this.ventas = new ArrayList();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public Vendedor(int id, String nombre, String apellido, List<Venta> ventas) {
        this.ventas = ventas;
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Vendedor() {this.ventas = new ArrayList();
}

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Vendedor " + (id+1) + "\tNombre: " + nombre + " " + apellido + "\t\tVentas: " + ventas.size();
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
    
    
}

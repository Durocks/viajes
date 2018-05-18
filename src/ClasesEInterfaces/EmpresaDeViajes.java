package ClasesEInterfaces;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cristolagos
 */
public class EmpresaDeViajes  implements getId{
    private int id;
    private String nombre;
    private long cuit;
    private final List<Viaje> viajes;

    public EmpresaDeViajes() {this.viajes = new ArrayList();
    }

    public EmpresaDeViajes(int id, String nombre, long cuit, List<Viaje> viajes) {
        this.viajes = viajes;
        this.id = id;
        this.nombre = nombre;
        this.cuit = cuit;
    }
    
    public EmpresaDeViajes(int id, String nombre, long cuit) {
        this.viajes = new ArrayList();
        this.id = id;
        this.nombre = nombre;
        this.cuit = cuit;
    }

    public List<Viaje> getViajes() {
        return viajes;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public long getCuit() {
        return cuit;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}

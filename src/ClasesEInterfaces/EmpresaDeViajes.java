package ClasesEInterfaces;

/**
 *
 * @author cristolagos
 */
public class EmpresaDeViajes {
    private int id;
    private String nombre;
    private long cuit;

    public EmpresaDeViajes() {}

    public EmpresaDeViajes(int id, String nombre, long cuit) {
        this.id = id;
        this.nombre = nombre;
        this.cuit = cuit;
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

package ClasesEInterfaces;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author cristolagos
 */
public class Venta {
    private int id;
    private Vendedor vendedor;        
    private List<Pasaje> pasajes;
    private Calendar fecha = GregorianCalendar.getInstance();
    private double precioTotal;
    private double totalPendiente;
    protected String formaDePago;
    protected int puntosCorrespondientes;

    public Venta() {
        this.pasajes = new ArrayList();
    }

    public Venta(int id, Vendedor vendedor, List<Pasaje> pasajes,
            double precioTotal, double totalPendiente, String formaDePago, int puntosCorrespondientes) {
        this.pasajes = pasajes;
        this.id = id;
        this.vendedor = vendedor;
        this.precioTotal = precioTotal;
        this.totalPendiente = totalPendiente;
        this.formaDePago = formaDePago;
        this.puntosCorrespondientes = puntosCorrespondientes;
    }
    
    public Venta(int id, Vendedor vendedor,
            double precioTotal, double totalPendiente, String formaDePago, int puntosCorrespondientes) {
        this.pasajes = new ArrayList();
        this.id = id;
        this.vendedor = vendedor;
        this.precioTotal = precioTotal;
        this.totalPendiente = totalPendiente;
        this.formaDePago = formaDePago;
        this.puntosCorrespondientes = puntosCorrespondientes;
    }
    
    public Venta(int id, Vendedor vendedor) {
        this.pasajes = new ArrayList();
        this.id = id;
        this.vendedor = vendedor;
    }
    
    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    public void setPuntosCorrespondientes(int puntosCorrespondientes) {
        this.puntosCorrespondientes = puntosCorrespondientes;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public int getPuntosCorrespondientes() {
        return puntosCorrespondientes;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public void setTotalPendiente(double totalPendiente) {
        this.totalPendiente = totalPendiente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public List<Pasaje> getPasajes() {
        return pasajes;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public void setPasajes(List<Pasaje> pasajes) {
        this.pasajes = pasajes;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        String toString = "Venta " + id + "\t\tVendedor: " +
                new Validator().align((vendedor.getNombre() + " " +
                        vendedor.getApellido()), 2) + "Fecha: " + new Validator().imprimirFecha(fecha) +
                "\t\tPrecioTotal: " + precioTotal + " $" + "\tTotal Pendiente: " + totalPendiente + " $";
        return toString;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public double getTotalPendiente() {
        return totalPendiente;
    }
    
    
    
    
}

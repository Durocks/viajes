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
    private String nombreVendedor;
    private String apellidoVendedor;
    private List<Pasaje> pasajes;
    private Calendar fecha = GregorianCalendar.getInstance();
    private double precioTotal;
    private double totalPendiente;
    protected String formaDePago;
    protected int puntosCorrespondientes;

    public Venta() {
        this.pasajes = new ArrayList();
    }

    public Venta(int id, String nombreVendedor, String apellidoVendedor, List<Pasaje> pasajes,
            double precioTotal, double totalPendiente, String formaDePago, int puntosCorrespondientes) {
        this.pasajes = pasajes;
        this.id = id;
        this.nombreVendedor = nombreVendedor;
        this.apellidoVendedor = apellidoVendedor;
        this.precioTotal = precioTotal;
        this.totalPendiente = totalPendiente;
        this.formaDePago = formaDePago;
        this.puntosCorrespondientes = puntosCorrespondientes;
    }
    
    public Venta(int id, String nombreVendedor, String apellidoVendedor,
            double precioTotal, double totalPendiente, String formaDePago, int puntosCorrespondientes) {
        this.pasajes = new ArrayList();
        this.id = id;
        this.nombreVendedor = nombreVendedor;
        this.apellidoVendedor = apellidoVendedor;
        this.precioTotal = precioTotal;
        this.totalPendiente = totalPendiente;
        this.formaDePago = formaDePago;
        this.puntosCorrespondientes = puntosCorrespondientes;
    }
    
    public Venta(int id, String nombreVendedor, String apellidoVendedor) {
        this.pasajes = new ArrayList();
        this.id = id;
        this.nombreVendedor = nombreVendedor;
        this.apellidoVendedor = apellidoVendedor;
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

    public String getApellidoVendedor() {
        return apellidoVendedor;
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

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public void setApellidoVendedor(String apellidoVendedor) {
        this.apellidoVendedor = apellidoVendedor;
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
                new Validator().align((nombreVendedor + " " +
                        apellidoVendedor), 2) + "Fecha: " + new Validator().imprimirFecha(fecha) +
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

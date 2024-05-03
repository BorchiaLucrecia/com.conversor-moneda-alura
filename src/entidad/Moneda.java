package entidad;

public class Moneda {
    private String monedaOrigen;
    private double cantidad;
    private String monedaDestino;

    public Moneda( String monedaOrigen,double cantidad, String monedaDestino) {

        this.monedaOrigen = monedaOrigen;
        this.cantidad = cantidad;
        this.monedaDestino = monedaDestino;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

}

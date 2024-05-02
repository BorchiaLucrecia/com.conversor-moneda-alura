package entidad;

public class Moneda {
    private double cantidad;
    private String monedaOrigen;
    private String monedaDestino;

    public Moneda(double cantidad, String monedaOrigen, String monedaDestino) {
        this.cantidad = cantidad;
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
    }

    // Getters y setters
    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public void setMonedaOrigen(String monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public void setMonedaDestino(String monedaDestino) {
        this.monedaDestino = monedaDestino;
    }
}

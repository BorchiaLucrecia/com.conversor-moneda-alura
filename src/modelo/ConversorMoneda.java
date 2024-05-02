package modelo;

public class ConversorMoneda {
    public static double convertir (double cantidad, double tasaOrigen, double tasaDestino){
        // Convertir la cantidad de la moneda de origen a la moneda de destino
        return cantidad * (tasaDestino / tasaOrigen);
    }
}

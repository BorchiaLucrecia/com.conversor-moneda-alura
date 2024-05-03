package modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.lang.String;


public class HistorialConversiones {

    private static final String NOMBRE_ARCHIVO = "historial_conversiones.txt";

    public static void registrarConversion( String monedaOrigen, double tasaOrigen,double cantidad, String monedaDestino, double tasaDestino) {
        LocalDateTime timestamp = LocalDateTime.now();
        String fechaConsulta = timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        double valorFinal = cantidad * tasaDestino;

        //String registro = String.format("[%s] Convirtió %.2f %s a %.2f %s. Valor final: %.2f %s%n", fechaConsulta, cantidad, monedaOrigen, cantidad, monedaDestino, tasaDestino, valorFinal);
        String registro = String.format("[%s] Convirtió %.2f %s en %s. Valor final: %.2f %s%n", fechaConsulta, cantidad, monedaOrigen, monedaDestino, valorFinal, monedaDestino);
        try (FileWriter writer = new FileWriter(NOMBRE_ARCHIVO, true)) {
            writer.write(registro);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de historial de conversiones: " + e.getMessage());
        }
    }

    public static void inicializarArchivo(){
        File file = new File(NOMBRE_ARCHIVO);
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                System.out.println("Error al crear el archivo de historial de conversiones: " + e.getMessage());
            }
        }
    }

    public static String obtenerRutaArchivo() {
        System.out.println("Copia y pega la siguiente ruta en tu explorador de archivos para visualizar el comprobante: ");
        return Paths.get(NOMBRE_ARCHIVO).toAbsolutePath().toString();

    }
}

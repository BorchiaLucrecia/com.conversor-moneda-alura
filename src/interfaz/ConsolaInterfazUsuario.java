package interfaz;

import api.FiltroMoneda;
import api.RespuestaAPI;
import com.google.gson.JsonObject;
import entidad.Moneda;
import modelo.ConversorMoneda;

import api.APIManager;
import api.JSONManager;
import modelo.HistorialConversiones;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class ConsolaInterfazUsuario implements InterfazUsuario {
    private final Scanner scanner;

    public ConsolaInterfazUsuario() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void iniciar() {
        System.out.println("Bienvenido al Conversor de Moneda");
        HistorialConversiones.inicializarArchivo();
        boolean continuar = true;

        while (continuar) {
            try {
                HttpResponse<String> respuestaAPI = APIManager.obtenerDatosAPI("USD");

                if (respuestaAPI.statusCode() != 200) {
                    System.out.println("Error al obtener los datos de la API. Código de estado: " + respuestaAPI.statusCode());
                    continue;
                }

                JsonObject jsonResponse = JSONManager.parsearJSON(respuestaAPI.body());
                if (jsonResponse == null) {
                    System.out.println("Error al parsear la respuesta JSON.");
                    continue;
                }

                RespuestaAPI respuesta = RespuestaAPI.fromJson(jsonResponse);
                FiltroMoneda.filtrarMonedas(respuesta);

                Moneda moneda = solicitarInformacionMoneda(respuesta);
                JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");
                if (conversionRates == null) {
                    System.out.println("No se encontraron tasas de conversión en la respuesta de la API.");
                    continue;
                }

                String monedaOrigen = moneda.getMonedaOrigen();
                String monedaDestino = moneda.getMonedaDestino();

                if (!conversionRates.has(monedaOrigen) || !conversionRates.has(monedaDestino)) {
                    System.out.println("Error: Las tasas de conversión para las monedas especificadas no están disponibles.");
                    continue;
                }

                double tasaOrigen = conversionRates.get(monedaOrigen).getAsDouble();
                double tasaDestino = conversionRates.get(monedaDestino).getAsDouble();

                double cantidadConvertida = ConversorMoneda.convertir(tasaOrigen, moneda.getCantidad(), tasaDestino);
                System.out.println("Cantidad convertida: " + cantidadConvertida + " " + moneda.getMonedaDestino());
                HistorialConversiones.registrarConversion(moneda.getMonedaOrigen(), tasaOrigen, moneda.getCantidad(), moneda.getMonedaDestino(), tasaDestino);



        } catch (Exception e) {
                System.out.println(e);

            }
            continuar = preguntarSiContinuar();
        }

        System.out.println("Gracias por usar el conversor.");
        System.out.println("Puede encontrar su comprobante de conversiones en: " + HistorialConversiones.obtenerRutaArchivo());
    }

    public Moneda solicitarInformacionMoneda(RespuestaAPI respuesta) {
        String monedaOrigen = solicitarMoneda("Ingrese las siglas de la moneda de origen: ", respuesta);
        double cantidad = solicitarDouble("Ingrese la cantidad a convertir: ");
        String monedaDestino = solicitarMoneda("Ingrese las siglas de la moneda de destino: ", respuesta);

        return new Moneda(monedaOrigen, cantidad, monedaDestino);
    }

    private String solicitarMoneda(String mensaje, RespuestaAPI respuesta) {
        List<String> monedasDisponibles = respuesta.monedasDisponibles();

        String moneda = "";
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println(mensaje);

            moneda = scanner.nextLine().toUpperCase();
            if (monedasDisponibles.contains(moneda)) {
                entradaValida = true;
            } else {
                System.out.println("Error: Seleccione una opción válida.");
            }
        }
        return moneda;
    }

    private double solicitarDouble(String mensaje) {
        double valor = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.print(mensaje);
            try {
                valor = Double.parseDouble(scanner.nextLine());
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        }
        return valor;
    }

    private boolean preguntarSiContinuar() {
        System.out.println("¿Desea realizar otra conversión? (s/n)");
        String respuesta = scanner.nextLine().toLowerCase();
        return respuesta.equals("s");
    }

}

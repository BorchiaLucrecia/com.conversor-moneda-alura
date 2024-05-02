package interfaz;

import com.google.gson.JsonObject;
import entidad.Moneda;
import modelo.ConversorMoneda;

import api.APIManager;
import api.RespuestaAPI;
import api.JSONManager;

import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConsolaInterfazUsuario implements InterfazUsuario {
    private final Scanner scanner;

    public ConsolaInterfazUsuario() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void iniciar() {
        HttpResponse<String> respuestaAPI;
        try {
            respuestaAPI = APIManager.obtenerDatosAPI("USD");
        } catch (Exception e) {
            System.out.println("Error al obtener los datos de la API: " + e.getMessage());
            return;
        }

        if (respuestaAPI.statusCode() != 200) {
            System.out.println("Error al obtener los datos de la API. Código de estado: " + respuestaAPI.statusCode());
            return;
        }

        JsonObject jsonResponse = JSONManager.parsearJSON(respuestaAPI.body());
        if (jsonResponse == null) {
            System.out.println("Error al parsear la respuesta JSON.");
            return;
        }

        Moneda moneda = solicitarInformacionMoneda();
        JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");
        if (conversionRates == null) {
            System.out.println("No se encontraron tasas de conversión en la respuesta de la API.");
            return;
        }

        double tasaOrigen = conversionRates.has(moneda.getMonedaOrigen()) ? conversionRates.get(moneda.getMonedaOrigen()).getAsDouble() : 0;
        double tasaDestino = conversionRates.has(moneda.getMonedaDestino()) ? conversionRates.get(moneda.getMonedaDestino()).getAsDouble() : 0;

        double cantidadConvertida = ConversorMoneda.convertir(moneda.getCantidad(), tasaOrigen, tasaDestino);
        System.out.println("Cantidad convertida: " + cantidadConvertida + " " + moneda.getMonedaDestino());
    }


    public Moneda solicitarInformacionMoneda() {
        System.out.println("Bienvenido al Conversor de Moneda");
        double cantidad = solicitarDouble("Ingrese la cantidad a convertir: ");
        String monedaOrigen = solicitarMoneda("Ingrese la moneda de origen (ARS, BRL, USD): ");
        String monedaDestino = solicitarMoneda("Ingrese la moneda de destino (ARS, BRL, USD): ");

        return new Moneda(cantidad, monedaOrigen, monedaDestino);
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

    private String solicitarMoneda(String mensaje) {
        String moneda = "";
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.print(mensaje);
            moneda = scanner.nextLine().toUpperCase();
            if (moneda.equals("ARS") || moneda.equals("BRL") || moneda.equals("USD")) {
                entradaValida = true;
            } else {
                System.out.println("Error: Ingrese una moneda válida (ARS, BRL, USD).");
            }
        }
        return moneda;
    }
}

//package interfaz;
//
//
//import entidad.Moneda;
//import modelo.ConversorMoneda;
//
//import api.APIManager;
//import api.RespuestaAPI;
//import api.JSONManager;
//
//// import java.io.IOException;
//import java.util.Scanner;
//
//public class ConsolaInterfazUsuario implements InterfazUsuario {
//    private final Scanner scanner;
//
//    public ConsolaInterfazUsuario() {
//        this.scanner = new Scanner(System.in);
//    }
//
//    @Override
//    public void iniciar() {
//        String respuestaJson;
//        try {
//            respuestaJson = APIManager.obtenerDatosAPI("USD").body();
//        } catch (IOException | InterruptedException e) {
//            System.out.println("Error al obtener los datos de la API: " + e.getMessage());
//            return;
//        }
//
//        RespuestaAPI respuestaAPI = new RespuestaAPI(respuestaJson);
//
//        boolean continuar;
//        do {
//            Moneda moneda = solicitarInformacionMoneda();
//            double cantidadConvertida = ConversorMoneda.convertir(moneda.getCantidad(),
//                    respuestaAPI.getTasa(moneda.getMonedaOrigen()),
//                    respuestaAPI.getTasa(moneda.getMonedaDestino()));
//            System.out.println("Cantidad convertida: " + cantidadConvertida + " " + moneda.getMonedaDestino());
//            System.out.println("¿Desea realizar otra conversión? (S/N)");
//            String respuesta = scanner.nextLine().toUpperCase();
//            continuar = respuesta.equals("S");
//        } while (continuar);
//    }
//
//    public Moneda solicitarInformacionMoneda() {
//        System.out.println("Bienvenido al Conversor de Moneda");
//        double cantidad = solicitarDouble("Ingrese la cantidad a convertir: ");
//        String monedaOrigen = solicitarMoneda("Ingrese la moneda de origen (ARS, BRL, USD): ");
//        String monedaDestino = solicitarMoneda("Ingrese la moneda de destino (ARS, BRL, USD): ");
//
//        return new Moneda(cantidad, monedaOrigen, monedaDestino);
//    }
//
//    private double solicitarDouble(String mensaje) {
//        double valor = 0;
//        boolean entradaValida = false;
//        while (!entradaValida) {
//            System.out.print(mensaje);
//            try {
//                valor = Double.parseDouble(scanner.nextLine());
//                entradaValida = true;
//            } catch (NumberFormatException e) {
//                System.out.println("Error: Ingrese un número válido.");
//            }
//        }
//        return valor;
//    }
//
//    private String solicitarMoneda(String mensaje) {
//        String moneda = "";
//        boolean entradaValida = false;
//        while (!entradaValida) {
//            System.out.print(mensaje);
//            moneda = scanner.nextLine().toUpperCase();
//            if (moneda.equals("ARS") || moneda.equals("BRL") || moneda.equals("USD")) {
//                entradaValida = true;
//            } else {
//                System.out.println("Error: Ingrese una moneda válida (ARS, BRL, USD).");
//            }
//        }
//        return moneda;
//    }
//}
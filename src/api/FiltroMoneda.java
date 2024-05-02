package api;

import com.google.gson.JsonObject;

public class FiltroMoneda {
    public static void filtrarMonedas(JsonObject jsonResponse){
        //verificar si la respuesta no es nula
        if(jsonResponse == null){
            System.out.println("La respuesta de la API es nula. No se pueden filtrar las monedas.");
            return;
        }

        //Acceder al objeto conversion-rates en la respuesta JSON
        JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion-rates");
        if(conversionRates == null){
            System.out.println("No se encontraron tasas de conversión en la respuesta de la API.");
            return;
        }

        //Filtrar y mostrar las monedas de interés
        System.out.println("Monedas disponibles:");
        if(conversionRates.has("USD")){
            System.out.println("USD - Dólar estadounidense");
        }
        if(conversionRates.has("BRL")){
            System.out.println("BRL - Real brasileño");
        }
        if(conversionRates.has("ARS")){
            System.out.println("ARS - Peso argentino");
        }
    }
}

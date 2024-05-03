package api;

import java.util.List;

public class FiltroMoneda {
    public static void filtrarMonedas(RespuestaAPI respuestaAPI) {
         List<String> monedasDisponibles = respuestaAPI.monedasDisponibles();

         if (monedasDisponibles.isEmpty()) {
                System.out.println("No se encontraron tasas de conversión en la respuesta de la API.");
                return;
         }

         System.out.println("Monedas disponibles:");

            int columnas = 15;
            int filas = (int) Math.ceil((double) monedasDisponibles.size() / columnas);

            // Imprimir en tres columnas
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    int index = i + j * filas;
                    if (index < monedasDisponibles.size()) {
                        System.out.printf("%-10s", monedasDisponibles.get(index));
                    }
                }
                System.out.println();
            }
        }

    }


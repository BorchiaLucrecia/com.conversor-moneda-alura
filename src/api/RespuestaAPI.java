package api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record RespuestaAPI(String body, int statusCode, List<String> monedasDisponibles) {
    public static RespuestaAPI fromJson(JsonObject jsonResponse) {
        List<String> monedasDisponibles = new ArrayList<>();

        if (jsonResponse != null) {
            JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");
            if (conversionRates != null) {
                for (Map.Entry<String, JsonElement> entry : conversionRates.entrySet()) {
                    monedasDisponibles.add(entry.getKey());
                }
            }
        }
        return new RespuestaAPI(jsonResponse.toString(), 200, monedasDisponibles);
    }
}

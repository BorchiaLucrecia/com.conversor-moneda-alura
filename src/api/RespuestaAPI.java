package api;

//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//
//public class RespuestaAPI {
//    private final JsonObject jsonObject;
//
//    public RespuestaAPI(String json) {
//        this.jsonObject = JsonParser.parseString(json).getAsJsonObject();
//    }
//
//    public double getTasa(String moneda) {
//        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
//        return conversionRates.get(moneda).getAsDouble();
//    }
// }

public record RespuestaAPI(String body, int statusCode) {
}

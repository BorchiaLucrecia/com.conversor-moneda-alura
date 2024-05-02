package api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class JSONManager {
    public static JsonObject parsearJSON(String json){
        //Verificar si el JSON es nulo o vacio
        if( json == null || json.isEmpty()){
            System.out.println("El JSON proporcionado es nulo o vacío");
            return null;
        }

        //Parsear el JSON
        try {
            return JsonParser.parseString(json).getAsJsonObject();
        } catch (Exception e){
            System.out.println("Error al parsear el JSON: " + e.getMessage());
            return null;
        }
    }
}

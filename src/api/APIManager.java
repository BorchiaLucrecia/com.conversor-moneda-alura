package api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class APIManager {
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/27ef83479e75a470be0f4c4a/latest/";

    public static HttpResponse<String> obtenerDatosAPI( String codigoMoneda) throws IOException, InterruptedException {
        String apiUrl = API_BASE_URL +codigoMoneda;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}

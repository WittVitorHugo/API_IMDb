import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;

public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {

        // criando cliente
        HttpClient client = HttpClient.newHttpClient();
        // criando request
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/k_2beq6vho"))
                .GET()
                .build();
        // Armazenando a resposta
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());


        System.out.println(response.body());
    }
}

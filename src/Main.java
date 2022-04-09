import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {

        // criando cliente
        HttpClient client = HttpClient.newHttpClient();

        File file = new File("src/api_key.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        // criando request
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(reader.readLine()))
                .GET()
                .build();

        // Armazenando a resposta
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());


        System.out.println(response.body());
    }
}

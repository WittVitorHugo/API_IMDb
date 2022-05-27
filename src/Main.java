import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static String parseJsonMovies(String json, String begin, String end) {
        // Extraindo o JSON em uma substring
        return json.substring(json.indexOf(begin), json.indexOf(end));
    }

    // Extraindo titulo, urlImagem, ano, notas (titles, urlImges, year, imDbRating) e armazenando em listas diferentes
    public static List<String> parseMoviesElements(String[] movies, String beginStr, String endStr ) {

        List<String> lista = new ArrayList<>();

        for (String movie:movies
             ) {
            Pattern beginPattern = Pattern.compile(beginStr + "\":\"");
            Matcher beginMatcher = beginPattern.matcher(movie);

            Pattern endPattern = Pattern.compile("\",\"" + endStr);
            Matcher endMatcher = endPattern.matcher(movie);

            int begin = 0, end = 0;

            if (beginMatcher.find()) begin = beginMatcher.end();
            if (endMatcher.find()) end = endMatcher.start();

            lista.add(movie.substring(begin, end));
        }
        return lista;
    }

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
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();


        String str = parseJsonMovies(json, "[", "]");

        // Separando cada filme em um array
        String[] moviesJson = str.split("},\\{");

        List<String> titles = parseMoviesElements(moviesJson, "title", "fullTitle");
        List<String> urlImages = parseMoviesElements(moviesJson, "image", "crew");
        List<String> year = parseMoviesElements(moviesJson, "year", "image");
        List<String> imDbRating = parseMoviesElements(moviesJson, "imDbRating", "imDbRatingCount");

    }
}

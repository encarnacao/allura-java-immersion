import java.io.FileInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class App {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream("config.ini"));
        String apiKey = props.getProperty("API_KEY");
        String baseUrl = "https://imdb-api.com/en/API/Top250Movies/";
        String url = baseUrl + apiKey;
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        JsonParser parser = new JsonParser();
        List<Map<String, String>> dataList = parser.parse(body);
        for (Map<String,String> movie: dataList){
            System.out.println(movie.get("title"));
            System.out.println(movie.get("imDbRating"));
            System.out.println();
        }
    }
}

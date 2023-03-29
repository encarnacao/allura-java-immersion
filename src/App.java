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
        String baseUrl = "https://imdb-api.com/en/API/MostPopularMovies/";
        String url = baseUrl + apiKey;
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        JsonParser parser = new JsonParser();
        List<Map<String, String>> dataList = parser.parse(body);
        for (Map<String,String> movie: dataList){
            System.out.println(new BoldText("Titulo: ")+movie.get("title"));
            System.out.println(new BoldText("Poster: ")+movie.get("image"));

            int roundRating;
            try {
                String imdbRating = movie.get("imDbRating");
                Float rating = Float.parseFloat(imdbRating);
                roundRating = Math.round(rating);
                String nota = "Nota: "+ imdbRating;
                var ratingObj = new Rating(nota, roundRating);
                System.out.println(ratingObj);
            } catch (Exception e){
                System.out.println("imdb rating not available");
                roundRating = 0;
            }
            String stars = "⭐".repeat(roundRating);
            System.out.println(stars);
            System.out.println();
        }
    }
}

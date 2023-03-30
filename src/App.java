import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Make the connection with the data and search all the movies
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        var dress = URI.create(url);
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(dress).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Divide the response to show the rank, title, image and rating
        var parser = new JsonParser();
        List<Map<String,String>> movieList = parser.parse(body);
        
        // Show the movie list how I want
        var sGenerator = new StickerGenerator();
        for (Map<String,String> filme : movieList) {
            String title = filme.get("title");
            String rating = filme.get("imDbRating");
            InputStream urlImage = new URL(filme.get("image")).openStream();
            String nomeArquivo = "saida/" + new File(title.replace(":", "-")) + ".png";
            sGenerator.create(urlImage, rating, nomeArquivo);
            
            System.out.println("\033[32;1m" + "Rank: " + "\033[0m" + filme.get("rank"));
            System.out.println("\033[32;1m" + "Movie Title: " + "\033[0m" + title);
            System.out.println("\033[32;1m" + "Folder: " + "\033[0m" + filme.get("image"));
            System.out.print("\033[46;1m" + " Rating: " + rating + " \033[0m ");
            
            double ratingDouble = Double.parseDouble(filme.get("imDbRating"));
            int ratingInt = (int) Math.round(ratingDouble/2);
            
            for (int i = 0; i < ratingInt; i++){
                System.out.print("\u2B50");
            }

            System.out.println("\n\n");
        }

    }
}
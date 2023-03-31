import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Make the connection with the data and search
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // ExtractContent extractor = new ExtractContentIMDB();
        
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2023-03-30&end_date=2023-03-31";
        ExtractContent extractor = new ExtractContentNasa();

        var clientHttp = new ClientHttp();
        String body = clientHttp.searchData(url);

        // Show the movie list how I want
        List<Contents> contentList = extractor.extract(body);
        var sGenerator = new StickerGenerator();
        
        for (int i = 0; i < contentList.size(); i++) {
            Contents content = contentList.get(i);
            
            String rank = content.getRank();
            String title = content.getTitle();
            String image = content.getUlrImage();
            String rating = content.getRating();
            String nomeArquivo = "saida/" + new File(title.replace(":", "-")) + ".png";
            
            InputStream urlImage = new URL(image).openStream();
            sGenerator.create(urlImage, rating, nomeArquivo);
            
            System.out.println("\033[32;1m" + "Rank: " + "\033[0m" + rank);
            System.out.println("\033[32;1m" + "Movie Title: " + "\033[0m" + title);
            System.out.println("\033[32;1m" + "Folder: " + "\033[0m" + image);
            System.out.print("\033[46;1m" + " Rating: " + rating + " \033[0m ");
            
            double ratingDouble = 0.0;
            if(rating != null){
                ratingDouble = Double.parseDouble(rating);
            }

            int ratingInt = (int) Math.round(ratingDouble / 2);
            String stars = "\u2B50".repeat(ratingInt);
            System.out.print(stars);

            System.out.println("\n\n");
        }

    }
}
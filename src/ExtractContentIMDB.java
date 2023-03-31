import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtractContentIMDB implements ExtractContent {
    
    public List<Contents> extract(String json){
        // Divide the response to show the rank, title, image and rating
        var parser = new JsonParser();
        List<Map<String, String>> atributesList = parser.parse(json);

        List<Contents> contents = new ArrayList<>();
        for (Map<String, String> atributes : atributesList) {
            String rank = atributes.get("rank");
            String title = atributes.get("title");
            String image = atributes.get("image");
            String rating = atributes.get("imDbRating");
            var content = new Contents(rank, title, image, rating);

            contents.add(content);
        }    
        return contents;

    }
}

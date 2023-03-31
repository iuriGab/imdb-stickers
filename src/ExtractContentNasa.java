import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtractContentNasa implements ExtractContent {
    
    public List<Contents> extract(String json){
        // Divide the response to show the rank, title, image and rating
        var parser = new JsonParser();
        List<Map<String, String>> atributesList = parser.parse(json);

        List<Contents> contents = new ArrayList<>();
        for (Map<String, String> atributes : atributesList) {
            String title = atributes.get("title");
            String image = atributes.get("url");

            var content = new Contents(null, title, image, null);

            contents.add(content);
        }    
        return contents;

    }
}

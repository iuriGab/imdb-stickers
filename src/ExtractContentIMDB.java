import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtractContentIMDB implements ExtractContent {
    
    private enum JsonAttribute {
        RANK("rank"),
        TITLE("title"),
        IMAGE("image"),
        RATING("imDbRating");
        
        private final String key;
        
        JsonAttribute(String key) {
            this.key = key;
        }
        
        public String getKey() {
            return key;
        }
    }

    public List<Contents> extract(String json){
        // Divide the response to show the rank, title, image and rating
        var parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        List<Contents> contents = new ArrayList<>();
        for (Map<String, String> attributes : attributesList) {
            String rank = attributes.get(JsonAttribute.RANK.getKey());
            String title = attributes.get(JsonAttribute.TITLE.getKey());
            String image = attributes.get(JsonAttribute.IMAGE.getKey());
            String rating = attributes.get(JsonAttribute.RATING.getKey());
            var content = new Contents(rank, title, image, rating);

            contents.add(content);
        }    
        return contents;

    }
}

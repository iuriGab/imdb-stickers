import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtractContentNasa implements ExtractContent {
   
    private enum JsonAttribute {
        TITLE("title"),
        IMAGE("url");

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
            String title = attributes.get(JsonAttribute.TITLE.getKey());
            String image = attributes.get(JsonAttribute.IMAGE.getKey());

            var content = new Contents(null, title, image, null);

            contents.add(content);
        }    
        return contents;

    }
}

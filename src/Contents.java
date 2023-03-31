
public class Contents {
    
    private final String rank;
    private final String title;
    private final String ulrImage;
    private final String rating;

    public Contents(String rank, String title, String ulrImage, String rating) {
        this.rank = rank;
        this.title = title;
        this.ulrImage = ulrImage;
        this.rating = rating;
    }
    
    public String getRank() {
        return rank;
    }

    public String getTitle() {
        return title;
    }

    public String getUlrImage() {
        return ulrImage;
    }

    public String getRating() {
        return rating;
    }
    
}

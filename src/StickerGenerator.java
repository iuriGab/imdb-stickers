import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {
    
    //Methode to resize the image for default
    private class ImageResizer {
        public static BufferedImage resize(BufferedImage image, int newWidth, int newHeight) {
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, image.getType());
            Graphics2D g = resizedImage.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance((double) newWidth / width, (double) newHeight / height);
            g.drawRenderedImage(image, at);
            g.dispose();
            return resizedImage;
        }
    }
    
    public void create(InputStream inputStream, String rating, String nomeArquivo) throws Exception{
        //read the image
        BufferedImage originalImage = ImageIO.read(inputStream);
        
        //If rating is != null, this is because I'm using the IMDB API. So I'll resizing the images to have a default's size 
        if(rating != null){
            originalImage = StickerGenerator.ImageResizer.resize(originalImage, 1396, 2378);
        }

        //create new image with new dimensions and translucent
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int marginHeight = (int) (height * 0.2);
        int newHeight = height + marginHeight;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
    
        //copy the original image into the new image
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);
    
        //Font configuration
        graphics.setColor(Color.YELLOW);
        File fontImpact = new File("fonts/impact.ttf");
        var font = Font.createFont(Font.TRUETYPE_FONT, fontImpact);
        graphics.setFont(font);
        
        //config text according to rating
        String text = "TOPZERA";
        Double ratingDouble = 0.0;
        int fontSize = font.getSize();
        int maxWidth = originalImage.getWidth() - 50;
        FontMetrics fm = graphics.getFontMetrics(font);

        if (rating != null){
            ratingDouble = Double.parseDouble(rating);
        }
        
        var surprisedMan = ImageIO.read(new File("imgs/supresed-man.png"));
        if(ratingDouble > 8.9){
            text = "SUPER TOPZERA";
            graphics.drawImage(surprisedMan, 0, (int) (newHeight * 0.7), null);
        }

        while (fm.stringWidth(text) > maxWidth) {
            fontSize--;
            font = font.deriveFont((float) fontSize);
            graphics.setFont(font);
            fm = graphics.getFontMetrics();
        }
        
        while (fm.stringWidth(text) < maxWidth) {
            fontSize++;
            font = font.deriveFont((float) fontSize);
            graphics.setFont(font);
            fm = graphics.getFontMetrics();
        }
        
        //Make a limit for the text position 
        Rectangle2D bounds = fm.getStringBounds(text, graphics);
        int xText = (int) ((originalImage.getWidth() - bounds.getWidth())/2);
        int yText = newHeight-20;
        
        //Set the text into the new image
        graphics.drawString(text, xText, yText);
        
        //save the new image into a file
        ImageIO.write(newImage, "png", new File(nomeArquivo));
        
    }
    
}
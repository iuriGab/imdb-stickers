import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {
    
    public void criate(InputStream inputStream, String nomeArquivo) throws Exception{
        //read the image
        BufferedImage originalImage = ImageIO.read(inputStream);
    
        //create new image with new dimensions and translucent
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int marginHeight = (int) (height * 0.2);
        int newHeight = height + marginHeight;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
    
        //copy the original image into the new image
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, marginHeight/2, null);
    
        //Font configuration
        graphics.setColor(Color.YELLOW);
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 150);
        graphics.setFont(font);
        String text = "TOPZERA";
        int fontSize = font.getSize();
        int maxWidth = originalImage.getWidth() - 50;
        FontMetrics fm = graphics.getFontMetrics(font);
    
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
    
        Rectangle2D bounds = fm.getStringBounds(text, graphics);
        int x = (int) ((originalImage.getWidth() - bounds.getWidth())/2);
        
        //calculate the y coordinate to align the text to the vertical center of the new image
        int y = (newHeight - marginHeight) / 2 + fm.getAscent() - fm.getDescent();
        
        //create a text into the new image
        graphics.drawString(text, x, y);
        
        //save the new image
        ImageIO.write(newImage, "png", new File(nomeArquivo));
        
    }
    

}
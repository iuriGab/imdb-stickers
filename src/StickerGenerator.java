import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {
    
    public void criate(InputStream inputStream, String nomeArquivo) throws Exception{
        //read the image
        // InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);

        //create new image with new dimensions and translucent
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 600;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        //copy the original image into the new image
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        //Font configuration
        graphics.setColor(Color.YELLOW);
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 300);
        graphics.setFont(font);
        
        //create a text into the new image
        graphics.drawString("TOPZERA", 100, newHeight - 100);
        
        //save the new image
        ImageIO.write(newImage, "png", new File(nomeArquivo));
        
    }

}
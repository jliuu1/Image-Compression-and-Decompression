import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) throws MalformedURLException, IOException {
        String path = "testing.jpeg";
        BufferedImage bf = ImageIO.read(new File(path));
        Picture pic = new Picture(bf);

        double[][] picR = pic.getRMatrix();
        double[][] picG = pic.getGMatrix();
        double[][] picB = pic.getBMatrix();

        Compress compR = new Compress(picR);
        Compress compG = new Compress(picG);
        Compress compB = new Compress(picB);

        int numberOfSVDs = 5;
        double[][] rInfo = compR.getReducedImage(numberOfSVDs);
        double[][] gInfo = compG.getReducedImage(numberOfSVDs);
        double[][] bInfo = compB.getReducedImage(numberOfSVDs);
        Pixel[][] imagePixel = new Pixel[picR.length][picR[0].length];
        for (int i = 0; i < rInfo.length; ++i) {
            for (int j = 0; j < rInfo[0].length; ++j) {
                int red = (int) rInfo[i][j];
                int green = (int) gInfo[i][j];
                int blue = (int) bInfo[i][j];
                red = red > 255 ? 255 : red < 0 ? 0 : red;
                green = green > 255 ? 255 : green < 0 ? 0 : green;
                blue = blue > 255 ? 255 : blue < 0 ? 0 : blue;

                Pixel pixel = new Pixel(new Color(red, green, blue));
                imagePixel[i][j] = pixel;
            }
        }
        Picture newPic = new Picture(imagePixel);
        int width = newPic.getWidth();
        int height = newPic.getHeight();

        ImageRenderer rend = new ImageRenderer();
        rend.initialize(width, height);
        rend.renderFrame(newPic.getPixels());

        System.out.println(compR);
    }
}
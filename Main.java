import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.awt.Color;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws MalformedURLException, IOException {
        // ImageRenderer rend = new ImageRenderer();

        // String path = "person.jpeg";
        // BufferedImage bf = ImageIO.read(new File(path));
        // Picture pic = new Picture(bf);
        // Pixel[][] image = pic.getPixels();

        // rend.initialize(image.length, image[0].length);
        // rend.renderFrame(image);

        // rend.initialize(256, 256);

        // Pixel[][] image = new Pixel[256][256];
        // for (int x = 0; x < 256; ++x) {
        // for (int y = 0; y < 256; ++y) {
        // image[x][y] = new Pixel(new Color(x, y, y));
        // }
        // }
        // rend.renderFrame(image);

        // Picture pic = new Picture(image);
        // System.out.println(pic.getRGBMatrix()[0][255][0]);

        String path = "water_lily.jpeg";
        BufferedImage bf = ImageIO.read(new File(path));
        Picture pic1 = new Picture(bf);
        Picture pic2 = new Picture(bf);

        Comparator comp = new Comparator(pic1);
        System.out.println(comp.compareSVD(pic2));
    }
}
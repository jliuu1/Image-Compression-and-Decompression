import java.util.Random;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ShapeGenerator {
    final int epsilon = 10000;
    final double epsilon_2 = 0.05;
    private Picture image;
    private double baseCost;
    private Random rand;
    private Comparator comp;

    private int width, height;

    private Pixel[][] shapes;
    private ImageRenderer rend;

    public ShapeGenerator(Picture image) {
        this.image = image;
        this.rand = new Random();

        this.width = image.getWidth();
        this.height = image.getHeight();

        this.rend = new ImageRenderer();
        this.rend.initialize(this.width, this.height);
        this.shapes = new Pixel[this.width][this.height];

        for (int x = 0; x < this.width; ++x) {
            for (int y = 0; y < this.height; ++y) {
                shapes[x][y] = new Pixel(new Color(255, 255, 255));
            }
        }

        this.comp = new Comparator(this.image);
        this.baseCost = this.comp.compareSVD(new Picture(this.shapes));
        this.rend.renderFrame(shapes);
    }

    public void addShape() {
        int r = RandomUtils.uniform(rand, 255);
        int g = RandomUtils.uniform(rand, 255);
        int b = RandomUtils.uniform(rand, 255);
        int opacity = RandomUtils.uniform(rand, 127);
        Color randColor = new Color(r, g, b, opacity);
        StdDraw.setPenColor(randColor);

        double x1 = (double) RandomUtils.uniform(rand, this.width);
        double x2 = (double) RandomUtils.uniform(rand, this.width);
        double x3 = (double) RandomUtils.uniform(rand, this.width);
        double y1 = (double) RandomUtils.uniform(rand, this.height);
        double y2 = (double) RandomUtils.uniform(rand, this.height);
        double y3 = (double) RandomUtils.uniform(rand, this.height);
        double[] x = { x1, x2, x3 };
        double[] y = { y1, y2, y3 };
        StdDraw.filledPolygon(x, y);
        StdDraw.show();
    }

    public void addShapes() throws IOException {
        StdDraw.save("intermediate_buffer.jpg");
        double cost;

        for (int i = 0; i < 250; ++i) {
            addShape();

            StdDraw.save("intermediate_buffer2.jpg");
            String newPath = "intermediate_buffer2.jpg";
            BufferedImage newPic = ImageIO.read(new File(newPath));
            Picture newKek = new Picture(newPic);

            cost = this.comp.compareSVD(newKek);
            System.out.println(baseCost);

            if (cost < baseCost * (1 - epsilon_2)) {
                StdDraw.save("intermediate_buffer.jpg");
                this.baseCost = cost;
            } else {
                String path = "intermediate_buffer.jpg";
                BufferedImage oldPic = ImageIO.read(new File(path));
                Picture old = new Picture(oldPic);
                this.shapes = old.getPixels();
                this.rend.renderFrame(shapes);
            }
        }
    }

    public Pixel[][] getShapes() {
        return this.shapes;
    }
}

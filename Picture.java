import java.awt.image.BufferedImage;
import java.awt.Color;

public class Picture {
    private int width, height;
    private Pixel[][] image;
    private double[][] rMatrix;
    private double[][] gMatrix;
    private double[][] bMatrix;

    public Picture(BufferedImage input) {
        this.width = input.getWidth();
        this.height = input.getHeight();
        this.image = new Pixel[width][height];

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                this.image[x][height - y - 1] = new Pixel(new Color(input.getRGB(x, y)));
            }
        }

        this.rMatrix = generateRMatrix();
        this.gMatrix = generateGMatrix();
        this.bMatrix = generateBMatrix();
    }

    public Picture(Pixel[][] inputArray) {
        this.width = inputArray.length;
        this.height = inputArray[0].length;
        this.image = inputArray;

        this.rMatrix = generateRMatrix();
        this.gMatrix = generateGMatrix();
        this.bMatrix = generateBMatrix();
    }

    public Pixel[][] getPixels() {
        return this.image;
    }

    private double[][] generateRMatrix() {
        double[][] rMatrix = new double[this.width][this.height];
        for (int y = 0; y < this.height; ++y) {
            for (int x = 0; x < this.width; ++x) {
                rMatrix[x][y] = (double) this.image[x][y].getColor().getRed();
            }
        }
        return rMatrix;
    }

    private double[][] generateGMatrix() {
        double[][] gMatrix = new double[this.width][this.height];
        for (int y = 0; y < this.height; ++y) {
            for (int x = 0; x < this.width; ++x) {
                gMatrix[x][y] = (double) this.image[x][y].getColor().getGreen();
            }
        }
        return gMatrix;
    }

    private double[][] generateBMatrix() {
        double[][] bMatrix = new double[this.width][this.height];
        for (int y = 0; y < this.height; ++y) {
            for (int x = 0; x < this.width; ++x) {
                bMatrix[x][y] = (double) this.image[x][y].getColor().getBlue();
            }
        }
        return bMatrix;
    }

    public double[][] getRMatrix() {
        return this.rMatrix;
    }

    public double[][] getGMatrix() {
        return this.gMatrix;
    }

    public double[][] getBMatrix() {
        return this.bMatrix;
    }
}
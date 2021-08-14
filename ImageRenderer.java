import java.awt.Color;
import java.awt.Font;

public class ImageRenderer {
    private int width;
    private int height;

    public void initialize(int w, int h) {
        this.width = w;
        this.height = h;

        StdDraw.setCanvasSize(width, height);
        Font font = new Font("Monaco", Font.BOLD, 14);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        StdDraw.clear(new Color(0, 0, 0));

        StdDraw.enableDoubleBuffering();
        StdDraw.show();
    }

    public void renderFrame(Pixel[][] image) {
        int numXTiles = image.length;
        int numYTiles = image[0].length;

        StdDraw.clear(new Color(0, 0, 0));

        for (int x = 0; x < numXTiles; ++x) {
            for (int y = 0; y < numYTiles; ++y) {
                Pixel pixel = image[x][y];
                Color pixelColor = pixel.getColor();
                StdDraw.setPenColor(pixelColor);
                StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
            }
        }

        StdDraw.show();
    }
}
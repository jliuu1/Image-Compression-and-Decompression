import java.awt.Color;

public class Pixel {
    private Color color;

    public Pixel() {
        this.color = new Color(255, 255, 255);
    }

    public Pixel(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void changeColor(Color newColor) {
        this.color = newColor;
    }
}
// import java.util.Random;
// import java.awt.Color;
// import java.awt.image.BufferedImage;
// import javax.imageio.ImageIO;
// import javax.sound.midi.SysexMessage;

// import java.io.File;
// import java.io.IOException;

// public class ShapeGenerator {
// private double epsilon = 33000;
// private Picture image;
// private double baseCost;
// private Random rand;
// private Compress comp;
// private int fail;

// private int width, height;

// public ShapeGenerator(Picture image) {
// this.image = image;
// this.rand = new Random();

// StdDraw.save("temp.jpg");
// Picture tempPic = null;
// try {
// BufferedImage tempBI = ImageIO.read(new File("temp.jpg"));
// tempPic = new Picture(tempBI);
// } catch (IOException e) {
// System.exit(0);
// }

// this.comp = new Compress(this.image);
// }

// public void addShape() {
// int r = RandomUtils.uniform(rand, 255);
// int g = RandomUtils.uniform(rand, 255);
// int b = RandomUtils.uniform(rand, 255);
// int opacity = RandomUtils.uniform(rand, 127);
// Color randColor = new Color(r, g, b, opacity);
// StdDraw.setPenColor(randColor);

// double x1 = (double) RandomUtils.uniform(rand, this.width);
// double x2 = (double) RandomUtils.uniform(rand, this.width);
// double x3 = (double) RandomUtils.uniform(rand, this.width);
// double y1 = (double) RandomUtils.uniform(rand, this.height);
// double y2 = (double) RandomUtils.uniform(rand, this.height);
// double y3 = (double) RandomUtils.uniform(rand, this.height);
// double[] x = { x1, x2, x3 };
// double[] y = { y1, y2, y3 };
// StdDraw.filledPolygon(x, y);
// StdDraw.show();
// }

// public void addShapes() throws IOException {
// StdDraw.save("intermediate_buffer.jpg");
// double cost;
// for (int i = 0; i < 25000; ++i) {
// this.addShape();

// StdDraw.save("intermediate_buffer2.jpg");
// String newPath = "intermediate_buffer2.jpg";
// BufferedImage newPic = ImageIO.read(new File(newPath));
// Picture newKek = new Picture(newPic);

// cost = this.comp.compareSVD(newKek);
// System.out.print(cost + " ");
// System.out.println(epsilon);
// if (this.fail >= 50) {
// this.epsilon += 400;
// this.fail = 0;
// }
// if (cost < epsilon) {
// System.out.println("THIS WORKs");
// epsilon *= 0.81;
// System.out.println(epsilon);
// StdDraw.save("intermediate_buffer.jpg");
// this.baseCost = cost;
// this.fail = 0;
// // this.rend.renderFrame(new Picture(ImageIO.read(new
// // File("intermediate_buffer.jpg"))).getPixels());
// } else {
// this.fail += 1;
// StdDraw.picture(this.width / 2, this.height / 2, "intermediate_buffer2.jpg");
// StdDraw.show();
// }
// }
// }
// }

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import java.lang.Math;
import java.util.Arrays;
import java.awt.image.BufferedImage;

public class Compress {
    private Matrix image;

    private int width;
    private int height;

    private Matrix S;
    private Matrix U;
    private Matrix V;
    private double[] svd;

    public Compress(double[][] image) {
        this.height = image.length;
        this.width = image[0].length;

        this.image = new Matrix(image);
        if (this.height < this.width) {
            this.image = this.image.transpose();
        }

        SingularValueDecomposition svd = this.image.svd();
        this.U = svd.getU();
        this.S = svd.getS();
        this.V = svd.getV();
        this.svd = svd.getSingularValues();

        Matrix reconImg = this.U.times(this.S);
        reconImg = reconImg.times(this.V.transpose());
    }

    public double[][] getOriginalImage() {
        Matrix reconImg = this.U.times(this.S);
        reconImg = reconImg.times(this.V.transpose());
        return reconImg.getArray();
    }

    public double[][] getReducedImage(int numOfSVDs) {
        int maxSVDs = numOfSVDs > this.width ? this.width : numOfSVDs;
        Matrix newS = this.S;

        for (int i = 0; i < this.S.getRowDimension(); ++i) {
            for (int j = 0; j < this.S.getColumnDimension(); ++j) {
                if (i == j && i > maxSVDs) {
                    newS.set(i, j, 0);
                }
            }
        }

        Matrix reconImg = this.U.times(newS);
        reconImg = reconImg.times(this.V.transpose());

        return reconImg.getArray();
    }
}
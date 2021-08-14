import Jama.Matrix;
import Jama.SingularValueDecomposition;
import java.lang.Math;

public class Comparator {
    private Picture original;
    private double[] svdR;
    private double[] svdG;
    private double[] svdB;

    public Comparator(Picture original) {
        this.original = original;
        this.svdR = this.getSingularValues(this.original.getRMatrix());
        this.svdG = this.getSingularValues(this.original.getGMatrix());
        this.svdB = this.getSingularValues(this.original.getBMatrix());

    }

    public double compareSVD(Picture newImage) {
        double[] newRsvd = this.getSingularValues(newImage.getRMatrix());
        double[] newGsvd = this.getSingularValues(newImage.getGMatrix());
        double[] newBsvd = this.getSingularValues(newImage.getBMatrix());

        double rDist = 0;
        double gDist = 0;
        double bDist = 0;

        for (int i = 0; i < newRsvd.length; ++i) {
            rDist += Math.pow(svdR[i] - newRsvd[i], 2);
            gDist += Math.pow(svdG[i] - newGsvd[i], 2);
            bDist += Math.pow(svdB[i] - newBsvd[i], 2);
        }
        rDist = Math.sqrt(rDist);
        gDist = Math.sqrt(gDist);
        bDist = Math.sqrt(bDist);

        return rDist + gDist + bDist;
    }

    private double[] getSingularValues(double[][] input) {
        int m = input.length;
        int n = input[0].length;

        Matrix inputAsMatrix = new Matrix(input);
        if (m < n) {
            inputAsMatrix = inputAsMatrix.transpose();
        }
        SingularValueDecomposition svd = inputAsMatrix.svd();
        Matrix s = svd.getS();

        // int min = height > width ? width : height;
        int len = Math.min(m, n);
        double[] singularValues = new double[len];
        for (int i = 0; i < len; ++i) {
            singularValues[i] = s.get(i, i);
        }

        return singularValues;
    }
}
package tools;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import main.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * white letters    black letters
 *   hue 28-31		/ 32-105
 *   sat 255-255	/ 0-255
 *   val 255-255	/ 123-255
 */
public class HSVimgFactory {

    private static int HUE_MIN = 28;		// The Hue,Saturation, Value/Brightness (HSV) values with minimum and maximum boundaries
    private static int HUE_MAX = 31;	// HUE range: (0-180) and SAT/VAL range: (0-255)
    private static int SAT_MIN = 255;
    private static int SAT_MAX = 255;
    private static int VAL_MIN = 255;
    private static int VAL_MAX = 255;

    public static void main(String[] args) {
        HSVimgFactory imgFac = new HSVimgFactory();

        BufferedImage image = Utils.loadImage("resources/test_mainimgADJUSTED.png");
        BufferedImage img = imgFac.getHSVImage(image);
        Utils.storeImage(img, "resources/test_mainimgADJUSTED_bw.png");
    }

    /**
     * Returns a black/white BufferedImage using the given BufferedImage and the set HSV values.
     * @param image BufferedImage that needs to be processed
     * @return - black and white BufferedImage
     */
    public BufferedImage getHSVImage(BufferedImage image) {
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

        Mat mat = bufferedImageToMat(image);
        Mat mat2 = getFilteredMat(mat);
        BufferedImage bufImg = mat2Img(mat2);
        return bufImg;
    }

    //Return Mat from BufferedImage
    private Mat bufferedImageToMat(BufferedImage image) {
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }


    //Filters Mat to black and white image
    private Mat getFilteredMat(Mat mat) {
        Mat hsv = mat.clone();
        Scalar minc = new Scalar(HUE_MIN, SAT_MIN, VAL_MIN, 0);
        Scalar maxc = new Scalar(HUE_MAX, SAT_MAX, VAL_MAX, 0);
        Imgproc.cvtColor(mat, hsv, Imgproc.COLOR_BGR2HSV);
        Core.inRange(hsv, minc, maxc, hsv);
        return hsv;
    }

    //Reads Mat and returns BufferedImage
    private static BufferedImage mat2Img(Mat in) {
        BufferedImage out;
        byte[] data = new byte[in.cols() * in.rows() * (int)in.elemSize()];
        int type;
        in.get(0, 0, data);

        if(in.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
            System.out.println("GRAYSCALE");
        } else {
            type = BufferedImage.TYPE_3BYTE_BGR;
            System.out.println("BGR COLORED");
        }
        out = new BufferedImage(in.cols(), in.rows(), type);
        out.getRaster().setDataElements(0, 0, in.cols(), in.rows(), data);
        return out;
    }


    //===============================SETTERS===========================================
    public static void setHUE_MIN(int hUE_MIN) {
        HUE_MIN = hUE_MIN;
    }

    public static void setHUE_MAX(int hUE_MAX) {
        HUE_MAX = hUE_MAX;
    }

    public static void setSAT_MIN(int sAT_MIN) {
        SAT_MIN = sAT_MIN;
    }

    public static void setSAT_MAX(int sAT_MAX) {
        SAT_MAX = sAT_MAX;
    }

    public static void setVAL_MIN(int vAL_MIN) {
        VAL_MIN = vAL_MIN;
    }

    public static void setVAL_MAX(int vAL_MAX) {
        VAL_MAX = vAL_MAX;
    }

}
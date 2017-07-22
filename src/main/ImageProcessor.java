package main;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * white letters    black letters
 *   hue 28-31		/ 32-105
 *   sat 255-255	/ 0-255
 *   val 255-255	/ 123-255
 */
public class ImageProcessor {

    // INSTANCE VARIABLES
    private static final int HUE_MIN = 28;		// The Hue,Saturation, Value/Brightness (HSV) values with minimum and maximum boundaries
    private static final int HUE_MAX = 31;	    // HUE range: (0-180) and SAT/VAL range: (0-255)
    private static final int SAT_MIN = 255;
    private static final int SAT_MAX = 255;
    private static final int VAL_MIN = 255;
    private static final int VAL_MAX = 255;


    /** Returns all the characters from the filtered black and white image. */
    public List<BufferedImage> getCharacters(BufferedImage rawImage) {
        List<BufferedImage> chars = new ArrayList<>();
        BufferedImage image = getHSVImage(rawImage);

        List<Integer> rows = getCoordinates(image, image.getHeight(), image.getWidth(), false);
        List<Integer> cols = getCoordinates(image, image.getWidth(), image.getHeight(), true);

        int y = rows.get(0);
        int height = rows.get(1) - rows.get(0);
        for (int i = 0; i < cols.size(); i += 2) {
            chars.add(image.getSubimage(cols.get(i), y, cols.get(i + 1) - cols.get(i), height));
        }
        return chars;
    }

    /* Returns all the row or col interval values (dependent on size_1 dimension) which have a white pixel in it. */
    private List<Integer> getCoordinates(BufferedImage image, int size_1, int size_2, boolean cols_first) {
        List<Integer> result = new ArrayList<>();
        boolean already_found = false;
        boolean found = false;
        for (int a = 0; a < size_1; a++) {
            if (found) {
                already_found = true;
                found = false;
            }
            for (int b = 0; b < size_2; b++) {
                if (255 == (cols_first ? getColor(image, a, b) : getColor(image, b, a))) {
                    found = true;
                }
            }
            if (found && !already_found) {
                result.add(a);
            } else if (already_found && !found) {
                result.add(a);
                already_found = false;
            }
        }
        return result;
    }

    /* Returns the (blue) color value of the pixel. */
    private int getColor(BufferedImage image, int x, int y) {
        return image.getRGB(x, y) & 0xff;
    }

    /* Returns a black/white BufferedImage using the given BufferedImage and the set HSV values. */
    public BufferedImage getHSVImage(BufferedImage image) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat mat = img2Mat(image);
        Mat mat2 = getFilteredMat(mat);
        return mat2Img(mat2);
    }

    /* Converts a BufferedImage to a Mat data type. */
    private Mat img2Mat(BufferedImage image) {
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }

    /* Filters Mat to a black and white image. */
    private Mat getFilteredMat(Mat mat) {
        Mat hsv = mat.clone();
        Scalar minc = new Scalar(HUE_MIN, SAT_MIN, VAL_MIN, 0);
        Scalar maxc = new Scalar(HUE_MAX, SAT_MAX, VAL_MAX, 0);
        Imgproc.cvtColor(mat, hsv, Imgproc.COLOR_BGR2HSV);
        Core.inRange(hsv, minc, maxc, hsv);
        return hsv;
    }

    /* Converts a Mat to a BufferedImage. */
    private static BufferedImage mat2Img(Mat in) {
        BufferedImage out;
        byte[] data = new byte[in.cols() * in.rows() * (int)in.elemSize()];
        int type;
        in.get(0, 0, data);

        if (in.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        out = new BufferedImage(in.cols(), in.rows(), type);
        out.getRaster().setDataElements(0, 0, in.cols(), in.rows(), data);
        return out;
    }



    // ===================== EXECUTE CODE ===========================

    public static void main(String[] args) {
        ImageProcessor proc = new ImageProcessor();

        BufferedImage image = Utils.loadImage("resources/test_mainimgADJUSTED.png");
        BufferedImage img = proc.getHSVImage(image);
        Utils.storeImage(img, "resources/test_mainimgADJUSTED_bw.png");
        proc.getCharacters(img);
    }


}
package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for reading the word tiles and returning it as a String type.
 * Settings: min x = 20, max x = 1250, min y = 400, max y = 560
 */
public class Capturer {

    private Robot robot;
    private static List<BufferedImage> imageList;

    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Capturer() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        imageList = Utils.loadImages();
    }

    public String captureLetters() {
        return "nulepitc";  //TODO implement
    }


    public BufferedImage captureScreen() {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        return robot.createScreenCapture(screenRect);
    }


    public int countSubImages(BufferedImage img, BufferedImage subimg) {
        int count = 0;
        int width = subimg.getWidth();
        int heigth = subimg.getHeight();

        for (int y = 400; y < 560; y++) {
            for (int x = 20; x < 1250; x++) {
//                System.out.printf("(x,y) = (%d,%d)%n", x, y);
                if (imageEqual(img.getSubimage(x, y, width, heigth), subimg)) {
                    count++;
                    System.out.printf("(x,y) = (%d,%d)%n", x, y);
                }
            }
        }
        return count;
    }

    /**
     * Checks whether two BufferedImages are pixel perfectly equal, assuming equal size.
     */
    public boolean imageEqual(BufferedImage imgA, BufferedImage imgB) {
        for (int y = 0; y < imgA.getHeight(); y++) {
            for (int x = 0; x < imgA.getWidth(); x++) {
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                    if (x != 0 || y != 0) {
                        System.out.printf("FALSE (x,y) = (%d,%d)%n", x, y);
                        Utils.storeImage(imgA, "resources/test/x=" + x + "y=" + y + ".png");
                    }
                    return false;
                }
            }
        }
        return true;
    }



    // ===================== EXECUTE CODE ===========================

    public static void main(String[] args) throws InterruptedException {
        Capturer capturer = new Capturer();
//        BufferedImage img = capturer.captureScreen();

        Thread.sleep(1000);
        BufferedImage subimg = imageList.get(4); // LETTER E
//        BufferedImage mainimg = Utils.loadImage("resources/test_img.png");

        BufferedImage mainimg = capturer.captureScreen();
        System.out.println(capturer.countSubImages(mainimg, subimg));


        Utils.storeImage(mainimg, "resources/test_mainimg.png");
        Utils.storeImage(subimg, "resources/test_subimg.png");
    }

}

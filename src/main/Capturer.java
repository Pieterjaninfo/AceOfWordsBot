package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Class for reading the word tiles and returning it as a String type.
 * Settings: min x = 20, max x = 1250, min y = 400, max y = 560
 */
public class Capturer {

    private Robot robot;
    private static List<BufferedImage> charsList;

    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Capturer() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        charsList = Utils.loadCharacters();
    }

    public String captureLetters() {
        return "nulepitc";  //TODO implement
    }


    public BufferedImage captureScreen() {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        return robot.createScreenCapture(screenRect);
    }


    /**
     * Checks whether two BufferedImages are pixel perfectly equal, assuming equal size.
     */
    public boolean imageEqual(BufferedImage imgA, BufferedImage imgB) {
        for (int y = 0; y < imgA.getHeight(); y++) {
            for (int x = 0; x < imgA.getWidth(); x++) {
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
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
                } else {
//                    Utils.storeImage(img.getSubimage(x, y, width, heigth), "resources/test/x=" + x + "y=" + y + ".png");
                }
            }
        }
        return count;
    }



    // ===================== EXECUTE CODE ===========================

    public static void main(String[] args) throws InterruptedException {
        Capturer capturer = new Capturer();
        ImageProcessor proc = new ImageProcessor();

        Thread.sleep(1000);
        BufferedImage image = capturer.captureScreen().getSubimage(20, 400, 1230, 160);
        Utils.storeImage(image, "resources/chars/char.png");
        image = Utils.loadImage("resources/chars/char.png");
        List<BufferedImage> chars = proc.getCharacters(image);




    }

}

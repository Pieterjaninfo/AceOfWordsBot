package main;

import org.opencv.core.Mat;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Class for reading the word tiles and returning it as a String type.
 * Settings: min x = 20, max x = 1250, min y = 400, max y = 560
 */
public class Capturer {

    private Robot robot;
    private ImageProcessor proc;
    private static List<BufferedImage> charsList;

    private String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Capturer() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        proc = new ImageProcessor();
        charsList = Utils.loadCharacters();
    }

    /** Determines all the characters in the image. */
    public String captureLetters() {
        StringBuilder result = new StringBuilder();

        BufferedImage image = captureScreen().getSubimage(20, 400, 1230, 160);
        Utils.storeImage(image, "resources/chars/char.png");
        image = Utils.loadImage("resources/chars/char.png");
        List<BufferedImage> chars = proc.getCharacters(image);

        for (BufferedImage character : chars) {
            result.append(determineCharacter(character));
        }
        return result.toString().toLowerCase();
    }

    /* Captures the main screen. */
    private BufferedImage captureScreen() {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        return robot.createScreenCapture(screenRect);
    }


    /* Returns the amount of pixel deviations the second image has with the first image. */
    private int pixelDeviations(BufferedImage imgA, BufferedImage imgB) {
        int width = Math.min(imgA.getWidth(), imgB.getWidth());
        int heigth = Math.min(imgA.getHeight(), imgB.getHeight());

        int errorCount = 0;
        for (int y = 0; y < heigth; y++) {
            for (int x = 0; x < width; x++) {
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                    errorCount++;
                }
            }
        }
        return errorCount;
    }

    /* Determines the most probable character which the image could represent. */
    private char determineCharacter(BufferedImage image) {
        int errors = Integer.MAX_VALUE;
        char result = '-';

        for (int i = 0; i < charsList.size(); i++) {
            int deviations = pixelDeviations(charsList.get(i), image);
            if (deviations < errors) {
                errors = deviations;
                result = letters.charAt(i);

            }
            System.out.println(letters.charAt(i) + " : " + deviations);
        }
        System.out.println("");
        return result;
    }


    // ===================== EXECUTE CODE ===========================

    public static void main(String[] args) throws InterruptedException {
        Capturer capturer = new Capturer();
        Thread.sleep(1000);
        System.out.println(capturer.captureLetters());
    }

}

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

        imageList = loadImages();
    }

    public String captureLetters() {
        return "nulepitc";
    }


    public BufferedImage captureScreen() {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        return robot.createScreenCapture(screenRect);
    }


    public void storeImage(BufferedImage image, String output) {
        try {
            ImageIO.write(image, "png", new File(output));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<BufferedImage> loadImages() {
        List<BufferedImage> images = new ArrayList<>();
        String path = "resources/letters/Letter_";
        for (int i = 0; i < letters.length(); i++) {
            try {
                System.out.println("path=" + path + letters.charAt(i) + ".png");
                images.add(ImageIO.read(new File(path + letters.charAt(i) + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return images;
    }

    public int subImage() {
        int count = 0;



        return count;
    }




    // ===================== EXECUTE CODE ===========================

    public static void main(String[] args) {
        Capturer capturer = new Capturer();
//        BufferedImage img = capturer.captureScreen();
//        capturer.storeImage(img, "resources/test_img.png");

        System.out.println(capturer.loadImages());

        BufferedImage img = imageList.get(4); // LETTER E



    }



}

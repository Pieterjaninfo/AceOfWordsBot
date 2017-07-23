package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Tools for working with .txt files (IO-handler).
 */
public class Utils {
    private Utils() {}
    private static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /** Reads a (.txt) file and returns a list of strings. */
    public static List<String> readFile(String input) {
        Scanner s = null;
        try {
            s = new Scanner(new File(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> list = new ArrayList<>();
        while (s.hasNext()){
//            list.add(s.next().toLowerCase().replace("\u0003", "").replace("\u0004","").replace("\u0005","").replace(
//                    "\u0006","").replace("\u0007","").replace("\b","")
//            );
            list.add(s.next());
        }
        s.close();
        return list;
    }

    /** Given a list of strings, will output a (.txt) file at the given output location */
    public static void writeFile(List<String> input, String output) {
        Path out = Paths.get(output);
        try {
            Files.write(out, input, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Stores the buffered image at the given output location. */
    public static void storeImage(BufferedImage image, String output) {
        String ext = output.substring(output.lastIndexOf(".") + 1);
        try {
            ImageIO.write(image, ext, new File(output));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Loads all the images in the chars folder which prepend with char_ and adds it to a list of images. */
    public static List<BufferedImage> loadCharacters() {
        List<BufferedImage> images = new ArrayList<>();
        String path = "resources/chars/char_";
        for (int i = 0; i < letters.length(); i++) {
            images.add(ImageProcessor.resizeImage(loadImage(path + letters.charAt(i) + ".png")));
        }
        return images;
    }

    /** Loads the image at the given input location. */
    public static BufferedImage loadImage(String input) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

}

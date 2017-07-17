package javas;

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
 * Tools for working with .txt files and for converting one data type into the other.
 */
public class Utils {
    private Utils() {}

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
//                    "\u0006","").replace("\b","").replace("\u0007","")
//            );
            list.add(s.next());
        }
        s.close();
        return list;
    }


    public static void writeFile(List<String> input, String output) {
        Path out = Paths.get(output);
        try {
            Files.write(out, input, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}

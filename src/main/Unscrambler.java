package main;

import java.util.*;


/**
 * Given an input returning all the possible words.
 */
public class Unscrambler {
    private static final List<String> DICT = Utils.readFile("resources/dictionary_english.txt");

    /** Returns all the possible words that can be build using the characters in the given string. */
    public static Set<String> retrieveWords(String letters) {
        String pattern = "[" + letters + "]+";
        Set<String> filteredWords = new HashSet<>();
        for (String word : DICT) {
            if (word.matches(pattern)) {
                filteredWords.add(word);
            }
        }

        // limit even further
        Set<String> furtherFilteredWords = new HashSet<>();
        for (String word : filteredWords) {
            String temp = word;

            for (char c : letters.toCharArray()) {
                if (temp.contains(c+"")) {
                    temp = temp.replaceFirst(""+c, "");
                }
            }

            if (temp.equals("")) {
                furtherFilteredWords.add(word);
            }
        }
        return furtherFilteredWords;
    }



    // ===================== EXECUTE CODE ===========================

    public static void main(String[] args) throws Exception {
        Set<String> words = retrieveWords("cacwaest");

        System.out.println(words.size());
        System.out.println("words.contains(\"ace\") = " + words.contains("ace"));
        System.out.println("DICT.contains(\"ace\") = " + DICT.contains("ace"));
        System.out.println("DICT.contains(\"\") = " + DICT.contains(""));

        System.out.println("DICT.get(0) = " + DICT.get(0));

        System.out.println("DICT.contains(\"act\") = " + DICT.contains("act"));
    }


}
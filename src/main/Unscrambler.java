package main;

import java.util.*;


/**
 * Given an input returning all the possible words.
 */
public class Unscrambler {
    private static final List<String> DICT = Utils.readFile("resources/dictionary_english.txt");


    public static void main(String[] args) throws Exception {
        System.out.println(retrieveWords("rujedose").size());
    }


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


}
package main;

import java.util.*;


/**
 * Given an input returning all the possible words.
 */
public class Unscrambler {
    private static final List<String> DICT = Utils.readFile("resources/dictionary_english.txt");


    public static void main(String[] args) throws Exception {
        System.out.println(retrieveWords("saneremm").size());


    }


    public static Set<String> retrieveWords(String letters) {
        String pattern = "[" + letters + "]+";
        Set<String> filteredWords = new HashSet<>();
        for (String word : DICT) {
            if (word.matches(pattern)) {
                filteredWords.add(word);
            }
        }


        Set<String> furtherFilteredWords = new HashSet<>();
        // limit even further
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


//    public static Set<String> permute(String key) {
//        Set<String> set = new TreeSet<>();
//        for (int i = 0; i < key.length(); i++) {
//            Set<String> temp = new HashSet<>();
//            char c = key.charAt(i);
//
//            for (String str : set) {
//                temp.add(str + c);
//            }
//            set.add(c+"");
//            set.addAll(temp);
//        }
//        return set;
//    }
//
//    public static Set<String> filterPermutations(String key) {
//        Set<String> words = new HashSet<>();
//
//        Set<String> permutations = permute(key);
//        for (String permutation : permutations) {
//            if (DICT.contains(permutation)) {
//                words.add(permutation);
//            }
//        }
//        return words;
//    }






}
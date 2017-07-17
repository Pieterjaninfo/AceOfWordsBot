package main;


public class Controller {
    public static void main(String[] args) throws InterruptedException {
        Presser presser = new Presser();
        Capturer capturer = new Capturer();

        while (true) {
            // Capture letters
            String letters = capturer.captureLetters();

            // Set words
            presser.setWords(Unscrambler.retrieveWords(letters));

            // Enter all the words
            presser.pressAllWords();
            presser.nextPuzzle();
        }
    }
}

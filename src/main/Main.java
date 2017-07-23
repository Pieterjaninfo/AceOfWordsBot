package main;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Presser presser = new Presser();
        Capturer capturer = new Capturer();

        Thread.sleep(1000);

        while (true) {
            // Capture letters
            String letters = capturer.captureLetters();
            System.out.println("letters = " + letters);

            // Set words
            presser.setWords(Unscrambler.retrieveWords(letters));

            // Enter all the words
            presser.pressAllWords();
            presser.nextPuzzle();
        }
    }

}

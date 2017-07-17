package main;


public class Controller {
    public static void main(String[] args) throws InterruptedException {
        Presser presser = new Presser();

//        presser.setWords(Unscrambler.retrieveWords("saneremm"));

//        Thread.sleep(1000);
//        presser.pressAllWords();

        presser.nextPuzzle();
    }
}

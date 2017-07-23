package main;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Set;

/**
 * Pressing the actual buttons in the game.
 */
public class Presser {
    private static final int DELAY = 100;
    private Robot robot;
    private Set<String> words;

    public Presser() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /** Set the to be typed words. */
    public void setWords(Set<String> words) {
        this.words = words;
    }

    /* Types one character. */
    private void pressKey(char key) {
        int keycode = KeyEvent.getExtendedKeyCodeForChar(key);
        robot.keyPress(keycode);
        robot.keyRelease(keycode);
    }

    /* Types one given word. */
    private void pressWord(String word) throws InterruptedException {
        for (int i = 0; i < word.length(); i++) {
            pressKey(word.charAt(i));
            Thread.sleep(DELAY);
        }
        Thread.sleep(DELAY);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    /** Types all the determined words. */
    public void pressAllWords() throws InterruptedException {
        Thread.sleep(1000);
        for (String word : words) {
            pressWord(word);
            Thread.sleep(DELAY*2);
        }
    }

    /** Clicks on the next puzzle button. */
    public void nextPuzzle() throws InterruptedException {
        Thread.sleep(6000);
        int x = 480;
        int y = 640;
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        Thread.sleep(DELAY);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        Thread.sleep(4000);
    }



    // ===================== EXECUTE CODE ===========================

    public static void main(String[] args) throws Exception {
        Thread.sleep(1000);
        new Presser().pressAllWords();
    }

}

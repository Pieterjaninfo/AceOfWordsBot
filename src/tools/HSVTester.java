package tools;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

public class HSVTester {

    public static void main(String[] args) {

        //INIT toolkit
        JFXPanel panel = new JFXPanel();

        ImageWindow imgWin = new ImageWindow();
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    imgWin.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ImageWindow imgWin2 = new ImageWindow();
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    imgWin2.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        SliderWindow slWin = new SliderWindow();
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    slWin.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HSVimgFactory imgFac = new HSVimgFactory();
        Clock clock = new Clock(slWin, imgWin, imgWin2, imgFac);

        clock.start();
    }

}
package tools;

import main.Utils;

import java.awt.image.BufferedImage;
import java.util.Date;

public class Clock extends Thread {

    public static final long TICK_LENGTH = 0;

    private boolean running = true;
    private SliderWindow sliderWindow;
    private ImageWindow imageWindow;
    private ImageWindow imgWin;
    private HSVimgFactory imgFactory;

    public Clock(SliderWindow sliderWindow, ImageWindow imageWindow, ImageWindow imgWin, HSVimgFactory imgFactory) {
        this.sliderWindow = sliderWindow;
        this.imageWindow = imageWindow;
        this.imgFactory = imgFactory;
        this.imgWin = imgWin;
    }

    public void run() {
        while (running) {
            long startTime = new Date().getTime();
            tick();
            waitForNextTick(startTime);
        }
    }

    private void waitForNextTick(long startTime) {
        long ammountOfTimeWeTook = new Date().getTime() - startTime;
        long timeToWait = TICK_LENGTH - ammountOfTimeWeTook;
        if (timeToWait > 0) {
            try {
                Thread.sleep(timeToWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void tick() {
        updateHSVValues();
        BufferedImage image = Utils.loadImage("resources/test_mainimgADJUSTED.png");
        BufferedImage hsvImage = imgFactory.getHSVImage(image);
        imageWindow.showImage(hsvImage);
        imgWin.showImage(image);

        Utils.storeImage(hsvImage, "resources/test_blackwhite.png");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateHSVValues() {
        imgFactory.setHUE_MIN(sliderWindow.getHueMin());
        imgFactory.setHUE_MAX(sliderWindow.getHueMax());
        imgFactory.setSAT_MIN(sliderWindow.getSatMin());
        imgFactory.setSAT_MAX(sliderWindow.getSatMax());
        imgFactory.setVAL_MIN(sliderWindow.getValMin());
        imgFactory.setVAL_MAX(sliderWindow.getValMax());
    }

}
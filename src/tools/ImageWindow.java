package tools;

import java.awt.image.BufferedImage;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ImageWindow extends Application {

    private ImageView imgView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setHeight(720);
        primaryStage.setWidth(1280);

        StackPane root = new StackPane();

        imgView = new ImageView();
        root.getChildren().add(imgView);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showImage(BufferedImage bImage) {
        if (imgView != null) {
            imgView.setImage(SwingFXUtils.toFXImage(bImage, null));
        }
    }
}
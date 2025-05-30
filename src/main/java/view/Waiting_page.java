package view;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Waiting_page extends VBox {

    public Waiting_page(Stage primaryStage) {
        Image backgroundImage = new Image("file:67a2c6896733dbcf816ea713607a1aab.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true)
        );
        this.setBackground(new Background(background));

        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");

        Label waitLabel = new Label("لطفاً منتظر بمانید...");
        waitLabel.setStyle("-fx-font-size: 60px; -fx-text-fill: rgb(255,255,255);-fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 8, 0, 4, 4);");
        waitLabel.setFont(new Font("Arial", 20));

        Label dot1 = new Label(".");
        dot1.setFont(new Font("Arial", 20));
        Label dot2 = new Label(".");
        dot2.setFont(new Font("Arial", 20));
        Label dot3 = new Label(".");
        dot3.setFont(new Font("Arial", 20));
        dot1.setStyle("-fx-font-size: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;-fx-font-style: italic;-fx-font-size: 90px;-fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 8, 0, 4, 4);");
        dot2.setStyle("-fx-font-size: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;-fx-font-style: italic;-fx-font-size: 90px;-fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 8, 0, 4, 4);");
        dot3.setStyle("-fx-font-size: 50px; -fx-text-fill: #ffffff;-fx-font-weight: bold;-fx-font-style: italic;-fx-font-size: 90px;-fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 8, 0, 4, 4);");

        createFadeAnimation(dot1, 0, 1).play();
        createFadeAnimation(dot2, 300, 1).play();
        createFadeAnimation(dot3, 600, 1).play();

        HBox dotsBox = new HBox(10, dot1, dot2, dot3);
        dotsBox.setAlignment(javafx.geometry.Pos.CENTER);

        this.setAlignment(javafx.geometry.Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(waitLabel, dotsBox);
    }

    private FadeTransition createFadeAnimation(Label dot, int delay, int cycleCount) {
        FadeTransition fade = new FadeTransition(Duration.millis(800), dot);
        fade.setFromValue(0.3);
        fade.setToValue(1);
        fade.setCycleCount(FadeTransition.INDEFINITE);
        fade.setAutoReverse(true);
        fade.setDelay(Duration.millis(delay));
        return fade;
    }
}

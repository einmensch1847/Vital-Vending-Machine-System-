package com.sadra.uni_project;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.AdminPanel;
import view.MainView;
import view.PasswordDialog;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {

        try {
            MainView mainView = new MainView(primaryStage);
            Scene mainScene = new Scene(mainView, 800, 600);

            StackPane root = new StackPane();
            root.getChildren().add(mainView);

            Rectangle overlay = new Rectangle(800, 600, Color.BLACK);
            overlay.setOpacity(1);
            root.getChildren().add(overlay);

            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.setFullScreen(true);
            primaryStage.show();

            FadeTransition fadeOut = new FadeTransition(Duration.millis(500), overlay);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(event -> root.getChildren().remove(overlay));
            fadeOut.play();

            primaryStage.setOnCloseRequest(event -> {
                event.consume();
                if (PasswordDialog.showPasswordDialog(primaryStage)) {
                    AdminPanel newPage = new AdminPanel(primaryStage);
                    switchSceneWithFadeTransition(primaryStage, newPage);
                    primaryStage.getScene().setRoot(newPage);
                    primaryStage.setFullScreen(true);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchSceneWithFadeTransition(Stage primaryStage, Parent newPage) {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3), primaryStage.getScene().getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        fadeOut.setOnFinished(event -> {
            primaryStage.getScene().setRoot(newPage);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3), primaryStage.getScene().getRoot());
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });

        fadeOut.play();
    }

    public static void main(String[] args) {
        launch();
    }
}
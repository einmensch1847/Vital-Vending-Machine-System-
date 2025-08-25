package view;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class btn6_Controller extends VBox {
    public double age, weight;
    public String gender;
    private Stage primaryStage;
    private ArduinoConnector arduino;

    public btn6_Controller(Stage primaryStage) {
//        **********************************************************************************

        //arduino = new ArduinoConnector("COM6"); // نام پورت آردوینوت رو به درستی وارد کن

//        **********************************************************************************
        this.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setFullScreen(true);

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

        VBox vBox = new VBox(30);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-padding: 40px;");

        VBox genderColumn = new VBox(20);
        genderColumn.setAlignment(Pos.CENTER);
        genderColumn.setStyle("-fx-background-color: rgba(46,46,46,0.2); -fx-padding: 20px; -fx-border-radius: 10px;-fx-background-radius: 10px;-fx-border-width: 10px;-fx-border-color: rgba(0, 0, 0, 0.3);-fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 8, 0, 4, 4);");

        Label genderLabel = new Label("جنسیت");
        genderLabel.setFont(Font.font("Arial", 25));
        genderLabel.setTextFill(Color.WHITE);

        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleButton = new RadioButton("مرد");
        maleButton.setToggleGroup(genderGroup);
        maleButton.setStyle("-fx-text-fill: white;-fx-font-size: 20px;-fx-font-weight: bold;");

        RadioButton femaleButton = new RadioButton(" زن");
        femaleButton.setToggleGroup(genderGroup);
        femaleButton.setStyle("-fx-text-fill: white;-fx-font-size: 20px;-fx-font-weight: bold;");

        genderColumn.getChildren().addAll(genderLabel, maleButton, femaleButton);

        VBox weightColumn = new VBox(20);
        weightColumn.setAlignment(Pos.CENTER);
        weightColumn.setStyle("-fx-background-color: rgba(46,46,46,0.2); -fx-padding: 20px; -fx-border-radius: 10px;-fx-background-radius: 10px;-fx-border-width: 10px;-fx-border-color: rgba(0, 0, 0, 0.3);-fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 8, 0, 4, 4);");

        Label weightLabel = new Label("وزن (kg)");
        weightLabel.setFont(Font.font("Arial", 25));
        weightLabel.setTextFill(Color.WHITE);

        Slider weightSlider = new Slider(10, 120, 50);
        weightSlider.setBlockIncrement(0.5);
        weightSlider.setMajorTickUnit(5);
        weightSlider.setMinorTickCount(9);
        weightSlider.setShowTickMarks(true);
        weightSlider.setShowTickLabels(true);

        weightSlider.setStyle(
                "-fx-font-size: 22px; " +
                        "-fx-control-inner-background: rgba(38,69,122,0.7);"
        );

        weightColumn.getChildren().addAll(weightLabel, weightSlider);

        VBox ageColumn = new VBox(20);
        ageColumn.setAlignment(Pos.CENTER);
        ageColumn.setStyle("-fx-background-color: rgba(46,46,46,0.2); -fx-padding: 20px; -fx-border-radius: 10px;-fx-background-radius: 10px;-fx-border-width: 10px;-fx-border-color: rgba(0, 0, 0, 0.3);-fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 8, 0, 4, 4);");

        Label ageLabel = new Label("سن");
        ageLabel.setFont(Font.font("Arial", 25));
        ageLabel.setTextFill(Color.WHITE);

        Slider ageSlider = new Slider(5, 70, 5);
        ageSlider.setBlockIncrement(1);
        ageSlider.setMajorTickUnit(5);
        ageSlider.setMinorTickCount(4);
        ageSlider.setShowTickMarks(true);
        ageSlider.setShowTickLabels(true);

        ageSlider.setStyle(
                "-fx-font-size: 22px; " +
                        "-fx-control-inner-background: rgba(38,69,122,0.7);"
        );

        ageColumn.getChildren().addAll(ageLabel, ageSlider);

        vBox.getChildren().addAll(genderColumn, weightColumn, ageColumn);

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setStyle("-fx-padding: 40px;");

        Button backButton = new Button("بازگشت");
        backButton.setStyle("-fx-font-size: 18px; -fx-background-color: rgba(255,99,71,0.85); -fx-text-fill: white; -fx-padding: 15px; -fx-border-radius: 8px;-fx-background-radius: 10px ; ");

        Button submitButton = new Button("ثبت");
        submitButton.setStyle("-fx-font-size: 18px; -fx-background-color: rgba(50,205,50,0.74); -fx-text-fill: white; -fx-padding: 15px; -fx-border-radius: 8px;-fx-background-radius: 10px;");

        buttonBox.getChildren().addAll(backButton, submitButton);

        this.getChildren().addAll(vBox, buttonBox);

        submitButton.setOnAction(event -> {

            MainView.btn6(true , primaryStage);

//            Waiting_page newPage = new Waiting_page(primaryStage);
////            Scene newScene = new Scene(newPage, 800, 600);
////            primaryStage.setScene(newScene);
////            primaryStage.setMaximized(true);
////            primaryStage.setFullScreen(true);
//            switchSceneWithFadeTransition(primaryStage, newPage);

            gender = genderGroup.getSelectedToggle() == maleButton ? "مرد" : "زن";
            weight = weightSlider.getValue();
            age = ageSlider.getValue();

            System.out.println("جنسیت: " + gender);
            System.out.println("وزن: " + weight);
            System.out.println("سن: " + age);

//            if (arduino != null) arduino.sendCommand("B6");

        });

        backButton.setOnAction(event -> {
            MainView newPage = new MainView(primaryStage);
//            Scene newScene = new Scene(newPage, 800, 600);
//            primaryStage.setScene(newScene);
//            primaryStage.setMaximized(true);
//            primaryStage.setFullScreen(true);
            switchSceneWithFadeTransition(primaryStage, newPage);
        });
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

}

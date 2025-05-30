package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Call_Admin extends VBox {
    public Call_Admin(Stage primaryStage) {


        Label waitLabel1 = new Label("لطفاً با پشتیبانی تماس بگیرید");
        waitLabel1.setStyle("-fx-font-size: 40px; -fx-text-fill: rgb(255,255,255);-fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 8, 0, 4, 4);");
        waitLabel1.setFont(new Font("Arial", 20));

        Label waitLabel2 = new Label("09144721384");
        waitLabel2.setStyle("-fx-font-size: 40px; -fx-text-fill: rgb(255,255,255);-fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 8, 0, 4, 4);");
        waitLabel2.setFont(new Font("Arial", 20));

        VBox vbox = new VBox(waitLabel1, waitLabel2);
        vbox.setSpacing(30);
        vbox.setStyle("-fx-background-color: rgba(70,130,180,0.63);-fx-background-radius: 10px");
        vbox.setAlignment(Pos.CENTER);

        HBox hbox = new HBox(vbox);
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-radius: 10px;");
        hbox.setSpacing(30);


        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");

        Image backgroundImage = new Image("file:67a2c6896733dbcf816ea713607a1aab.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true)
        );
        this.setBackground(new Background(background));
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(hbox);
    }
}

package view;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.animation.FadeTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.image.BufferedImage;

public class QRCodeGenerator extends VBox {

    private Stage primaryStage;
    private ImageView qrImageView;

    public QRCodeGenerator(Stage primaryStage, String paymentUrl) {
        this.primaryStage = primaryStage;
        initializeUI(paymentUrl);
    }

    private void initializeUI(String paymentUrl) {

        Button backButton = new Button("بازگشت");
        backButton.setStyle("-fx-font-size: 18px; -fx-background-color: rgba(255,99,71,0.85); -fx-text-fill: white; -fx-padding: 15px; -fx-border-radius: 8px;-fx-background-radius: 10px ; ");
        backButton.setOnAction(event -> {
            MainView newPage = new MainView(primaryStage);
            switchSceneWithFadeTransition(primaryStage, newPage);
        });

        Image backgroundImage = new Image("file:67a2c6896733dbcf816ea713607a1aab.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true)
        );
        this.setBackground(new Background(background));

        qrImageView = new ImageView();
        Text scanText = new Text("لطفا اسکن کنید تا به صفحه پرداخت بروید");
        scanText.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;-fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 1), 8, 0, 8, 8);");
        scanText.setFill(Color.WHITE);

        WritableImage qrImage = generateQRCode(paymentUrl, 300, 300);

        qrImageView.setImage(qrImage);
        qrImageView.setStyle("-fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 1), 8, 0, 8, 8);");

        HBox qrBox = new HBox(20, qrImageView, scanText);

        VBox vBox = new VBox(qrBox , backButton);
        vBox.setSpacing(30);

        qrBox.setStyle("-fx-alignment: center;");
        vBox.setStyle("-fx-alignment: center;");
        this.getChildren().addAll(vBox);
        this.setStyle("-fx-padding: 20; -fx-alignment: center;");
    }

    public void showStage() {
        Scene scene = new Scene(this, 400, 450);
        primaryStage.setTitle("QR Code Generator");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }

    private WritableImage generateQRCode(String text, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            return SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
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
}

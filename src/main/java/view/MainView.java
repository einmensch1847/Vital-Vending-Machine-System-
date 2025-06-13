package view;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;



public class MainView extends VBox {

//    public static int[] state = {1 , 1 , 1 , 1 , 1 , 1} ;

    private Button exitButton;
    private VBox btn1Box, btn2Box, btn3Box, btn4Box, btn5Box, btn6Box;
    private HBox footer;
    private ArduinoConnector arduino;
    private static MainView instance;


    public MainView(Stage primaryStage) {
        instance = this;
//        loadStateFromFile();

        Label timeLabel = new Label();
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalTime currentTime = LocalTime.now();
            timeLabel.setText(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }));

//        **********************************************************************************

        arduino = new ArduinoConnector("COM6"); // نام پورت آردوینوت رو به درستی وارد کن
        ArduinoCommandHandler handler = new ArduinoCommandHandler(primaryStage);

        arduino.setCommandListener(command -> {
            handler.handleCommand(command); // هر وقت آردوینو چیزی فرستاد، این اجرا میشه
        });


//        **********************************************************************************


        timeline1.setCycleCount(Timeline.INDEFINITE);
        timeline1.play();
        timeLabel.setFont(Font.font("Arial",50));
        timeLabel.setStyle("-fx-text-fill: STEELBLUE;-fx-font-weight: bold;-fx-font-style: italic;");

        primaryStage.setFullScreenExitHint("");
        exitButton = new Button("                         ");
        exitButton.setStyle("-fx-background-color: rgba(143,139,139,0); -fx-text-fill: rgba(255,255,255,0); -fx-font-size: 16px;");
        exitButton.setOnAction(e -> {
            boolean isPasswordCorrect = view.PasswordDialog.showPasswordDialog(primaryStage);
            if (isPasswordCorrect) {
                AdminPanel newPage = new AdminPanel(primaryStage);
                switchSceneWithFadeTransition(primaryStage, newPage);
                primaryStage.getScene().setRoot(newPage);
                primaryStage.setFullScreen(true);
            }
        });
        HBox exitContainer = new HBox(exitButton);
        exitContainer.setAlignment(Pos.TOP_RIGHT);

//        if(state[0] == 1) {
//            enableButton(1);
//        }else {
//            disableButton(1);
//        }
//        if(state[1] == 1) {
//            enableButton(1);
//        }else {
//            disableButton(1);
//        }
//        if(state[2] == 1) {
//            enableButton(1);
//        }else {
//            disableButton(1);
//        }
//        if(state[3] == 1) {
//            enableButton(1);
//        }else {
//            disableButton(1);
//        }
//        if(state[4] == 1) {
//            enableButton(1);
//        }else {
//            disableButton(1);
//        }
//        if(state[5] == 1) {
//            enableButton(1);
//        }else {
//            disableButton(1);
//        }
        btn1Box = createImageButton("file:21.png", "10000 تومان", e -> {
            Waiting_page newPage = new Waiting_page(primaryStage);
            switchSceneWithFadeTransition(primaryStage, newPage);
            try {
                if (arduino != null) arduino.sendCommand("BTN1");
            } catch (IOException ex) {
                System.err.println("خطا در ارسال فرمان به آردوینو: " + ex.getMessage());
            }
        });

        btn2Box = createImageButton("file:31.png", "12000 تومان", e -> {
            Waiting_page newPage = new Waiting_page(primaryStage);
            switchSceneWithFadeTransition(primaryStage, newPage);
            try {
                if (arduino != null) arduino.sendCommand("BTN2");
            } catch (IOException ex) {
                System.err.println("خطا در ارسال فرمان به آردوینو: " + ex.getMessage());
            }

        });

        btn3Box = createImageButton("file:41.png", "15000 تومان", e -> {
            Waiting_page newPage = new Waiting_page(primaryStage);
            switchSceneWithFadeTransition(primaryStage, newPage);
            try {
                if (arduino != null) arduino.sendCommand("BTN3");
            } catch (IOException ex) {
                System.err.println("خطا در ارسال فرمان به آردوینو: " + ex.getMessage());
            }

        });

        btn4Box = createImageButton("file:51.png", "11000 تومان", e -> {
            Waiting_page newPage = new Waiting_page(primaryStage);
            switchSceneWithFadeTransition(primaryStage, newPage);
            try {
                if (arduino != null) arduino.sendCommand("BTN4");
            } catch (IOException ex) {
                System.err.println("خطا در ارسال فرمان به آردوینو: " + ex.getMessage());
            }

        });

        btn5Box = createImageButton("file:61.png", "13000 تومان", e -> {
            Waiting_page newPage = new Waiting_page(primaryStage);
            switchSceneWithFadeTransition(primaryStage, newPage);
            try {
                if (arduino != null) arduino.sendCommand("BTN5");
            } catch (IOException ex) {
                System.err.println("خطا در ارسال فرمان به آردوینو: " + ex.getMessage());
            }

        });

        btn6Box = createImageButton("file:71.png", "18000 تومان", e -> {
            btn6_Controller newPage = new btn6_Controller(primaryStage);
            switchSceneWithFadeTransition(primaryStage, newPage);
        });


        HBox hbox1 = new HBox(10, btn1Box, btn2Box, btn3Box);
        HBox hbox2 = new HBox(10, btn4Box, btn5Box, btn6Box);

        hbox1.setAlignment(Pos.CENTER);
        hbox2.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, hbox1, hbox2);
        vbox.setAlignment(Pos.CENTER);
        VBox.setVgrow(vbox, Priority.ALWAYS);

        footer = createFooter();
//        disableButton(2);

        this.getChildren().addAll(timeLabel , vbox, exitContainer, footer);
        this.setSpacing(10);

        this.widthProperty().addListener((obs, oldVal, newVal) -> updateButtonSize(newVal.doubleValue(), this.getHeight()));
        this.heightProperty().addListener((obs, oldVal, newVal) -> updateButtonSize(this.getWidth(), newVal.doubleValue()));

        setBackgroundImage("file:67a2c6896733dbcf816ea713607a1aab.jpg");
    }

    private VBox createImageButton(String imagePath, String price, EventHandler<ActionEvent> eventHandler) {
        Button button = new Button();

        ImageView imageView = new ImageView(new Image(imagePath));

        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);

        button.setGraphic(imageView);
        button.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-border-color: transparent;");

        button.setOnAction(eventHandler);

        Label priceLabel = new Label(price);
        priceLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-font-style: italic; -fx-text-fill: white;");


        VBox vbox = new VBox( button, priceLabel);
        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }




    private HBox createFooter() {
        Text footerText = new Text("جهت ارتباط با پشتیبانی با این شماره تماس بگیرید : 09144721384");
        footerText.setFont(new Font(25));
        footerText.setFill(Color.DARKMAGENTA);
        footerText.setStroke(Color.WHITE);
        footerText.setStrokeWidth(0.8);
        footerText.setEffect(new DropShadow(4, 4, 4, Color.GRAY));

        ImageView logoView = new ImageView(new Image("file:1.png", 70, 70, true, true));
        logoView.setPreserveRatio(true);
        logoView.setFitHeight(69);

        HBox footerContent = new HBox(10, footerText, logoView);
        footerContent.setAlignment(Pos.CENTER);

        HBox footer = new HBox(footerContent);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(5, 15, 5, 15));
        footer.setBackground(new Background(new BackgroundFill(Color.STEELBLUE, new CornerRadii(1), null)));
        footer.setStyle("-fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 1), 8, 0, 8, 8);");

        return footer;
    }

    private void setBackgroundImage(String imagePath) {
        Image backgroundImage = new Image(imagePath);
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true)
        );
        this.setBackground(new Background(background));
    }

    public void updateButtonSize(double width, double height) {
        double btnWidth = width * 0.25;
        double btnHeight = height * 0.25;

        for (VBox btnBox : List.of(btn1Box, btn2Box, btn3Box, btn4Box, btn5Box, btn6Box)) {
            Button button = (Button) btnBox.getChildren().get(0);
            button.setPrefSize(btnWidth, btnHeight);
        }

        footer.setPrefHeight(height * 0.15);
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
    public static void disableButton(int btnNumber) {
//        state[btnNumber-1] = 0 ;
//        loadStateFromFile();
        if (instance == null) return;

        VBox targetBox = switch (btnNumber) {
            case 1 -> instance.btn1Box;
            case 2 -> instance.btn2Box;
            case 3 -> instance.btn3Box;
            case 4 -> instance.btn4Box;
            case 5 -> instance.btn5Box;
            case 6 -> instance.btn6Box;
            default -> null;
        };

        if (targetBox != null) {
            Button btn = (Button) targetBox.getChildren().get(0);
            btn.setDisable(true);
            btn.setOpacity(0.5);

            ColorAdjust grayscale = new ColorAdjust();
            grayscale.setSaturation(-1); // اعمال افکت سیاه‌وسفید
            btn.setEffect(grayscale);
        }
    }
    public static void enableButton(int btnNumber) {
//        state[btnNumber-1] = 0 ;
//        loadStateFromFile();
        if (instance == null) return;

        VBox targetBox = switch (btnNumber) {
            case 1 -> instance.btn1Box;
            case 2 -> instance.btn2Box;
            case 3 -> instance.btn3Box;
            case 4 -> instance.btn4Box;
            case 5 -> instance.btn5Box;
            case 6 -> instance.btn6Box;
            default -> null;
        };

        if (targetBox != null) {
            Button btn = (Button) targetBox.getChildren().get(0);
            btn.setDisable(false);
            btn.setOpacity(1.0);
            btn.setStyle("-fx-effect: none;"); // حذف فیلتر سیاه و سفید اگر اعمال شده
            btn.setGraphic(btn.getGraphic()); // ریست مجدد تصویر
        }
    }

//    public static void saveStateToFile() {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("state.txt"))) {
//            for (int i = 0; i < state.length; i++) {
//                writer.write(state[i] + (i < state.length - 1 ? "," : ""));
//            }
//        } catch (IOException e) {
//            System.err.println("خطا در ذخیره state: " + e.getMessage());
//        }
//    }
//
//    public static void loadStateFromFile() {
//        File file = new File("state.txt");
//        if (!file.exists()) return; // اگر فایل وجود ندارد، از مقدار پیش‌فرض استفاده شود
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line = reader.readLine();
//            if (line != null) {
//                String[] parts = line.split(",");
//                for (int i = 0; i < Math.min(parts.length, state.length); i++) {
//                    state[i] = Integer.parseInt(parts[i]);
//                }
//            }
//        } catch (IOException | NumberFormatException e) {
//            System.err.println("خطا در بارگذاری state: " + e.getMessage());
//        }
//    }
}

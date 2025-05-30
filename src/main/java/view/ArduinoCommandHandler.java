package view;

import javafx.application.Platform;
import javafx.stage.Stage;

public class ArduinoCommandHandler {

    private Stage primaryStage;

    public ArduinoCommandHandler(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void handleCommand(String command) {
        Platform.runLater(() -> {
            switch (command.trim()) {
                case "F":  // محصول تحویل داده شده
                    MainView mainView = new MainView(primaryStage);
                    primaryStage.getScene().setRoot(mainView);
                    break;

                case "E1":
                    MainView.disableButton(1);
                    break;
                case "E2":
                    MainView.disableButton(2);
                    break;
                case "E3":
                    MainView.disableButton(3);
                    break;
                case "E4":
                    MainView.disableButton(4);
                    break;
                case "E5":
                    MainView.disableButton(5);
                    break;
                case "E6":
                    MainView.disableButton(6);
                    break;

                case "ERR":  // مثلاً آردوینو خطای سخت‌افزاری یا اضطراری شناسایی کرده
                    Call_Admin callPage = new Call_Admin(primaryStage);
                    primaryStage.getScene().setRoot(callPage);
                    break;

                default:
                    System.out.println("فرمان ناشناخته از آردوینو: " + command);
                    break;
            }
        });
    }
}

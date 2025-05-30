package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdminPanel extends VBox {

    public AdminPanel(Stage primaryStage) {
        this.setSpacing(20);
        this.setPadding(new Insets(30));
        this.setAlignment(Pos.CENTER);

//        // دکمه‌های فعال‌سازی
//        for (int i = 1; i <= 6; i++) {
//            int btnNumber = i;
//            Button enableButton = new Button("فعال‌سازی دکمه شماره " + btnNumber);
//            enableButton.setPrefWidth(300);
//            enableButton.setOnAction(e -> {
//                MainView.state[btnNumber-1] = 1 ;
//                System.out.println(MainView.state[btnNumber-1]);
//                MainView.enableButton(btnNumber);
//            });
//            this.getChildren().add(enableButton);
//            Button disableButton = new Button("غیرفعال‌سازی دکمه شماره " + btnNumber);
//            disableButton.setPrefWidth(300);
//            disableButton.setOnAction(e -> {
//                MainView.state[btnNumber-1] = 0 ;
//                System.out.println(MainView.state[btnNumber-1]);
//                MainView.disableButton(btnNumber);
//            });
//            this.getChildren().add(disableButton);
//
//        }

        // دکمه بازگشت به صفحه اصلی
        Button backToMain = new Button("بازگشت به صفحه اصلی");
        backToMain.setPrefWidth(300);
        backToMain.setOnAction(e -> {
            MainView mainView = new MainView(primaryStage);
            primaryStage.getScene().setRoot(mainView); // بازگشت به صفحه اصلی
        });

        // دکمه خروج از برنامه
        Button exitApp = new Button("پایان برنامه");
        exitApp.setPrefWidth(300);
        exitApp.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;");
        exitApp.setOnAction(e -> {
            boolean isPasswordCorrect = view.PasswordDialog.showPasswordDialog(primaryStage);
            if (isPasswordCorrect) {
                primaryStage.close(); // بستن برنامه پس از تایید رمز
            }
        });

        this.getChildren().addAll(backToMain, exitApp);
    }
}

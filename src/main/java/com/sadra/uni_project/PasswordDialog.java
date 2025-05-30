package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PasswordDialog {

    private static final String CORRECT_PASSWORD = "1234";

    public static boolean showPasswordDialog(Stage ownerStage) {
        final boolean[] isAuthenticated = {false};  // با آرایه برای استفاده داخل lambda

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.setFullScreen(true);
        dialogStage.initOwner(ownerStage);  // مشخص‌کردن والد برای پشتیبانی بهتر

        Label label = new Label("لطفاً رمز عبور را وارد کنید:");
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(200);

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        // کی‌پد عددی
        GridPane keypad = new GridPane();
        keypad.setHgap(10);
        keypad.setVgap(10);
        keypad.setAlignment(Pos.CENTER);

        String[] keys = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        int row = 0, col = 0;
        for (String key : keys) {
            Button button = new Button(key);
            button.setMinSize(80, 80);
            button.setOnAction(e -> passwordField.setText(passwordField.getText() + key));
            keypad.add(button, col, row);
            col++;
            if (col > 2) {
                col = 0;
                row++;
            }
        }

        // دکمه پاک کردن
        Button clearButton = new Button("⌫");
        clearButton.setMinSize(80, 80);
        clearButton.setOnAction(e -> {
            String text = passwordField.getText();
            if (!text.isEmpty()) {
                passwordField.setText(text.substring(0, text.length() - 1));
            }
        });

        // دکمه تایید
        Button okButton = new Button("تأیید");
        okButton.setMinSize(80, 80);
        okButton.setOnAction(e -> {
            if (passwordField.getText().equals(CORRECT_PASSWORD)) {
                isAuthenticated[0] = true;
                dialogStage.close();  // فقط پنجره رمز بسته می‌شود
            } else {
                errorLabel.setText("❌ رمز نادرست است!");
                passwordField.clear();
            }
        });

        // دکمه بازگشت
        Button backButton = new Button("بازگشت به صفحه اصلی");
        backButton.setMinSize(120, 50);
        backButton.setOnAction(e -> {
            ownerStage.setFullScreen(true);
            isAuthenticated[0] = false;
            dialogStage.close();
        });

        VBox layout = new VBox(20, label, passwordField, errorLabel, keypad, clearButton, okButton, backButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();

        return isAuthenticated[0];
    }
}

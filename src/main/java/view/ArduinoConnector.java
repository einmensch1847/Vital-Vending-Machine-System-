package view;

import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ArduinoConnector {

    private SerialPort port;
    private OutputStream outputStream;
    private BufferedReader reader;
    private SerialDataListener dataListener;
    private boolean isListeningStarted = false;

    public ArduinoConnector(String portName) {
        port = SerialPort.getCommPort(portName);
        port.setBaudRate(9600);
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

        if (port.openPort()) {
            System.out.println("✅ پورت سریال باز شد.");
            outputStream = port.getOutputStream();
            reader = new BufferedReader(new InputStreamReader(port.getInputStream()));
        } else {
            System.err.println("❌ باز کردن پورت سریال ناموفق بود.");
        }
    }

    public void sendCommand(String command, Stage primaryStage) {
        if (port != null && port.isOpen() && outputStream != null) {
            try {
                outputStream.write((command + "\n").getBytes());
                outputStream.flush();
                System.out.println("📤 فرمان ارسال شد: " + command);

                waitForResponse(primaryStage); // بعد از ارسال منتظر "F" بمان
            } catch (IOException e) {
                System.err.println("❌ خطا در ارسال فرمان: " + e.getMessage());
            }
        }
    }

    private void waitForResponse(Stage primaryStage) {
        new Thread(() -> {
            try {
                System.out.println("⌛ منتظر دریافت پاسخ از آردوینو...");

                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    System.out.println("📥 دریافت شد: " + line);

                    if (line.equals("F")) {
                        System.out.println("✅ دریافت 'F' - بازگشت به صفحه اصلی.");
                        Platform.runLater(() -> {
                            MainView mainView = new MainView(primaryStage);
                            MainView.switchSceneWithFadeTransition(primaryStage, mainView);
                            primaryStage.setFullScreen(true);
                        });
                        break;
                    }
                }
            } catch (IOException e) {
                System.err.println("❌ خطا در دریافت پاسخ از آردوینو: " + e.getMessage());
            }
        }).start();
    }

    public void close() {
        if (port != null && port.isOpen()) {
            try {
                if (reader != null) reader.close();
                if (outputStream != null) outputStream.close();
            } catch (IOException ignored) {}
            port.closePort();
            System.out.println("🔒 پورت بسته شد.");
        }
    }

    // در این نسخه نیازی به شنونده مجزا نیست چون F مستقیم هندل میشه
    public interface SerialDataListener {
        void onDataReceived(String fullCommand);
    }

}

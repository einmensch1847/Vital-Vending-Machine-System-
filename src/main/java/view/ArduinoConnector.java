package view;

import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.util.Scanner;

public class ArduinoConnector {

    private SerialPort port;
    private CommandListener listener;

    public ArduinoConnector(String portName) {
        port = SerialPort.getCommPort(portName);
        port.setBaudRate(9600);

        if (port.openPort()) {
            System.out.println("پورت سریال باز شد.");

            new Thread(() -> {
                Scanner scanner = new Scanner(port.getInputStream());
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (listener != null) {
                        listener.onCommandReceived(line);
                    }
                }
                scanner.close();
            }).start();

        } else {
            System.err.println("خطا در باز کردن پورت سریال.");
        }
    }

    public void sendCommand(String command) throws IOException {
        if (port.isOpen()) {
            port.getOutputStream().write((command + "\n").getBytes());
            port.getOutputStream().flush();
        }
    }

    public void close() {
        if (port.isOpen()) {
            port.closePort();
        }
    }

    public void setCommandListener(CommandListener listener) {
        this.listener = listener;
    }

    public interface CommandListener {
        void onCommandReceived(String command);
    }
}

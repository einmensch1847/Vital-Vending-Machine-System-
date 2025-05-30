module com.sadra.uni_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires javafx.swing;
    requires com.fazecast.jSerialComm;


    opens com.sadra.uni_project to javafx.fxml;
    exports com.sadra.uni_project;
}
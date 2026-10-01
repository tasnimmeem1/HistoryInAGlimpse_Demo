module org.example.historyinaglimpse {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.historyinaglimpse to javafx.fxml;
    exports org.example.historyinaglimpse;
}
module client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires okhttp3;

    opens client to javafx.fxml;

    exports client;
}

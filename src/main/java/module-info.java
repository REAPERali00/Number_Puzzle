module client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires okhttp3;
    requires com.google.gson;

    opens client to javafx.fxml;

    exports client;
    exports Models to com.google.gson;
}

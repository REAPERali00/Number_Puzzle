package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private StackPane rootLayout;
    private static App instance;

    @Override
    public void start(Stage stage) throws IOException {
        instance = this;
        rootLayout = new StackPane();
        stage.setTitle("Num Puz");
        // Scene page = new Scene(loadFXML("puzzle"));
        Scene page = new Scene(rootLayout, 400, 600);
        stage.setScene(page);
        stage.show();
        loadView("login.fxml");
    }

    public void loadView(String fxml) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxml));
            // Set the CSS style sheet
            view.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            // Apply the background style class to the new view if necessary
            view.getStyleClass().add("background");
            rootLayout.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static App getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch();
    }

}
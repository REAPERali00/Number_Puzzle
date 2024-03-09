package client;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login {
    @FXML
    private TextField nameField;

    @FXML
    private void handleSubmit() throws Exception {
        String userName = nameField.getText().trim();
        if (!userName.isEmpty()) {
            // Load puzzle view
            App.getInstance().loadView("puzzle.fxml");

            // Optionally, if you need to pass data to the PuzzleController
            // Note: This requires modifying loadView in MainApp to return the controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("puzzle.fxml"));
            try {
                loader.load();
                Game_Grid puzzleController = loader.getController();
                puzzleController.setUserName(userName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

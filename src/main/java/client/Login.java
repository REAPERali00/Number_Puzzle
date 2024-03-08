package client;

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
            // Assuming you want to pass the user's name to the PuzzleController
            FXMLLoader loader = new FXMLLoader(getClass().getResource("puzzle.fxml"));
            Parent root = loader.load();

            // Optional: If you want to pass data to the PuzzleController
            Game_Grid puzzle = loader.getController();
            puzzle.setUserName(userName);

            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}

package client;

import Models.AppState;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Login {
    @FXML
    private TextField nameField;

    @FXML
    private void handleSubmit() throws Exception {
        String userName = nameField.getText().trim();
        if (!userName.isEmpty()) {
            AppState.getInstance().setUserName(userName);

            // Pass data to the PuzzleController
            App.getInstance().loadView("puzzle.fxml", (Game_Grid controller) -> {
                controller.setUserName(AppState.getInstance().getUserName());
            });
        }
    }

}

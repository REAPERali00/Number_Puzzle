package client;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Game_Grid {
    @FXML
    private GridPane buttonsPane;

    @FXML
    private void initialize() {
        fillGrid();
    }

    private Button createButton(int number) {
        Button button = new Button(Integer.toString(number));
        button.setAlignment(Pos.CENTER);
        // Set the button style (replace with your actual style class or inline style)
        button.getStyleClass().add("number-button");

        final double buttonSize = 100.0; // Or any size you prefer
        button.setMinSize(buttonSize, buttonSize);
        button.setPrefSize(buttonSize, buttonSize);
        button.setMaxSize(buttonSize, buttonSize);

        // Define the button click action
        button.setOnAction(event -> {
            System.out.println(button.getText());
        });

        return button;
    }

    public void fillGrid() {
        int dim = 3; // Dimension of the grid
        for (int i = 0; i < dim * dim; i++) {
            // Create a button and add it to the grid
            buttonsPane.add(createButton(i), i % dim, i / dim);
        }
        buttonsPane.setHgap(5);
        buttonsPane.setVgap(5);

    }

}

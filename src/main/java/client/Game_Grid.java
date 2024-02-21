package client;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Game_Grid {
    @FXML
    private GridPane buttonsPane;
    private ArrayList<Button> inventory = new ArrayList<>();
    private int active;

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
            setActive(Integer.valueOf(buttonsPane.getRowIndex(button) * 3 + buttonsPane.getColumnIndex(button)));

        });

        return button;
    }

    public void setActive(int ind) {
        Button current = inventory.get(ind);
        Button activeButton = inventory.get(active);

        String temp = current.getText();
        current.setText(activeButton.getText());
        activeButton.setText(temp);
        activeButton.getStyleClass().remove("special-button");
        current.getStyleClass().add("special-button");
        active = ind;
    }

    public void fillGrid() {
        int dim = 3; // Dimension of the grid
        Button button;
        for (int i = 0; i < dim * dim; i++) {
            // Create a button and add it to the grid
            button = createButton(i);
            buttonsPane.add(button, i % dim, i / dim);
            inventory.add(button);
        }
        active = 0;
        inventory.get(active).getStyleClass().add("special-button");
        buttonsPane.setHgap(5);
        buttonsPane.setVgap(5);

    }

}

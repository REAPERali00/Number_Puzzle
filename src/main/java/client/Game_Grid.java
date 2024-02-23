package client;

import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class Game_Grid {
    @FXML
    private GridPane buttonsPane;
    private ArrayList<Button> inventory = new ArrayList<>();
    private int active;
    private int dim;

    @FXML
    private void initialize() {
        dim = 3;
        fillGrid();
        randomize();
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
            int ind = GridPane.getRowIndex(button) * dim + GridPane.getColumnIndex(button);
            if (!checkValidMove(ind)) {
                errorCss("button-wrong", button);
                return;
            }
            setActive(ind);
            if (markCorrect())
                System.out.println("Congrats! you won!");
        });

        return button;
    }

    public boolean checkValidMove(int ind) {
        int possibility = Math.abs(active - ind);

        if (possibility > dim || ((active + 1) % dim == 0 && active == ind - 1))
            return false;
        if (possibility == 1 || possibility % dim == 0)
            return true;

        return false;
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

    public void errorCss(String cssClass, Button button) {
        button.getStyleClass().add(cssClass);
        PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
        pause.setOnFinished(e -> button.getStyleClass().remove(cssClass));
        pause.play();
        vibrate(button);
    }

    public void vibrate(Node node) {
        // Duration for each small movement
        Duration duration = Duration.millis(50);

        // Create small movements (translations)
        TranslateTransition moveRight = new TranslateTransition(duration, node);
        moveRight.setByX(5); // Move right

        TranslateTransition moveLeft = new TranslateTransition(duration, node);
        moveLeft.setByX(-10); // Move left (double distance for visual effect)

        TranslateTransition moveBack = new TranslateTransition(duration, node);
        moveBack.setByX(5); // Move back to the original position

        // Chain the movements in a sequence
        SequentialTransition sequence = new SequentialTransition(moveRight, moveLeft, moveBack);
        sequence.setCycleCount(3); // Repeat the sequence to enhance the effect

        // Play the vibration animation
        sequence.play();
    }

    public boolean markCorrect() {
        Button button;
        int count = 0;
        for (int i = 0; i < inventory.size(); i++) {
            button = inventory.get(i);
            String correctIndex = Integer.toString(i + 1);

            // Clear previous "correct" marking before applying new logic
            button.getStyleClass().removeIf(className -> "button-correct".equals(className));

            if (button.getText().equals(correctIndex)) {
                // Safely add "button-correct" only if it's not already present
                if (!button.getStyleClass().contains("button-correct")) {
                    button.getStyleClass().add("button-correct");
                }
                count++;
            }
        }

        return count == inventory.size();
    }

    public void randomize() {
        int length = inventory.size(), index1, index2;
        String temp;
        for (int i = 0; i < 10; i++) {
            index1 = (int) (Math.random() * (length - 1) + 1);
            index2 = (int) (Math.random() * (length - 1) + 1);
            if (index1 != active && index2 != active) {
                temp = inventory.get(index2).getText();
                inventory.get(index2).setText(inventory.get(index1).getText());
                inventory.get(index1).setText(temp);
            }
        }
    }

    public void fillGrid() {
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

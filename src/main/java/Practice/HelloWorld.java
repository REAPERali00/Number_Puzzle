package Practice;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class HelloWorld extends StackPane {
    private Button btn = new Button();

    public HelloWorld() {
        setupButton();
        super.getChildren().add(btn);
    }

    public void setupButton() {
        btn.setText("Say Hello world!");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Hello world");
            }
        });
    }
}

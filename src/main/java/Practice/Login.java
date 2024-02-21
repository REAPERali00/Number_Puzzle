package Practice;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Login extends GridPane {
    Text title = new Text("Welcome!");
    Font font = Font.font("Techma", FontWeight.NORMAL, 20);
    Label user_title = new Label("User name: ");
    Label pass_title = new Label("Password: ");
    TextField user_field = new TextField();
    TextField pass_field = new TextField();
    HBox submission = new HBox(10);

    public Login() {
        gridSetup();
        loginField();
    }

    public void gridSetup() {
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));
    }

    public void addBtn(String text) {
        Button btn = new Button(text);
        submission.getChildren().add(btn);
    }

    public void loginField() {
        // title.setFont(font);
        add(title, 1, 0, 2, 1);
        add(user_title, 0, 1);
        add(user_field, 1, 1);
        add(pass_title, 0, 2);
        add(pass_field, 1, 2);

        submission.setAlignment(Pos.BOTTOM_RIGHT);
        addBtn("Sign in");
        add(submission, 1, 4);
    }

}

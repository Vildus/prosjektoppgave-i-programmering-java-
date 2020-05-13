package ui;

import javafx.scene.control.*;
import javafx.scene.text.Font;

public class Common {

    public static TextField createTextField() {
        TextField textField = new TextField();
        textField.setFont(Font.font(18));
        return textField;
    }

    public static Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font(18));
        return label;
    }

    public static Button createButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font(18));
        return button;
    }
}

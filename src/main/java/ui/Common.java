package ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.text.Font;

public class Common {

    public static final int SCENE_WIDTH = 1000;
    public static final int SCENE_HEIGHT = 600;

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

    public static Label createHeader(String text) {
        Label label = new Label(text);
        label.setFont(Font.font(22));
        label.setPadding(new Insets(0, 0, 20, 0));
        return label;
    }

    public static Button createButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font(18));
        return button;
    }
}

package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginSuperUserController {

    private Stage stage;

    private Scene customerScene;

    private SceneCloser closer;

    private LoginCallback loginCallback;

    public LoginSuperUserController(LoginCallback loginCallback, SceneCloser closer) {
        this.closer = closer;
        this.loginCallback = loginCallback;
    }

    @FXML
    private Label lblInfo;

    @FXML
    private Label lblUserName;

    @FXML
    private Label lblPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    @FXML
    void cancel(ActionEvent event) {
        this.closer.close();
    }

    @FXML
    void signIn(ActionEvent event) {
        String userName = "super";
        String password = "user";
        if (this.txtUserName.getText().matches(userName) && this.txtPassword.getText().matches(password)) {
            this.loginCallback.login();
        } else {
            this.lblInfo.setText("User name or password was wrong. Please try again (Hint: try \"super\" as user name and \"user\" as password");
        }
    }


}



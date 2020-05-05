
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.LoginUserController;


import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene mainScene = this.createMainScene(primaryStage);
        primaryStage.setTitle(LoginUserController.TITLE);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Scene createMainScene(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/loginUser.fxml"));
        loader.setController(new LoginUserController((title, scene) -> {
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
        }));
        return new Scene(loader.load(), 600, 600);
    }
}


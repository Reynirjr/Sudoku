package hi.vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuApplication extends Application {

    private static Stage adalStage;

    @Override
    public void start(Stage stage) throws IOException {
        adalStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(SudokuApplication.class.getResource("erfidleikastig-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Veldu Erfidleikastig");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getAdalStage() {
        return adalStage;
    }

    public static void main(String[] args) {
        launch();
    }
}

package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ErfidleikastigController {

    @FXML
    private void onEasy(ActionEvent event) throws Exception {
        hefjaLeik("Auðvelt", event);
    }

    @FXML
    private void onMid(ActionEvent event) throws Exception {
        hefjaLeik("Miðlungs", event);
    }

    @FXML
    private void onDif(ActionEvent event) throws Exception {
        hefjaLeik("Erfitt", event);
    }

    @FXML
    private void onExtreme(ActionEvent event) throws Exception {
        hefjaLeik("Ómögulegt", event);
    }

    private void hefjaLeik(String difficulty, ActionEvent event) throws Exception {
        // Loadar sudoku-view.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sudoku-view.fxml"));
        Parent root = loader.load();

        // kastar erfidleikastiginu til LeikbordController
        SudokuController sudokuController = loader.getController();
        sudokuController.setDifficulty(difficulty);

        // Get the current stage and set the scene
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root, 650, 600));
        stage.show();
    }
}

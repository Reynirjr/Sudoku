package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MenuController {


    private SudokuController sudokuController;

    @FXML
    private LeikbordController leikbordController;


    public void setSudokuController(SudokuController aThis) {
        sudokuController = aThis;
    }


    public void initialize() {

    }


    public void onNewGame(ActionEvent actionEvent) {
        sudokuController.onNyrLeikur(actionEvent);
    }


    public void onQuit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Hætta");
        alert.setHeaderText("Beiðni um að hætta");
        alert.setContentText("Ertu viss um að þú viljir hætta?");

        ButtonType buttonTypeYes = new ButtonType("Já");
        ButtonType buttonTypeNo = new ButtonType("Nei");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            System.exit(0);
        }

    }

    /**
     * kallar á information alert sem gefur upplýsingar um höfund forrits og ártal
     *
     * @param actionEvent
     */
    public void onUmFor(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Um forritið");
        alert.setHeaderText(null);
        alert.setContentText("Höfundar: Benjamín Reynir Jóhannsson, \n Egill Arnar Helgason, \n Róbert Orri Stefánsson \n \nÁrtal: Haust 2024 \n \nForritið býr til Sudoku púsl fyrir notandan til að leysa.");

        alert.showAndWait();
    }


}

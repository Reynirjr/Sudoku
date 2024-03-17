package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioMenuItem;

public class MenuController {
    @FXML
    private SudokuController sudokuController;


    private String erfidleikastig = "";

    public void setSudokuController(SudokuController aThis) {
        sudokuController = aThis;
    }


    public void initialize() {

    }

    /**
     * aðferð til að meðhöndla þegar erfiðleikastig er valið af menu
     *
     * @param actionEvent
     */
    public void onErfidleikastig(ActionEvent actionEvent) {
        RadioMenuItem selectedMenuItem = (RadioMenuItem) actionEvent.getSource();
        setErfidleikastig(selectedMenuItem.getText());
        System.out.println("Valið erfiðleikastig: " + erfidleikastig);

    }

    //setter fyrir erfiðleikastigin
    public void setErfidleikastig(String erfidleikastig) {
        this.erfidleikastig = erfidleikastig;
    }

    //getter fyrir erfiðleikastigin
    public String getErfidleikastig() {
        return erfidleikastig;
    }

    public void onNewGame(ActionEvent actionEvent) {
    }

    public void onQuit(ActionEvent actionEvent) {
    }
}

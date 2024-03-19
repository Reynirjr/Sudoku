package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioMenuItem;

import java.util.Optional;

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
}

package hi.vidmot;

import javafx.fxml.FXML;

public class SudokuController {


    @FXML
    private MenuController menuStyringController;

    @FXML
    private LeikbordController leikbordStyringController;

    @FXML
    public void initialize() {

        menuStyringController.setSudokuController(this);
        leikbordStyringController.setSudokuController(this);
    }

    public void setDifficulty(String erfidleikastig) {
        int difficultyLevel = 0;
        if (erfidleikastig.equals("Auðvelt")) {
            difficultyLevel = 20;
        } else if (erfidleikastig.equals("Miðlungs")) {
            difficultyLevel = 35;
        } else if (erfidleikastig.equals("Erfitt")) {
            difficultyLevel = 40;
        } else if (erfidleikastig.equals("Ómögulegt")) {
            difficultyLevel = 50;
        }

        leikbordStyringController.setErfidleikastig(difficultyLevel);
    }

}

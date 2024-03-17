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

  
}

package hi.vidmot;

import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class SudokuController {

    @FXML
    private GridPane sudokuGrid;

    @FXML
    private MenuController menuStyringController;

    @FXML
    public void initialize() {
        sudokuGrid.widthProperty().addListener((obs, oldVal, newVal) -> updateGridSize());
        sudokuGrid.heightProperty().addListener((obs, oldVal, newVal) -> updateGridSize());
        populateGrid();
        menuStyringController.setSudokuController(this);
    }

    private void updateGridSize() {
        double size = Math.min(sudokuGrid.getWidth(), sudokuGrid.getHeight());
        sudokuGrid.setPrefSize(size, size);
        sudokuGrid.setMaxSize(size, size);
        // Adjust row and column sizes within the grid
        for (ColumnConstraints cc : sudokuGrid.getColumnConstraints()) {
            cc.setPrefWidth(size / 9);
        }
        for (RowConstraints rc : sudokuGrid.getRowConstraints()) {
            rc.setPrefHeight(size / 9);
        }
    }

    private void populateGrid() {
        // Populate your grid with cells here, adjust as necessary for your application
    }
}

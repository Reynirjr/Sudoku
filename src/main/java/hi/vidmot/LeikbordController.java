package hi.vidmot;

import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LeikbordController {
    @FXML
    private GridPane sudokuGrid;

    @FXML
    private SudokuController sudokuController;


    public void setSudokuController(SudokuController aThis) {
        sudokuController = aThis;
    }


    public void initialize() {
        sudokuGrid.widthProperty().addListener((obs, oldVal, newVal) -> updateGridSize());
        sudokuGrid.heightProperty().addListener((obs, oldVal, newVal) -> updateGridSize());
        fyllaGrid();
    }

    private void updateGridSize() {
        double size = Math.min(sudokuGrid.getWidth(), sudokuGrid.getHeight());
        sudokuGrid.setPrefSize(size, size);
        sudokuGrid.setMaxSize(size, size);
        for (ColumnConstraints cc : sudokuGrid.getColumnConstraints()) {
            cc.setPrefWidth(size / 9);
        }
        for (RowConstraints rc : sudokuGrid.getRowConstraints()) {
            rc.setPrefHeight(size / 9);
        }
    }

    private void fyllaGrid() {
        //Þykkt á borders
        double innriBorderThickness = 0.5;
        double ytriBorderThickness = 3.0;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Pane reiturPane = new Pane();

                double toppur = row == 0 || row % 3 == 0 ? ytriBorderThickness : innriBorderThickness;
                double haegri = (col + 1) % 3 == 0 ? ytriBorderThickness : innriBorderThickness;
                double botn = (row + 1) % 3 == 0 ? ytriBorderThickness : innriBorderThickness;
                double vinstri = col == 0 || col % 3 == 0 ? ytriBorderThickness : innriBorderThickness;

                BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(toppur, haegri, botn, vinstri));

                reiturPane.setBorder(new Border(borderStroke));
                sudokuGrid.add(reiturPane, col, row);
            }

        }
    }

}

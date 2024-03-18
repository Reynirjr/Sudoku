package hi.vidmot;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LeikbordController {
    @FXML
    private GridPane sudokuGrid;

    @FXML
    private SudokuController sudokuController;


    public void setSudokuController(SudokuController aThis) {
        sudokuController = aThis;
    }


    public void initialize() {
        sudokuGrid.widthProperty().addListener((obs, oldVal, newVal) -> updateGridStaerd());
        sudokuGrid.heightProperty().addListener((obs, oldVal, newVal) -> updateGridStaerd());
        fyllaGrid();
    }

    private void updateGridStaerd() {
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
                TextField reiturTextField = new TextField();
                reiturTextField.setMaxWidth(Double.MAX_VALUE);
                reiturTextField.setMaxHeight(Double.MAX_VALUE);


                //breytir stærð og fontinu
                reiturTextField.setFont(Font.font("Comic Sans MS", 16));

                //Þarf að vera 1 - 9
                reiturTextField.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
                    if (!"123456789".contains(keyEvent.getCharacter())) {
                        keyEvent.consume();
                    }
                });

                //þarf að vera 1 stafur
                reiturTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue.length() > 1) {
                        reiturTextField.setText(newValue.substring(0, 1));
                    }
                });


                //valin reitur verður blár, verður hvítur ef annar reitur er síðan valinn.
                reiturTextField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                    if (isNowFocused) {
                        reiturTextField.setEditable(true);
                        reiturTextField.setStyle("-fx-background-color: lightblue;");
                    } else {
                        reiturTextField.setEditable(false);
                        reiturTextField.setStyle("-fx-background-color: white;");
                    }
                });

                //Gerir línurnar á milli þykkar á endunum og á milli "subgrids"
                double toppur = row % 3 == 0 ? ytriBorderThickness : innriBorderThickness;
                double haegri = (col + 1) % 3 == 0 ? ytriBorderThickness : innriBorderThickness;
                double botn = (row + 1) % 3 == 0 ? ytriBorderThickness : innriBorderThickness;
                double vinstri = col % 3 == 0 ? ytriBorderThickness : innriBorderThickness;

                reiturTextField.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null,
                        new BorderWidths(toppur, haegri, botn, vinstri))));

                sudokuGrid.add(reiturTextField, col, row);
                GridPane.setFillWidth(reiturTextField, true);
                GridPane.setFillHeight(reiturTextField, true);
            }

        }
    }

}

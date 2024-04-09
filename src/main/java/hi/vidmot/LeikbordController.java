package hi.vidmot;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LeikbordController {
    @FXML
    private GridPane sudokuGrid;

    @FXML
    private SudokuController sudokuController;

    @FXML
    private MenuController menuController;

    private int erfidleiki = 0;

    private int teknirReitir = 0;

    private int rettNumerIReit = 0;

    private int rangtNumerIReit = 0;


    private final int[][] bord = new int[9][9];//Sudoku borðið

    private int[][] copyBordid = new int[9][9];//copy af borðinu

    private boolean[][] retturReitur = new boolean[9][9];


    public void setSudokuController(SudokuController aThis) {
        sudokuController = aThis;
    }


    public void initialize() {
        sudokuGrid.widthProperty().addListener((obs, oldVal, newVal) -> updateGridStaerd());
        sudokuGrid.heightProperty().addListener((obs, oldVal, newVal) -> updateGridStaerd());
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
        buaTilSudoku();//fyllir borðið á slembin hátt
        //Þykkt á borders
        double innriBorderThickness = 0.5;
        double ytriBorderThickness = 3.0;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                final int finalRow = row;
                final int finalCol = col;
                TextField reiturTextField = new TextField();
                //breytir stærð og fontinu
                reiturTextField.setFont(Font.font("Comic Sans MS", 16));

                if (bord[row][col] != 0) {
                    reiturTextField.setText(Integer.toString(bord[row][col]));
                    reiturTextField.setEditable(false);
                    reiturTextField.setDisable(true);
                    reiturTextField.setStyle("-fx-opacity: 1;");
                } else {
                    reiturTextField.setText(""); // Empty cells for user input
                    reiturTextField.setEditable(true); // Make it editable
                    reiturTextField.setStyle("-fx-text-fill: blue;");
                    reiturTextField.textProperty().addListener((obs, oldText, newText) -> {
                        reiturTextField.setStyle("-fx-text-fill: blue;");
                    });
                    reiturTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (!newValue.isEmpty() && newValue.matches("[1-9]")) {
                            int num = Integer.parseInt(newValue);
                            if (erNumberRett(finalRow, finalCol, num)) {// Rett svar
                                retturReitur[finalRow][finalCol] = true;
                                rettNumerIReit++;
                                reiturTextField.getStyleClass().add("correct-answer");
                                reiturTextField.setEditable(false);
                                reiturTextField.setDisable(true);
                                refreshReitirStyle();

                                if (rettNumerIReit == teknirReitir) {
                                    sigurMessage();
                                }

                            } else {
                                // Rangt svar
                                retturReitur[finalRow][finalCol] = false;
                                reiturTextField.setStyle("-fx-background-color: pink; -fx-text-fill: red;");
                                reiturTextField.getStyleClass().add("incorrect-answer");
                                rangtNumerIReit++;
                                if (rangtNumerIReit >= 3) {
                                    tapMessage();
                                }
                            }
                        }
                    });


                }


                reiturTextField.setMaxWidth(Double.MAX_VALUE);
                reiturTextField.setMaxHeight(Double.MAX_VALUE);


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
                GridPane.setRowIndex(reiturTextField, row);
                GridPane.setColumnIndex(reiturTextField, col);
            }

        }
    }

    private void sigurMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Victory");
        alert.setHeaderText(null);
        alert.setContentText("Til Hamingju Þú Leystir Sudok-ið");

        alert.showAndWait();
    }

    private void tapMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("Leik Lokið þú Fékkst 3 villur");

        alert.showAndWait();
    }

    private boolean fylltBord(int row, int col) {
        if (row == 9) {
            row = 0;
            if (++col == 9) return true;
        }
        if (bord[row][col] != 0) return fylltBord(row + 1, col);
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 9; i++) nums.add(i);
        Collections.shuffle(nums);//slembin númer

        for (Integer num : nums) {
            if (erRettStadsetning(row, col, num)) {
                bord[row][col] = num;
                if (fylltBord(row + 1, col)) return true;
                bord[row][col] = 0;
            }
        }
        return false;
    }

    private boolean erNumberRett(int row, int col, int number) {
        return copyBordid[row][col] == number;
    }


    private boolean erRettStadsetning(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (bord[i][col] == num || bord[row][i] == num) return false;
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (bord[boxRow][boxCol] == num) return false;
        }
        return true;
    }

    private void buaTilSudoku() {
        fylltBord(0, 0);//fyllir borðið frá top-vinstra horni
        copyBord(bord, copyBordid);
        geraPusl(erfidleiki);
    }


    private void geraPusl(int taka) {

        Random rand = new Random();
        List<Integer> removeMoguleikar = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            removeMoguleikar.add(i);
        }
        Collections.shuffle(removeMoguleikar);

        for (int i = 0; i < taka; i++) {
            int reiturIndex = removeMoguleikar.get(i);
            int row = reiturIndex / 9;
            int col = reiturIndex % 9;

            if (bord[row][col] != 0) {
                bord[row][col] = 0;
                teknirReitir++;
            }
            updateUI(row, col, "");
        }
        System.out.println("reitir teknir: " + teknirReitir);
    }

    private void updateUI(int row, int col, String value) {
        for (Node node : sudokuGrid.getChildren()) {
            // Default to 0 if the row or column index is null
            Integer nodeRow = GridPane.getRowIndex(node) != null ? GridPane.getRowIndex(node) : 0;
            Integer nodeCol = GridPane.getColumnIndex(node) != null ? GridPane.getColumnIndex(node) : 0;

            if (nodeRow == row && nodeCol == col && node instanceof TextField) {
                ((TextField) node).setText(value);
                break;
            }
        }
    }


    public void setErfidleikastig(int difficultyLevel) {
        this.erfidleiki = difficultyLevel;
        fyllaGrid();
    }

    private void copyBord(int[][] source, int[][] dest) {
        for (int i = 0; i < source.length; i++) {
            System.arraycopy(source[i], 0, dest[i], 0, source[i].length);
        }
    }

    private void refreshReitirStyle() {
        for (Node node : sudokuGrid.getChildren()) {
            if (node instanceof TextField) {
                TextField reitur = (TextField) node;
                int row = GridPane.getRowIndex(reitur);
                int col = GridPane.getColumnIndex(reitur);

                if (retturReitur[row][col]) {
                    reitur.setDisable(true);
                }

            }
        }
    }


}

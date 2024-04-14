package hi.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

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

    private boolean[][] retturReitur = new boolean[9][9];//


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
                    reiturTextField.setDisable(true);
                    reiturTextField.setStyle("-fx-opacity: 1;");
                } else {
                    reiturTextField.setText("");
                    reiturTextField.setEditable(true);
                    reiturTextField.setStyle("-fx-text-fill: green;");
                    reiturTextField.textProperty().addListener((obs, oldText, newText) -> {
                        reiturTextField.setStyle("-fx-text-fill: green;");
                    });
                    reiturTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (!newValue.isEmpty() && newValue.matches("[1-9]")) {
                            int num = Integer.parseInt(newValue);
                            if (erNumberRett(finalRow, finalCol, num)) {// Rett svar
                                rettNumerIReit++;
                                reiturTextField.getStyleClass().remove("incorrect-answer");
                                reiturTextField.getStyleClass().add("correct-answer");
                                reiturTextField.setEditable(false);
                                reiturTextField.setDisable(true);

                                if (rettNumerIReit == teknirReitir) {
                                    sigurMessage();
                                }

                            } else {
                                // Rangt svar
                                reiturTextField.setStyle("-fx-background-color: pink; -fx-text-fill: red;");
                                reiturTextField.getStyleClass().add("incorrect-answer");
                                rangtNumerIReit++;
                                sudokuController.setVillur(rangtNumerIReit);
                                if (rangtNumerIReit >= 3) {
                                    tapMessage();
                                }
                            }
                        }
                    });


                }

                reiturTextField.setOnMouseClicked(event -> highlightSomuNumer(reiturTextField.getText()));
                reiturTextField.textProperty().addListener((obs, oldText, newText) -> {
                    if (!newText.matches("\\d*")) {
                        reiturTextField.setText(newText.replaceAll("[^\\d]", ""));
                    }
                });


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

    private void highlightSomuNumer(String numer) {
        if (numer.isEmpty()) return;

        for (Node node : sudokuGrid.getChildren()) {
            if (node instanceof StackPane) {
                Label lbl = (Label) ((StackPane) node).getChildren().get(0);
                if (lbl.getText().equals(numer)) {
                    lbl.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
                    lbl.setTextFill(Color.LIGHTBLUE);
                } else {
                    lbl.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 16));
                    lbl.setTextFill(Color.BLACK);
                }
            }
        }
    }


    //Alert þegar Notandi klárar púslið
    private void sigurMessage() {
        sudokuController.stopTimer();
        String eyddurTimi = sudokuController.formatTime(sudokuController.getSek());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Victory");
        alert.setHeaderText(null);
        alert.setContentText("Til Hamingju! Þú Leystir Súdókúið! Þinn tími var " + eyddurTimi);

        ButtonType newGameButton = new ButtonType("Hefja nýjan leik");
        ButtonType cancelButton = new ButtonType("Hætta við", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(newGameButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == newGameButton) {
            Stage stage = (Stage) sudokuGrid.getScene().getWindow();
            stage.close();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/hi/vidmot/erfidleikastig-view.fxml"));
                Parent root = loader.load();
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.setTitle("Veldu Erfiðleika!");
                newStage.show();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    }

    private void tapMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("Leik Lokið þú Fékkst 3 villur");

        ButtonType newGameButton = new ButtonType("Hefja nýjan leik");
        ButtonType cancelButton = new ButtonType("Hætta við", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(newGameButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == newGameButton) {
            Stage stage = (Stage) sudokuGrid.getScene().getWindow();
            stage.close();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/hi/vidmot/erfidleikastig-view.fxml"));
                Parent root = loader.load();
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.setTitle("Veldu Erfiðleika!");
                newStage.show();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
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


}

module hi.vidmot.sudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens hi.vidmot to javafx.fxml;
    exports hi.vidmot;
}

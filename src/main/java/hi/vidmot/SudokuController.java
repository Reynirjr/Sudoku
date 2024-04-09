package hi.vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class SudokuController {


    @FXML
    private MenuController menuStyringController;

    @FXML
    private LeikbordController leikbordStyringController;

    @FXML
    private Label villaLabel;

    @FXML
    private Label timeLabel;

    private Timeline timeline;
    private int sek = 0;

    private int villur = 0;

    @FXML
    public void initialize() {

        menuStyringController.setSudokuController(this);
        leikbordStyringController.setSudokuController(this);
        setupTimer();
    }

    private void setupTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateTimer() {
        sek++;
        timeLabel.setText(String.format("Tími: %02d:%02d", sek / 60, sek % 60));

    }

    public void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    public void resetTimer() {
        stopTimer();
        sek = 0;
        timeLabel.setText("Time: 00:00");
        setupTimer();
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

    public void setVillur(int villa) {
        this.villur = villa;
        villaLabel.setText("Villur: " + villa);
    }


}

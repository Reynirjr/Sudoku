package hi.vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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

    public String formatTime(int heildarSek) {
        int min = heildarSek / 60;
        int sek = heildarSek % 60;
        return String.format("%02d:%02d", min, sek);
    }

    public int getSek() {
        return sek;
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


    public void onNyrLeikur(ActionEvent event) {
        try {
            // Close the current stage

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            // Load the difficulty selection screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("erfidleikastig-view.fxml")); // Adjust path as necessary
            Parent root = loader.load();

            // Create and show the new stage
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Choose Difficulty");
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

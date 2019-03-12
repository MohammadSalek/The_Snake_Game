package graphics;

import game.Game;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {

    @FXML private AnchorPane mainPane;

    @FXML private AnchorPane startPane;

    @FXML private AnchorPane gameAnchorPane;

    @FXML private Pane gridPane;

    @FXML private Pane gameOverPane;

    @FXML private Pane pausePane;

    @FXML private Text scoreNum;

    private boolean hitResumeBtn = false;

    @FXML
    protected void startFunction() {
        gameOverPane.setVisible(false);
        gameOverPane.setDisable(true);
        startPane.setVisible(false);
        startPane.setDisable(true);
        gameAnchorPane.setVisible(true);
        gameAnchorPane.setDisable(false);
        Game.setGameStarted(true);
    }

    @FXML
    protected void restartFunction() {
        Game.restartGame();
        gridPane.getChildren().addAll(Game.getGridGroup());
        startFunction();
    }

    @FXML
    private void quitFunction() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize() {
        startPane.setVisible(true);
        startPane.setDisable(false);
        gameAnchorPane.setVisible(false);
        gameAnchorPane.setDisable(true);
        pausePane.setVisible(false);
        pausePane.setDisable(true);
        gridPane.getChildren().addAll(Game.getGridGroup());
    }

    public void gameIsOver() {
        gameOverPane.setDisable(false);
        gameOverPane.setVisible(true);
        scoreNum.setText(String.valueOf(Game.getScore()));
    }

    public void gamePaused() {
        pausePane.setDisable(false);
        pausePane.setVisible(true);
    }

    public void gameResumed() {
        pausePane.setDisable(true);
        pausePane.setVisible(false);
        hitResumeBtn = false;
    }

    @FXML
    private void hitResume() {
        hitResumeBtn = true;
    }

    public boolean hasHitResume() {
        return hitResumeBtn;
    }

}

import game.Game;
import model.Direction;
import graphics.Controller;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    Controller myController;
    private boolean gameOverFlag = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("The Snake Game");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("graphics/start.fxml").openStream());
        myController = fxmlLoader.getController();
        Scene mainScene = new Scene(root);
        mainScene.getStylesheets().add(Main.class.getResource("graphics/stylesheet/style.css").toExternalForm());

        mainScene.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.LEFT || key.getCode() == KeyCode.A) {
                Game.setSnakeDirection(Direction.LEFT);
            } else if (key.getCode() == KeyCode.RIGHT || key.getCode() == KeyCode.D) {
                Game.setSnakeDirection(Direction.RIGHT);
            } else if (key.getCode() == KeyCode.UP || key.getCode() == KeyCode.W) {
                Game.setSnakeDirection(Direction.UP);
            } else if (key.getCode() == KeyCode.DOWN || key.getCode() == KeyCode.S) {
                Game.setSnakeDirection(Direction.DOWN);
            }
        });

        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);

        new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long currentNanoTime) {
                if (currentNanoTime - lastUpdate >= 120_000_000) {
                    if (Game.isGameOver()) {
                        if (!gameOverFlag) {
                            System.out.println("game.Game over.");
                            myController.gameIsOver();
                            gameOverFlag = true;
                        }
                    }
                    else if (Game.hasGameStarted()) {
                        gameOverFlag = false;
                        Game.update();
                    }
                    lastUpdate = currentNanoTime;
                }
            }
        }.start();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Controller getController() {
        return myController;
    }

}



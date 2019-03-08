package game;

import model.Direction;
import model.Grid;
import model.Snake;
import model.Tile;
import javafx.scene.Group;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    private static int rows = 17;
    private static int columns = 17;
    private static int snakeLength = 3;
    private static Grid grid = new Grid(rows, columns);
    private static Snake snake = new Snake(snakeLength);
    private static Random random = new Random();
    private static boolean gameStarted = false;
    private static boolean gameOver = false;
    private static boolean init = true;
    private static int score = 0;

    public static void update() {
        if (init) {
            putFruit();
            init = false;
        }
        snake.move();
        if (snake.getHeadTile().getIsWall() || snake.getBodyTiles().contains(snake.getHeadTile())) {
            gameOver = true;
        }

        if (snake.getHeadTile().getHasFruit()) {
            score++;
            snake.grow();
            snake.getHeadTile().setHasFruit(false);
            putFruit();
        }
    }

    private static void putFruit() {
        ArrayList<Tile> availableCells = new ArrayList<>();
            for (int j = 0; j < grid.getRowsNumInColumn(); ++j) {
                for (int i = 0; i < grid.getTilesNumInRow(); ++i) {
                    Tile tmpCell = grid.getTile(j, i);
                    if (!tmpCell.getIsWall() &&
                            !snake.getBodyTiles().contains(tmpCell) &&
                            !snake.getHeadTile().equals(tmpCell)) {
                        availableCells.add(tmpCell);
                    }
                }
            }
        int randomNum = random.nextInt(availableCells.size());
        Tile chosenCell = availableCells.get(randomNum);
        chosenCell.setHasFruit(true);
    }

    public static boolean hasGameStarted() {
        return gameStarted;
    }

    public static void setGameStarted(boolean started) {
        gameStarted = started;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static Group getGridGroup() {
        return grid.getGroup();
    }

    public static Grid getGrid() {
        return grid;
    }

    public static void setSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }

    public static int getScore() {
        return score;
    }

    public static void restartGame() {
        grid = new Grid(rows, columns);
        snakeLength = 3;
        snake = new Snake(snakeLength);
        init = true;
        score = 0;
        gameOver = false;
        gameStarted = true;
    }

}

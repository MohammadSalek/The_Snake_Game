package model;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Tile extends StackPane {

    Random random = new Random();
    private static final Color NORMAL_COLOR1 = Color.rgb(241, 194, 125);
    private static final Color NORMAL_COLOR2 = Color.rgb(181, 158, 129);
    private static final Color NORMAL_COLOR3 = Color.rgb(255, 219, 172);
    List<Color> normalColors = Arrays.asList(
            NORMAL_COLOR1,
            NORMAL_COLOR2,
            NORMAL_COLOR3
    );
    private static final Color SNAKE_BODY_COLOR = Color.rgb(37, 160, 50);
    private static final Color SNAKE_HEAD_COLOR = Color.rgb(36, 136, 35);
    private static final Color WALL_COLOR = Color.rgb(67, 54, 41);
    private static final Color FRUIT_COLOR = Color.rgb(238, 45, 41);
    private static final Color BORDER_COLOR = Color.BLACK;
    private final static double strokeWidth = 0.4;
    private Rectangle rectangle;
    private int rowIndex;
    private int columnIndex;
    private boolean isWall = false;
    private boolean hasFruit = false;

    public Tile(int columnIndex, int rowIndex, double tileSize) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        rectangle = new Rectangle(tileSize, tileSize);
        rectangle.setFill(null);
        rectangle.setStroke(BORDER_COLOR);
        rectangle.setStrokeWidth(strokeWidth);
        rectangle.setFill(normalColors.get(random.nextInt(normalColors.size())));
        setAlignment(Pos.CENTER);
        getChildren().add(rectangle);
    }

    public void setIsSnakeHeadIn(boolean isSnakeHeadIn) {
        if (isSnakeHeadIn) {
            rectangle.setFill(SNAKE_HEAD_COLOR);
        }
        else {
            rectangle.setFill(normalColors.get(random.nextInt(normalColors.size())));
        }
    }

    public void setIsSnakeBodyIn(boolean isSnakeBodyIn) {
        if (isSnakeBodyIn) {
            rectangle.setFill(SNAKE_BODY_COLOR);
        }
        else {
            rectangle.setFill(normalColors.get(random.nextInt(normalColors.size())));
        }
    }

    public void setIsWall(boolean isWall) {
        this.isWall = isWall;
        if (isWall) {
            rectangle.setFill(WALL_COLOR);
        }
        else {
            rectangle.setFill(normalColors.get(random.nextInt(normalColors.size())));
        }
    }

    public boolean getIsWall() {
        return isWall;
    }

    public void setHasFruit(boolean hasFruit) {
        this.hasFruit = hasFruit;
        if (hasFruit) {
            rectangle.setFill(FRUIT_COLOR);
        }
        else {
            rectangle.setFill(normalColors.get(random.nextInt(normalColors.size())));
        }
    }

    public boolean getHasFruit() {
       return hasFruit;
    }

    public int getrowIndex() {
        return rowIndex;
    }

    public int getcolumnIndex() {
        return columnIndex;
    }

    public static double getRectStrokeWidth() {
        return strokeWidth;
    }

}

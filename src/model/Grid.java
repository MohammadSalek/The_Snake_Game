package model;

import game.Game;
import javafx.scene.Group;

public class Grid {

    private Group gridGroup;
    private final int WIDTH = Game.WIDTH;
    private final int HEIGHT = Game.HEIGHT;
    private final double strokeWidth = Tile.getRectStrokeWidth();
    private int tilesInRow;
    private int rowsInColumn;
    private Tile[][] tiles;
    private double tileSize;

    public Grid(int row, int column) {
        gridGroup = new Group();
        this.tilesInRow = row;
        this.rowsInColumn = column;
        tileSize = ((float)WIDTH / row) - strokeWidth * 2;
        tiles = new Tile[column][row];
        for (int i = 0; i < column; ++i) {
            for (int j = 0; j < row; ++j) {
                tiles[i][j] = new Tile(i , j, tileSize);
                if ((i % column == 0) ||
                        (i % column == (column - 1)) ||
                        (j % row == 0) ||
                        (j % row == (row - 1))) {
                    tiles[i][j].setIsWall(true);
                }
                else {
                    tiles[i][j].setIsWall(false);
                }
                putOnGrid(tiles[i][j], j, i);
            }
        }
    }

    public Group getGroup() {
        return gridGroup;
    }

    private void putOnGrid(Tile tile, int columnIndex, int rowIndex) {
        tile.setTranslateX((tileSize + strokeWidth * 2) * columnIndex);
        tile.setTranslateY((tileSize + strokeWidth * 2) * rowIndex);
        gridGroup.getChildren().add(tile);
    }

    public int getTilesNumInRow() {
        return tilesInRow;
    }

    public int getRowsNumInColumn() {
        return rowsInColumn;
    }

    public Tile getTile(int column, int row) {
        return tiles[column][row];
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}

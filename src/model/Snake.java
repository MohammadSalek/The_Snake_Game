package model;

import game.Game;

import java.util.ArrayList;

public class Snake {

    private Direction direction;
    private Direction nextDirection;
    private Tile head, tail;
    private ArrayList<Tile> body;
    private boolean isGrowing = false;

    public Snake(int length) {
        head = Game.getGrid().getTile(Game.getGrid().getRowsNumInColumn() / 2, Game.getGrid().getTilesNumInRow() / 2);
        head.setIsSnakeHeadIn(true);
        body = new ArrayList<>();
        for (int i = length - 1; i > 0; --i) {
            Tile bodyTile = Game.getGrid().getTile(head.getcolumnIndex() + i, head.getrowIndex());
            body.add(bodyTile);
            bodyTile.setIsSnakeBodyIn(true);
        }
        tail = body.get(0);
        direction = nextDirection = Direction.UP;
    }

    public void move() {
        if (Game.isGameOver()) {
            return;
        }

        if (!isGrowing) {
            tail.setIsSnakeBodyIn(false);
            body.remove(tail);
            tail = body.get(0);
            tail.setIsSnakeBodyIn(true);
        }
        else {
            isGrowing = false;
        }

        body.add(head);
        head.setIsSnakeHeadIn(false);
        head.setIsSnakeBodyIn(true);

        direction = nextDirection;
        if (direction.equals(Direction.UP)) {
            head = Game.getGrid().getTile(head.getcolumnIndex() - 1, head.getrowIndex());
        }
        else if (direction.equals(Direction.DOWN)) {
            head = Game.getGrid().getTile(head.getcolumnIndex() + 1, head.getrowIndex());
        }
        else if (direction.equals(Direction.LEFT)) {
            head = Game.getGrid().getTile(head.getcolumnIndex(), head.getrowIndex() - 1);
        }
        else if (direction.equals(Direction.RIGHT)) {
            head = Game.getGrid().getTile(head.getcolumnIndex(), head.getrowIndex() + 1);
        }
        head.setIsSnakeHeadIn(true);
    }

    public void grow() {
        isGrowing = true;
    }

    public Tile getHeadTile() {
        return head;
    }

    public ArrayList<Tile> getBodyTiles() {
        return body;
    }

    public void setDirection(Direction direction) {
        if (((this.direction == Direction.RIGHT) && (direction != Direction.LEFT))) {
            nextDirection = direction;
        }
        else if (((this.direction == Direction.LEFT) && (direction != Direction.RIGHT))) {
            nextDirection = direction;
        }
        else if (((this.direction == Direction.UP) && (direction != Direction.DOWN))) {
            nextDirection = direction;
        }
        else if (((this.direction == Direction.DOWN) && (direction != Direction.UP))) {
            nextDirection = direction;
        }
    }
}

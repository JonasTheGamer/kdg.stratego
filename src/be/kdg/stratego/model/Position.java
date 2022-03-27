/*
    Class: Position
    Responsiblity: Base class for GameBoardField which handles the positioning of the field in the grid
 */
package be.kdg.stratego.model;

public class Position {
    protected int positionX;
    protected int positionY;

    // Getters
    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    // Setters
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
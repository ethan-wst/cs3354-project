package pieces;

import board.Board;

/**
 * The `Piece` class represents a chess piece with properties such as position,
 * color, and movement
 * validity check.
 */
public abstract class Piece {
    private int x;
    private int y;

    private boolean alive;
    private boolean white;
    private boolean hasMoved;

    /**
     * Creates a piece object
     * 
     * @param x     X cord of piece on the board
     * @param y     Y cord of piece on the board
     * @param alive True if alive, false if not
     * @param white True if white and false if black
     */
    public Piece(int x, int y, boolean alive, boolean white) {
        super();
        this.x = x;
        this.y = y;
        this.alive = alive;
        this.white = white;
        this.hasMoved = false;
    }

    /**
     * The getX() function in Java returns the value of the variable x.
     * 
     * @return The method `getX()` is returning the value of the variable `x`
     *         belonging to the current
     *         object.
     */
    public int getX() {
        return this.x;
    }

    /**
     * This Java function returns the value of the 'y' attribute of an object.
     * 
     * @return The method `getY()` is returning the value of the `y` instance
     *         variable of the current
     *         object.
     */
    public int getY() {
        return this.y;
    }

    /**
     * The function isAlive() returns a boolean value indicating whether the object
     * is alive or not.
     * 
     * @return The method isAlive() is returning the value of the boolean variable
     *         "alive" in the
     *         current object.
     */
    public boolean isAlive() {
        return this.alive;
    }

    /**
     * The `isWhite` function in Java returns the value of the `white` attribute.
     * 
     * @return The method `isWhite()` returns the value of the `white` instance
     *         variable of the object.
     */
    public boolean isWhite() {
        return this.white;
    }

    /**
     * The function `setMoved` in Java sets the `hasMoved` boolean variable to the
     * value passed as a
     * parameter.
     * 
     * @param moved The `moved` parameter is a boolean value that indicates whether
     *              an object has been
     *              moved or not.
     */
    public void setMoved(boolean moved) {
        this.hasMoved = moved;
    }

    /**
     * The function `hasMoved()` returns a boolean value indicating whether an
     * object has moved.
     * 
     * @return The method `hasMoved()` returns the value of the `hasMoved` instance
     *         variable.
     */
    public boolean hasMoved() {
        return this.hasMoved;
    }

    /**
     * The function setX(int x) sets the value of the variable x to the input
     * parameter x.
     * 
     * @param x The parameter `x` is an integer value that is being passed to the
     *          `setX` method.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * The function sets the value of the variable "y" to the provided input value.
     * 
     * @param y The parameter "y" represents the value that will be assigned to the
     *          instance variable
     *          "y" in the class.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * This function sets the "alive" status of an object in Java.
     * 
     * @param alive The `alive` parameter is a boolean variable that indicates
     *              whether an object is
     *              alive or not. It is used to set the state of an object to either
     *              alive (true) or not alive
     *              (false).
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * The function `setWhite` sets the value of a boolean variable `white`.
     * 
     * @param white The `white` parameter in the `setWhite` method is a boolean
     *              value that indicates
     *              whether an object is white or not. When the `setWhite` method is
     *              called with a `true` value for
     *              the `white` parameter, it sets the object to be white.
     */
    public final void setWhite(boolean white) {
        this.white = white;
    } 

    /**
     * This abstract method checks if a move on the board from a starting position
     * to an ending position
     * is valid.
     * 
     * @param board  The `board` parameter represents the game board on which the
     *               move is being made. It
     *               likely contains information about the current state of the
     *               game, such as the positions of pieces.
     * @param startX The `startX` parameter represents the x-coordinate of the
     *               starting position of a
     *               piece on the board.
     * @param startY The `startY` parameter represents the starting y-coordinate of
     *               the piece on the
     *               board where the move is originating from.
     * @param endX   The `endX` parameter represents the x-coordinate of the ending
     *               position on the board
     *               where the move is intended to go.
     * @param endY   The `endY` parameter represents the y-coordinate of the ending
     *               position on the board
     *               where the move is intended to go. This parameter is used in the
     *               `validMove` method to determine
     *               if a move from the starting position `(startX, startY)` to the
     *               ending position `(endX, end
     * @return A boolean value is being returned, indicating whether the move from
     *         the starting position
     *         (startX, startY) to the ending position (endX, endY) on the board is
     *         valid or not.
     */
    public abstract boolean validMove(Board board, int startX, int startY, int endX, int endY);
}

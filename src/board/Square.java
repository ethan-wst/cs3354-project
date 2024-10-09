package board;

/**
 * Represents a single square on a board
 */
public final class Square {
   private Piece piece;
   private int x;
   private int y;

   /**
    * Creates a square with a specific x,y coordinate and piece
    * @param x X coordinate of square
    * @param y Y coordinate of square
    * @param piece Piece that occupies the square (null if none)
    */
   public Square(int x, int y, Piece piece) {
    this.setPiece(piece);
    this.setX(x);
    this.setY(y);
   }

   /**
    * Gets the piece on the square
    * @return A piece object if any and null otherwise
    */
   public Piece getPiece() {
    return this.piece;
   }

   /**
    * Sets the piece of the square
    * @param piece A piece object (null if none)
    */
   public void setPiece(Piece piece) {
    this.piece = piece;
   }

   /**
    * Gets the X coordinate of the square
    * @return The integer X coordinate of the square
    */
   public int getX() {
    return this.x;
   }
   
   /**
    * Sets the X coordinate of the square
    * @param x The integer X coordinate of the square
    */
   public void setX(int x) {
    this.x = x;
   }

   /**
    * Gets the Y coordinate of the square
    * @return The integer Y coordinate of the square
    */
   public int getY() {
    return this.y;
   }

   /**
    * Sets the Y coordinate of the square
    * @param y The integer Y coordinate of the square
    */
   public void setY(int y) {
    this.y = y;
   }
}

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
   public Square(int x, int y) {
      this.x = x;
      this.y = y;
      piece = null;
   }

   public Piece occupySpot(Piece piece) {
      Piece origin = this.piece;
      //if piece already here, delete it, ie set it to dead
      if (this.piece != null) {
         this.piece.setAlive(false);
      }
      //place piece here
      this.piece = piece;
      return origin;
   }


   public boolean isOccupied() {
      return piece != null;
   }

   public Piece releaseSpot() {
      Piece releasedPiece = this.piece;
      this.piece = null;
      return releasedPiece;
   }

   public Piece getPiece() {
      return this.piece;
   }
}
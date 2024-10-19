package board;

/**
 * This class represents a square shape.
 */
public final class Square {
   private Piece piece;

   /**
    * Creates a square with a specific piece
    * 
    * @param piece Piece that occupies the square (null if none)
    */
   public Square(Piece piece) {
      this.piece = piece;
   }

   /**
    * Used to move a piece to a square, and get old piece.
    * 
    * @param piece Piece moved to square
    * @return Piece that was on square, null if none
    */
   public Piece occupySquare(Piece piece) {
      Piece origin = this.piece;
      // if piece already here, delete it, ie set it to dead
      if (this.piece != null) {
         this.piece.setAlive(false);
         this.piece.setX(-1);
         this.piece.setY(-1);
      }
      this.piece = piece;
      return origin;
   }

   /**
    * Gets the status of the square
    * @return True if square has a piece, false otherwise
    */
   public boolean isOccupied() {
      return piece != null;
   }

   /**
    * Sets square to null, used for moving pieces
    * @return Piece that was on spot
    */
   public Piece releaseSquare() {
      Piece releasedPiece = this.piece;
      this.piece = null;
      return releasedPiece;
   }

   /**
    * Gets the piece that is currently on the square
    * @return piece on square
    */
   public Piece getPiece() {
      return this.piece;
   }
}
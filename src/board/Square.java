package board;

import pieces.Piece;

/**
 * This class represents a square shape.
 */
public final class Square {
   private Piece piece;

   /**
    * Creates a square with a specific piece
    * @param piece Piece that occupies the square (null if none)
    */
   public Square(Piece piece) {
      this.piece = piece;
   }


   /**
    * Used to move a piece to a square, and get old piece. Acts as a mutator method
    * @param piece Piece moved to square
    * @return Piece that was on square, null if none
    */
   public Piece occupySquare(Piece movingPiece) {
      Piece origin = piece;
      // if piece already here, delete it, ie set it to dead
      this.piece = movingPiece;
      return origin;
   }


   /**
    * Sets the piece on the square to null
    */
   public void releaseSquare() {
      this.piece = null;
   }

   
   /**
    * Accessor method for the piece that is currently on the square
    * @return piece on square
    */
   public Piece getPiece() {
      return this.piece;
   }
}
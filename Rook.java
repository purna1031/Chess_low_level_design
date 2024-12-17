package chess;

public class Rook extends Piece {

    public Rook(String color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position start, Position end, ChessBoard board) {
        // Check if the move is horizontal or vertical
        if (start.getRow() != end.getRow() && start.getCol() != end.getCol()) {
            return false; // Rook can only move horizontally or vertically
        }

        // Check if there are any pieces in the way
        if (start.getRow() == end.getRow()) {
            // Moving horizontally
            int colStart = Math.min(start.getCol(), end.getCol()) + 1;
            int colEnd = Math.max(start.getCol(), end.getCol()) - 1;
            for (int col = colStart; col <= colEnd; col++) {
                if (board.getPiece(new Position(start.getRow(), col)) != null) {
                    return false; // There's a piece in the way
                }
            }
        } else if (start.getCol() == end.getCol()) {
            // Moving vertically
            int rowStart = Math.min(start.getRow(), end.getRow()) + 1;
            int rowEnd = Math.max(start.getRow(), end.getRow()) - 1;
            for (int row = rowStart; row <= rowEnd; row++) {
                if (board.getPiece(new Position(row, start.getCol())) != null) {
                    return false; // There's a piece in the way
                }
            }
        }

        // Check if the destination is occupied by a piece of the same color
        Piece targetPiece = board.getPiece(end);
        if (targetPiece != null && targetPiece.getColor().equals(this.color)) {
            return false; // Can't land on a piece of the same color
        }

        return true; // Valid move
    }

    @Override
    public char getSymbol() {
        return color.equals("white") ? 'R' : 'r';
    }
}

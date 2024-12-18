package chess;

public class Rook extends Piece {

    public Rook(String color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position start, Position end, ChessBoard board) {
        if (start.getRow() != end.getRow() && start.getCol() != end.getCol()) {
            return false; 
        }
        if (start.getRow() == end.getRow()) {
            int colStart = Math.min(start.getCol(), end.getCol()) + 1;
            int colEnd = Math.max(start.getCol(), end.getCol()) - 1;
            for (int col = colStart; col <= colEnd; col++) {
                if (board.getPiece(new Position(start.getRow(), col)) != null) {
                    return false; 
                }
            }
        } else if (start.getCol() == end.getCol()) {
            int rowStart = Math.min(start.getRow(), end.getRow()) + 1;
            int rowEnd = Math.max(start.getRow(), end.getRow()) - 1;
            for (int row = rowStart; row <= rowEnd; row++) {
                if (board.getPiece(new Position(row, start.getCol())) != null) {
                    return false; 
                }
            }
        }
        Piece targetPiece = board.getPiece(end);
        if (targetPiece != null && targetPiece.getColor().equals(this.color)) {
            return false; 
        }

        return true; 
    }

    @Override
    public char getSymbol() {
        return color.equals("white") ? 'R' : 'r';
    }
}

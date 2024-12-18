package chess;

public class Pawn extends Piece {
    public Pawn(String color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position start, Position end, ChessBoard board) {
        int direction = color.equals("white") ? -1 : 1; 
        if (start.getCol() == end.getCol() && end.getRow() == start.getRow() + direction
                && board.getPiece(end) == null) {
            return true;
        }
        if (start.getCol() == end.getCol() &&
                ((start.getRow() == 6 && color.equals("white")) || (start.getRow() == 1 && color.equals("black"))) &&
                end.getRow() == start.getRow() + 2 * direction &&
                board.getPiece(end) == null) {
            Position middle = new Position(start.getRow() + direction, start.getCol());
            return board.getPiece(middle) == null; 
        }
        if (Math.abs(start.getCol() - end.getCol()) == 1 && end.getRow() == start.getRow() + direction &&
                board.getPiece(end) != null && !board.getPiece(end).getColor().equals(color)) {
            return true;
        }
        return false;
    }

    @Override
    public char getSymbol() {
        return color.equals("white") ? 'P' : 'p';
    }
}

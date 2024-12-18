package chess;

public class Bishop extends Piece {
    public Bishop(String color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position start, Position end, ChessBoard board) {
        return true; 
    }

    @Override
    public char getSymbol() {
        return color.equals("white") ? 'B' : 'b';
    }
}

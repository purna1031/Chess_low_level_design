package chess;

public class Queen extends Piece {
    public Queen(String color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position start, Position end, ChessBoard board) {
        return true; 
    }

    @Override
    public char getSymbol() {
        return color.equals("white") ? 'Q' : 'q';
    }
}

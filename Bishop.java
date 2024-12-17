package chess;

public class Bishop extends Piece {
    public Bishop(String color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position start, Position end, ChessBoard board) {
        // Add bishop-specific movement logic here
        return true; // Placeholder
    }

    @Override
    public char getSymbol() {
        return color.equals("white") ? 'B' : 'b';
    }
}

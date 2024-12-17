package chess;

public class King extends Piece {
    public King(String color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position start, Position end, ChessBoard board) {
        // Add king-specific movement logic here
        return true; // Placeholder
    }

    @Override
    public char getSymbol() {
        return color.equals("white") ? 'K' : 'k';
    }
}

package chess;

public class Knight extends Piece {
    public Knight(String color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position start, Position end, ChessBoard board) {
        // Add knight-specific movement logic here
        return true; // Placeholder
    }

    @Override
    public char getSymbol() {
        return color.equals("white") ? 'N' : 'n';
    }
}

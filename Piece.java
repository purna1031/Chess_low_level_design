package chess;

public abstract class Piece {
    protected String color; // "white" or "black"
    protected Position position;

    public Piece(String color, Position position) {
        this.color = color;
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract boolean isValidMove(Position start, Position end, ChessBoard board);

    public abstract char getSymbol();
}

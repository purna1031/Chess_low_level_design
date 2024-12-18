package chess;

public class ChessBoard {
    private Piece[][] board;

    public ChessBoard() {
        board = new Piece[8][8];
        initializeBoard();
    }
    public void initializeBoard() {
        board[0][0] = new Rook("black", new Position(0, 0));
        board[0][7] = new Rook("black", new Position(0, 7));
        board[7][0] = new Rook("white", new Position(7, 0));
        board[7][7] = new Rook("white", new Position(7, 7));
        board[0][1] = new Knight("black", new Position(0, 1));
        board[0][6] = new Knight("black", new Position(0, 6));
        board[7][1] = new Knight("white", new Position(7, 1));
        board[7][6] = new Knight("white", new Position(7, 6));
        board[0][2] = new Bishop("black", new Position(0, 2));
        board[0][5] = new Bishop("black", new Position(0, 5));
        board[7][2] = new Bishop("white", new Position(7, 2));
        board[7][5] = new Bishop("white", new Position(7, 5));
        board[0][3] = new Queen("black", new Position(0, 3));
        board[7][3] = new Queen("white", new Position(7, 3));
        board[0][4] = new King("black", new Position(0, 4));
        board[7][4] = new King("white", new Position(7, 4));

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("black", new Position(1, i));
            board[6][i] = new Pawn("white", new Position(6, i));
        }
    }

    public boolean movePiece(Position start, Position end, String currentTurn) {
        if (!isValidPosition(start) || !isValidPosition(end)) {
            System.out.println("Invalid position!");
            return false;
        }

        Piece piece = getPiece(start);
        if (piece == null) {
            System.out.println("No piece at the start position!");
            return false;
        }

        if (!piece.getColor().equals(currentTurn)) {
            System.out.println("Not " + currentTurn + "'s turn!");
            return false;
        }

        if (!piece.isValidMove(start, end, this)) {
            System.out.println("Invalid move for " + piece.getClass().getSimpleName());
            return false;
        }

        if (isBlocked(start, end, piece)) {
            System.out.println("Path is blocked!");
            return false;
        }

        setPiece(end, piece);
        setPiece(start, null);
        piece.setPosition(end);
        return true;
    }

    public boolean isValidPosition(Position position) {
        return position.getRow() >= 0 && position.getRow() < 8 &&
                position.getCol() >= 0 && position.getCol() < 8;
    }

   
    public Piece getPiece(Position position) {
        if (!isValidPosition(position)) {
            return null;
        }
        return board[position.getRow()][position.getCol()];
    }

    public void setPiece(Position position, Piece piece) {
        if (!isValidPosition(position)) {
            throw new IllegalArgumentException("Invalid position: " + position);
        }
        board[position.getRow()][position.getCol()] = piece;
    }

    // position conversion
    public static Position convertPosition(String pos) {
        int col = pos.charAt(0) - 'a'; // Column: 'a' -> 0, ..., 'h' -> 7
        int row = 8 - (pos.charAt(1) - '0'); // Row: '1' -> 7, ..., '8' -> 0
        if (row < 0 || row >= 8 || col < 0 || col >= 8) {
            return null;
        }
        return new Position(row, col);
    }

    // Print the board 
    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece == null) {
                    System.out.print(" . ");
                } else {
                    System.out.print(" " + piece.getSymbol() + " ");
                }
            }
            System.out.println();
        }
    }

    // blocked
    public boolean isBlocked(Position start, Position end, Piece piece) {
        int rowDiff = end.getRow() - start.getRow();
        int colDiff = end.getCol() - start.getCol();
        if (Math.abs(rowDiff) == Math.abs(colDiff)) {
            int rowStep = rowDiff > 0 ? 1 : -1;
            int colStep = colDiff > 0 ? 1 : -1;
            int row = start.getRow() + rowStep;
            int col = start.getCol() + colStep;

            while (row != end.getRow() && col != end.getCol()) {
                if (board[row][col] != null) {
                    return true;
                }
                row += rowStep;
                col += colStep;
            }
        }
        else if (rowDiff == 0 || colDiff == 0) {
            int rowStep = (rowDiff == 0) ? 0 : (rowDiff > 0 ? 1 : -1);
            int colStep = (colDiff == 0) ? 0 : (colDiff > 0 ? 1 : -1);
            int row = start.getRow() + rowStep;
            int col = start.getCol() + colStep;

            while (row != end.getRow() || col != end.getCol()) {
                if (board[row][col] != null) {
                    return true;
                }
                row += rowStep;
                col += colStep;
            }
        }

        return false;
    }



    public boolean isKingCaptured(String opponentColor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece instanceof King && piece.getColor().equals(opponentColor)) {
                    return false;
                }
            }
        }
        return true;
    }
}
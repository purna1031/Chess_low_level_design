package chess;

import java.util.Scanner;

public class Game {
    private ChessBoard board;
    private String currentTurn;

    public Game(String whitePlayer, String blackPlayer) {
        board = new ChessBoard();
        currentTurn = "white";
    }

    public void startGame() {
        System.out.println("Welcome to Chess!");
        board.printBoard();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(currentTurn + "'s turn. Enter your move (e.g., e2 e4): ");
            String move = scanner.nextLine();

            if (!processMove(move)) {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            board.printBoard();

            if (board.isKingCaptured(currentTurn.equals("white") ? "black" : "white")) {
                System.out.println(currentTurn + " wins! Game over.");
                break;
            }

            currentTurn = currentTurn.equals("white") ? "black" : "white";
        }

        scanner.close();
    }

    private boolean processMove(String move) {
        String[] positions = move.split(" ");
        if (positions.length != 2) {
            System.out.println("Invalid input format! Use chess notation like 'e2 e4'.");
            return false;
        }

        Position start = ChessBoard.convertPosition(positions[0]);
        Position end = ChessBoard.convertPosition(positions[1]);

        if (start == null || end == null) {
            System.out.println("Invalid position format! Use chess notation like 'e2 e4'.");
            return false;
        }

        Piece piece = board.getPiece(start);
        if (piece == null) {
            System.out.println("No piece at the start position.");
            return false;
        }

        if (!piece.getColor().equals(currentTurn)) {
            System.out.println("Invalid move! You cannot move an opponent's piece.");
            return false;
        }

        if (board.movePiece(start, end, currentTurn)) {
            return true;
        } else {
            System.out.println("Invalid move! The move is not allowed.");
            return false;
        }
    }
}

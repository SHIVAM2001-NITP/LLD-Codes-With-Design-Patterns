package service;

import entities.Board;
import entities.Move;
import entities.Position;
import enums.Color;
import enums.GameStatus;
import exceptions.InvalidMoveException;
import pieces.King;
import pieces.Piece;

import java.util.ArrayList;
import java.util.List;

// Orchestrates turns on top of the board: validates a move's shape and legality (does not
// leave the mover's own king in check), applies it, switches turns and computes status.
public class Game {
    private final Board board = new Board();
    private final List<Move> history = new ArrayList<>();
    private Color currentTurn = Color.WHITE;
    private GameStatus status = GameStatus.ACTIVE;

    public Board getBoard() { return board; }

    public Color getCurrentTurn() { return currentTurn; }

    public GameStatus getStatus() { return status; }

    public synchronized Move makeMove(Position from, Position to) {
        if (status == GameStatus.CHECKMATE || status == GameStatus.STALEMATE) {
            throw new InvalidMoveException("The game is already over");
        }
        Piece piece = board.getPiece(from);
        if (piece == null) {
            throw new InvalidMoveException("No piece on " + from);
        }
        if (piece.getColor() != currentTurn) {
            throw new InvalidMoveException("It is " + currentTurn + "'s turn, that piece is " + piece.getColor());
        }
        if (!piece.canMove(board, from, to)) {
            throw new InvalidMoveException(piece + " cannot move " + from + " -> " + to);
        }

        Piece captured = simulateAndCheckSelf(from, to, piece, currentTurn);
        Move move = new Move(from, to, piece, captured);
        history.add(move);
        piece.markMoved();
        currentTurn = currentTurn.opposite();
        updateStatus();
        return move;
    }

    // Applies the move, then reverts it if it would leave the mover's own king in check
    private Piece simulateAndCheckSelf(Position from, Position to, Piece piece, Color mover) {
        Piece captured = board.getPiece(to);
        board.setPiece(to, piece);
        board.setPiece(from, null);

        boolean selfInCheck = board.isSquareAttacked(board.findKing(mover), mover.opposite());
        if (selfInCheck) {
            board.setPiece(from, piece);
            board.setPiece(to, captured);
            throw new InvalidMoveException("That move would leave " + mover + "'s king in check");
        }
        return captured;
    }

    private void updateStatus() {
        boolean inCheck = board.isSquareAttacked(board.findKing(currentTurn), currentTurn.opposite());
        boolean hasMove = hasAnyLegalMove(currentTurn);
        if (inCheck) {
            status = hasMove ? GameStatus.CHECK : GameStatus.CHECKMATE;
        } else {
            status = hasMove ? GameStatus.ACTIVE : GameStatus.STALEMATE;
        }
    }

    // Tries every (piece, destination) pair for `color` and returns true as soon as one does
    // not leave that color's own king in check. The board is small, so a full scan is cheap.
    private boolean hasAnyLegalMove(Color color) {
        for (int fr = 0; fr < 8; fr++) {
            for (int fc = 0; fc < 8; fc++) {
                Position from = new Position(fr, fc);
                Piece piece = board.getPiece(from);
                if (piece == null || piece.getColor() != color) {
                    continue;
                }
                for (int tr = 0; tr < 8; tr++) {
                    for (int tc = 0; tc < 8; tc++) {
                        Position to = new Position(tr, tc);
                        if (piece.canMove(board, from, to) && wouldBeSafe(from, to, piece, color)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean wouldBeSafe(Position from, Position to, Piece piece, Color mover) {
        Piece captured = board.getPiece(to);
        board.setPiece(to, piece);
        board.setPiece(from, null);
        boolean safe = !board.isSquareAttacked(board.findKing(mover), mover.opposite());
        board.setPiece(from, piece);
        board.setPiece(to, captured);
        return safe;
    }
}

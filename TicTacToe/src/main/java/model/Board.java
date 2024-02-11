package model;

import com.sun.tools.javac.util.Pair;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {
    public int size;
    public PlayingPiece[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new PlayingPiece[size][size];
    }


    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].pieceType.name() + "   ");
                } else {
                    System.out.print("    ");

                }
                System.out.print(" | ");
            }
            System.out.println();

        }
    }

    public List<AbstractMap.SimpleEntry<Integer, Integer>> getFreeCells() {
        List<AbstractMap.SimpleEntry<Integer, Integer>> freeCells = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    AbstractMap.SimpleEntry<Integer, Integer> rowColumn = new AbstractMap.SimpleEntry<>(i, j);
                    freeCells.add(rowColumn);
                }
            }
        }

        return freeCells;
    }

    public boolean addPiece(int inputRow, int inputColumn, PlayingPiece piece) {
        if (board[inputRow][inputColumn] != null) {
            return false;
        }
        board[inputRow][inputColumn] = piece;
        return true;
    }
}

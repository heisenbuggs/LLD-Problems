import model.Board;
import model.PieceType;
import model.Player;
import model.PlayingPiece;
import model.PlayingPieceX;

import java.util.AbstractMap;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    Deque<Player> players;
    
    Board gameBoard;
    
    TicTacToe() {
        initializeGame();
    }

    void initializeGame() {
        // Creating 2 players
        players = new LinkedList<>();

        PlayingPiece crossPiece = new PlayingPieceX(PieceType.X);
        Player player1 = new Player("Player 1", crossPiece);

        PlayingPiece noughtsPiece = new PlayingPiece(PieceType.O);
        Player player2 = new Player("Player 2", noughtsPiece);

        players.add(player1);
        players.add(player2);

        // initializeBoard
        gameBoard = new Board(3);
    }

    public String startGame(){
        boolean noWinner = true;

        while (noWinner) {
            // Take out the player whose turn is and also put the player in the list back
            Player playerTurn = players.removeFirst();

            // Get the free space from the board
            gameBoard.printBoard();

            List<AbstractMap.SimpleEntry<Integer, Integer>> freeSpaces = gameBoard.getFreeCells();

            if (freeSpaces.isEmpty()) {
                noWinner = false;
                continue;
            }

            // Read the user input
            System.out.println("Player : " + playerTurn.name + " Enter row, column : ");
            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.parseInt(values[0]);
            int inputColumn = Integer.parseInt(values[1]);

            // Place the piece
            boolean pieceAddedSuccessfully = gameBoard.addPiece(inputRow, inputColumn, playerTurn.piece);
            if (!pieceAddedSuccessfully) {
                // Player can not insert the piece into this cell, player has to choose another cell
                System.out.println("Incorrect position chosen, try again");
                players.addFirst(playerTurn);
                continue;
            }
            players.addLast(playerTurn);

            boolean winner = isThereWinner(inputRow, inputColumn, playerTurn.piece.pieceType);
            if (winner) {
                return playerTurn.name;
            }
        }
        return "tie";
    }

    public boolean isThereWinner(int row, int column, PieceType pieceType) {

        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        // Need to check in row
        for (int i=0; i<gameBoard.size; i++) {

            if (gameBoard.board[row][i] == null || gameBoard.board[row][i].pieceType != pieceType) {
                rowMatch = false;
            }
        }

        // Need to check in column
        for (int i=0; i<gameBoard.size; i++) {

            if (gameBoard.board[i][column] == null || gameBoard.board[i][column].pieceType != pieceType) {
                columnMatch = false;
            }
        }

        // Need to check diagonals
        for (int i=0, j=0; i<gameBoard.size; i++, j++) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                diagonalMatch = false;
            }
        }

        // Need to check anti-diagonals
        for (int i=0, j=gameBoard.size-1; i<gameBoard.size; i++, j--) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }




}

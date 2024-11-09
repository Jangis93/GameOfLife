package org.example.v1;

import java.util.Random;


// First version of Game of Life

public class CellBoard implements Board {

    private int[][] board;
    private final int boardSize;
    private final Random random = new Random();

    public CellBoard(int boardSize) {
        this(boardSize, 0.2);
    }

    public CellBoard(int boardSize, double aliveProbability) {
        int[][] tempBoard = new int[boardSize][boardSize];

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                tempBoard[i][j] =  getRandom(aliveProbability);
            }
        }
        board = tempBoard;
        this.boardSize = boardSize;
    }


    @Override
    public void print() {
        StringBuilder sb = new StringBuilder();

        // Top border
        sb.append("-".repeat(boardSize + 2)).append("\n");

        for(int i = 0; i < boardSize; i++) {
            sb.append("|"); // Left border
            for (int j = 0; j < boardSize; j++) {
                sb.append(board[i][j] == 1 ? 'X' : "-");
            }
            sb.append("|\n"); // Right border
        }
        // Bottom border
        sb.append("-".repeat(boardSize + 2));

        System.out.println(sb);
    }

    @Override
    public void evolve() {
        var newBoard = new int[boardSize][boardSize];
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {

                var aliveNeighbours = countAliveNeighbours(i, j);

                // Apply the Game of Life rules
                if (board[i][j] == 1 && (aliveNeighbours == 2 || aliveNeighbours == 3)) {
                    newBoard[i][j] = 1; // Cell stays alive
                } else if (board[i][j] == 0 && aliveNeighbours == 3) {
                    newBoard[i][j] = 1; // Cell becomes alive
                } else {
                    newBoard[i][j] = 0; // Cell dies or stays dead
                }
            }
        }
        board = newBoard;
    }

    private int countAliveNeighbours(int i, int j) {

        var neighbours = 0;
        for(int k = i - 1; k <= i + 1; k++) {
            for(int l = j - 1; l <= j + 1; l++) {
                if ((k != i || l != j) && k >= 0 && k < boardSize && l >= 0 && l < boardSize) {
                    neighbours += board[k][l];
                }
            }
        }
        return neighbours;
    }

    private int getRandom(double aliveProbability) {
        return random.nextDouble() < aliveProbability ? 1 : 0;
    }
}

package org.example.v2;

import java.util.Random;

public abstract class Board {

    protected int[][] board;
    protected int boardSize;

    private final Random random = new Random();

    Board(int boardSize, double aliveProbability) {
        int[][] tempBoard = new int[boardSize][boardSize];

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                tempBoard[i][j] =  getRandom(aliveProbability);
            }
        }
        board = tempBoard;
        this.boardSize = boardSize;
    }

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

    abstract void evolve();

    private int getRandom(double aliveProbability) {
        return random.nextDouble() < aliveProbability ? 1 : 0;
    }
}

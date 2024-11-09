package org.example.v2;

// Second version of Game of Life - uses recursion
public class CellBoard extends Board {

    public CellBoard(int boardSize, double aliveProbability) {
        super(boardSize, aliveProbability);
    }

    @Override
    public void evolve() {
        var newBoard = new int[boardSize][boardSize];
        validateCellsRecursive(0, 0, newBoard);

        board = newBoard;
    }

    private void validateCellsRecursive(int i, int j, int[][] newBoard) {
        // we have iterated over the whole board
        if(i >= boardSize) {
            return;
        }
        var aliveNeighbours = countAliveNeighboursRecursive(i, j);

        if (board[i][j] == 1 && (aliveNeighbours == 2 || aliveNeighbours == 3)) {
            newBoard[i][j] = 1; // Cell stays alive
        } else if (board[i][j] == 0 && aliveNeighbours == 3) {
            newBoard[i][j] = 1; // Cell becomes alive
        } else {
            newBoard[i][j] = 0; // Cell dies or stays dead
        }

        // we have reached the end of the row -> next function call on new row
        if(j + 1 == boardSize) {
            validateCellsRecursive(i + 1, 0, newBoard);
        } else {
            // next function call on new column
            validateCellsRecursive(i, j + 1, newBoard);
        }

    }

    private int countAliveNeighboursRecursive(int i, int j) {
        return count(i, j, 0, 0);
    }

    private int count(int i, int j, int offsetIndex, int count) {
        int[][] offsets = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},          {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        if (offsetIndex >= offsets.length) {
            return count;
        }

        // Get the current neighbor's row and column offsets
        int k = offsets[offsetIndex][0];
        int l = offsets[offsetIndex][1];
        int neighborRow = i + k;
        int neighborCol = j + l;

        // Check if the neighbor cell is within bounds
        if (neighborRow >= 0 && neighborRow < boardSize && neighborCol >= 0 && neighborCol < boardSize) {
            count += board[neighborRow][neighborCol]; // Add neighbor cell's value if within bounds
        }

        // Recursive call to check the next neighbor
        return count(i, j, offsetIndex + 1, count);
    }
}

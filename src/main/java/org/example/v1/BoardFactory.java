package org.example.v1;

public class BoardFactory {

    public Board createBoard(int boardSize) {
        return new CellBoard(boardSize);
    }
}

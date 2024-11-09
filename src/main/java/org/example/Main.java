package org.example;

import org.example.v2.CellBoard;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*
        First version of GoL with basic approach:
        BoardFactory factory = new BoardFactory();
        Board board = factory.createBoard(9);
        */
        CellBoard board = new CellBoard(9, 0.2);
        var counter = 0;
        while(counter < 50) {
            board.print();
            board.evolve();
            counter++;
            Thread.sleep(1000);
        }

    }
}
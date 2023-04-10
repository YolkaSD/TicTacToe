package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {

    private final List<Integer> freePos = new ArrayList<>(Arrays.asList(1, 2, 3, 4 ,5 ,6 ,7 ,8, 9));

    public List<Integer> getFreePos() {
        return freePos;
    }

    private final char[][] gameBoard = {
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}
    };

    public void printGameBoard() {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public boolean removeFreePos(Integer pos) {
        if (freePos.contains(pos)) {
            this.freePos.remove(pos);
            return true;
        }
        return false;
    }


    public void updateGameBoard(int row, int col, char symbol) {
        gameBoard[row][col] = symbol;
    }

}

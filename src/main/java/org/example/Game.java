package org.example;

import java.util.*;

public class Game {
    private static final int TOP_LEFT = 1;
    private static final int TOP_CENTER = 2;
    private static final int TOP_RIGHT = 3;
    private static final int MIDDLE_LEFT = 4;
    private static final int MIDDLE_CENTER = 5;
    private static final int MIDDLE_RIGHT = 6;
    private static final int BOTTOM_LEFT = 7;
    private static final int BOTTOM_CENTER = 8;
    private static final int BOTTOM_RIGHT = 9;
    private String player = "player";
    private boolean inGame = true;

    private final List<Integer> playerPositions = new ArrayList<>();
    private final List<Integer> CPUPositions = new ArrayList<>();
    private final GameBoard gameBoard = new GameBoard();

    private boolean checkWinner() {
        List<Integer> topRow = List.of(1, 2, 3);
        List<Integer> midRow = List.of(4, 5, 6);
        List<Integer> botRow = List.of(7, 8, 9);
        List<Integer> leftCol = List.of(1, 4, 7);
        List<Integer> midCol = List.of(2, 5, 8);
        List<Integer> rightCol = List.of(3, 6, 9);
        List<Integer> cross1 = List.of(1, 5, 9);
        List<Integer> cross2 = List.of(3, 5, 7);

        List<List<Integer>> winning = List.of(topRow, midRow, botRow, leftCol, midCol, rightCol, cross1, cross2);

        for (List<Integer> l : winning) {
            if (new HashSet<>(playerPositions).containsAll(l)) {
                System.out.println("Congratulations you won!");
                return false;
            } else if (new HashSet<>(CPUPositions).containsAll(l)) {
                System.out.println("CPU wins!");
                return false;
            }
        }

        if (gameBoard.getFreePos().isEmpty()) {
            System.out.println("CAT!");
            return false;
        }
        return true;
    }

    private int askUserForMove() {
        int pos = 0;
        boolean isCorrect = false;
        if (player.equals("player")) {
            while (!isCorrect) {
                try {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter your placement (1-9): ");
                    pos = scanner.nextInt();
                    if (gameBoard.removeFreePos(pos)) {
                        isCorrect = true;
                        playerPositions.add(pos);
                    } else {
                        System.out.println("Enter a correct Position!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("You must enter a number!");
                }
            }
        } else {
            while (!isCorrect) {
                pos = new Random().nextInt(9) + 1;
                isCorrect = gameBoard.removeFreePos(pos);
            }
            CPUPositions.add(pos);
        }
        return pos;
    }

    private void switchPlayer(){
        if (this.player.equals("player"))
            this.player = "cpu";
        else
            this.player = "player";
    }

    private void gameBoardManagement() {
        char symbol = player.equals("cpu") ? 'O' : 'X';
        switch (askUserForMove()) {
            case TOP_LEFT:
                gameBoard.updateGameBoard(0,0, symbol);
                break;
            case TOP_CENTER:
                gameBoard.updateGameBoard(0,2, symbol);
                break;
            case TOP_RIGHT:
                gameBoard.updateGameBoard(0,4, symbol);
                break;
            case MIDDLE_LEFT:
                gameBoard.updateGameBoard(2,0, symbol);
                break;
            case MIDDLE_CENTER:
                gameBoard.updateGameBoard(2,2, symbol);
                break;
            case MIDDLE_RIGHT:
                gameBoard.updateGameBoard(2,4, symbol);
                break;
            case BOTTOM_LEFT:
                gameBoard.updateGameBoard(4,0, symbol);
                break;
            case BOTTOM_CENTER:
                gameBoard.updateGameBoard(4,2, symbol);
                break;
            case BOTTOM_RIGHT:
                gameBoard.updateGameBoard(4,4, symbol);
                break;
        }
    }

    public void startGame() {
        while (inGame) {
            gameBoard.printGameBoard();
            gameBoardManagement();
            System.out.println("======================");
            switchPlayer();
            inGame = checkWinner();
        }
        gameBoard.printGameBoard();
    }
}

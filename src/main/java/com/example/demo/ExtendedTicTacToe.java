package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ExtendedTicTacToe extends Application {

    private static final int SIZE = 5; // Size of the board
    private static final int WINNING_LENGTH = 3; // Length of consecutive marks needed to win
    private Button[][] buttons = new Button[SIZE][SIZE];
    private char currentPlayer = 'X';
    private boolean gameOver = false;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Button button = new Button();
                button.setMinSize(80, 80);
                int finalI = i;
                int finalJ = j;
                button.setOnAction(e -> {
                    if (!gameOver && button.getText().isEmpty()) {
                        button.setText(String.valueOf(currentPlayer));
                        if (checkWin(finalI, finalJ)) {
                            gameOver = true;
                            showWinner(currentPlayer);
                        } else if (isBoardFull()) {
                            gameOver = true;
                            showDraw();
                        } else {
                            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                        }
                    }
                });
                grid.add(button, j, i);
                buttons[i][j] = button;
            }
        }

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Extended Tic Tac Toe");
        primaryStage.show();
    }

    private boolean checkWin(int row, int col) {
        char mark = currentPlayer;
        // Check row
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (buttons[row][i].getText().equals(String.valueOf(mark))) {
                count++;
            } else {
                count = 0;
            }
            if (count == WINNING_LENGTH) {
                return true;
            }
        }

        // Check column
        count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (buttons[i][col].getText().equals(String.valueOf(mark))) {
                count++;
            } else {
                count = 0;
            }
            if (count == WINNING_LENGTH) {
                return true;
            }
        }

        // Check diagonal
        count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (buttons[i][i].getText().equals(String.valueOf(mark))) {
                count++;
            } else {
                count = 0;
            }
            if (count == WINNING_LENGTH) {
                return true;
            }
        }

        // Check reverse diagonal
        count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (buttons[i][SIZE - 1 - i].getText().equals(String.valueOf(mark))) {
                count++;
            } else {
                count = 0;
            }
            if (count == WINNING_LENGTH) {
                return true;
            }
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWinner(char player) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("Player " + player + " wins!");
        alert.showAndWait();
    }

    private void showDraw() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("It's a draw!");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

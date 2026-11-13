
import java.util.Scanner;

public class TicTacToe {

    static char[][] board = new char[3][3];
    static char currentPlayer = 'X';

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("       TIC-TAC-TOE GAME");
        System.out.println("================================");

        boolean playAgain = true;

        while (playAgain) {

            initializeBoard();

            boolean gameOver = false;

            while (!gameOver) {

                displayBoard();

                System.out.println("Player " + currentPlayer + "'s turn.");

                int row = getValidInput("Enter row (1-3): ");
                int col = getValidInput("Enter column (1-3): ");

                // Check if the selected space is available
                if (board[row - 1][col - 1] != ' ') {
                    System.out.println("That space is already occupied!");
                    System.out.println("Please choose another space.\n");
                    continue;
                }

                // Place the player's mark
                board[row - 1][col - 1] = currentPlayer;

                // Check if the current player wins
                if (checkWinner()) {

                    displayBoard();

                    System.out.println("Congratulations!");
                    System.out.println("Player " + currentPlayer + " wins!");

                    gameOver = true;

                } else if (checkDraw()) {

                    displayBoard();

                    System.out.println("The game is a draw!");

                    gameOver = true;

                } else {

                    // Switch to the other player
                    switchPlayer();
                }
            }

            System.out.print("\nDo you want to play again? (Y/N): ");

            String choice = input.next();

            if (!choice.equalsIgnoreCase("Y")) {
                playAgain = false;
            }

            System.out.println();
        }

        System.out.println("================================");
        System.out.println(" Thank you for playing!");
        System.out.println("================================");

        input.close();
    }

    // Initialize all board spaces
    public static void initializeBoard() {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }

        currentPlayer = 'X';
    }

    // Display the game board
    public static void displayBoard() {

        System.out.println("\n       TIC-TAC-TOE");
        System.out.println();

        for (int row = 0; row < 3; row++) {

            System.out.print("   ");

            for (int col = 0; col < 3; col++) {

                System.out.print(" " + board[row][col] + " ");

                if (col < 2) {
                    System.out.print("|");
                }
            }

            System.out.println();

            if (row < 2) {
                System.out.println("   ---+---+---");
            }
        }

        System.out.println();
    }

    // Get valid row and column input
    public static int getValidInput(String message) {

        int number;

        while (true) {

            System.out.print(message);

            if (input.hasNextInt()) {

                number = input.nextInt();

                if (number >= 1 && number <= 3) {
                    return number;
                }

                System.out.println("Invalid input! Enter a number from 1 to 3.");

            } else {

                System.out.println("Invalid input! Please enter a number.");

                input.next();
            }
        }
    }

    // Check all possible winning combinations
    public static boolean checkWinner() {

        // Check rows
        for (int row = 0; row < 3; row++) {

            if (board[row][0] == currentPlayer &&
                board[row][1] == currentPlayer &&
                board[row][2] == currentPlayer) {

                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {

            if (board[0][col] == currentPlayer &&
                board[1][col] == currentPlayer &&
                board[2][col] == currentPlayer) {

                return true;
            }
        }

        // Check diagonal from top-left to bottom-right
        if (board[0][0] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][2] == currentPlayer) {

            return true;
        }

        // Check diagonal from top-right to bottom-left
        if (board[0][2] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][0] == currentPlayer) {

            return true;
        }

        return false;
    }

    // Check if all spaces are occupied
    public static boolean checkDraw() {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    // Switch between Player X and Player O
    public static void switchPlayer() {

        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }
}

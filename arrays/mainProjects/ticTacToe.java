package arrays.mainProjects;

import java.io.PrintWriter;
import java.util.stream.IntStream;

import javax.swing.text.StyledEditorKit.BoldAction;

public class ticTacToe {
  private Boolean[] board = new Boolean[9];
  private boolean turn = false; // false = x, true = o
  private int[] scores = new int[2];

  public static void main(String[] args) {
    ticTacToe game = new ticTacToe();
    printRules();
    game.resetBoard();
    // wait for input
    System.console().readLine("\033[3;90mPress enter to continue.\033[0m");
    for (;;) {
      game.printBoard();
      if (game.turn) {
        game.promptMove();
      } else {
        int botMove = game.bot();
        game.board[botMove] = false;
        game.turn = true;
      }
      if (game.isDraw()) {
        game.printBoard();
        System.out.println("Draw.");
        game.resetBoard();
        // wait for input
        System.console().readLine("\033[3;90mPress enter to continue.\033[0m");
      }
      if (game.check() != null) {
        boolean winner = game.board[game.check()[0]];
        game.scores[winner ? 1 : 0]++;
        game.turn = winner;
        game.printBoard();
        System.out.println("Winner: " + (winner ? "O" : "X"));
        game.resetBoard();
        // wait for input
        System.console().readLine("\033[3;90mPress enter to continue.\033[0m");
      }
    }
  }

  public int bot() {
    // for now just play random
    // maybe do minimax later
    int[] available = new int[9];
    int availableCount = 0;
    for (int i = 0; i < 9; i++) {
      if (board[i] == null) {
        available[availableCount] = i;
        availableCount++;
      }
    }
    return available[(int) (Math.random() * availableCount)];
  }

  public boolean isDraw() {
    for (Boolean square : board) {
      if (square == null) {
        return false;
      }
    }
    return true;
  }

  public int[] check() {
    // check for wins
    // check rows
    for (int i = 0; i < 3; i++) {
      if (board[i * 3] != null && board[i * 3] == board[i * 3 + 1] && board[i * 3] == board[i * 3 + 2]) {
        return new int[] { i * 3, i * 3 + 1, i * 3 + 2 };
      }
    }
    // check columns
    for (int i = 0; i < 3; i++) {
      if (board[i] != null && board[i] == board[i + 3] && board[i] == board[i + 6]) {
        return new int[] { i, i + 3, i + 6 };
      }
    }
    // check diagonals
    if (board[0] != null && board[0] == board[4] && board[0] == board[8]) {
      return new int[] { 0, 4, 8 };
    }
    if (board[2] != null && board[2] == board[4] && board[2] == board[6]) {
      return new int[] { 2, 4, 6 };
    }
    return null;
  }

  public static void printRules() {
    System.out.println("🇷 🇺 🇱 🇪 🇸​");
    System.out.println(" - The board is a 3x3 grid.");
    System.out.println(" - The first player is X, the second is O.");
    System.out.println(" - The first player to get 3 in a row wins.");
    System.out.println(" - The game ends in a tie if the board is full without a winner.");
    System.out.println(
        " - To play, enter the row and column of the space you want to play in.");
    System.out.println(
        "   - The first input method is a single number from 1 to 9, where 1 is the top left corner and 9 is the bottom right corner.");
    System.out.println(
        "   - The second is two numbers separated by a space, where the first number is the row and the second is the column.");
  }

  public void promptMove() {
    for (;;) {
      String input = System.console().readLine("Enter move: ").trim();
      // check if input is valid
      String inputOne = "^[0-9]$";
      String inputTwo = "^[0-9] [0-9]$";
      // if it doesn't match either, prompt again
      if (!input.matches(inputOne) && !input.matches(inputTwo)) {
        System.out.println("Invalid input.");
        continue;
      }
      int square = 0;
      // if it matches inputOne, convert to int
      if (input.matches(inputOne)) {
        square = Integer.parseInt(input);
      }
      // if it matches inputTwo, convert to int
      if (input.matches(inputTwo)) {
        String[] inputArray = input.split(" ");
        square = (Integer.parseInt(inputArray[1]) - 1) * 3 + Integer.parseInt(inputArray[0]);
      }

      square--;
      if (square < 0 || square > 8) {
        System.out.println("Invalid input.");
        continue;
      }

      if (board[square] != null) {
        System.out.println("Invalid input. Space already taken.");
        continue;
      }

      board[square] = this.turn;
      this.turn = !this.turn;
      break;
    }
  }

  public void printBoard() {
    clear();
    boolean faintSupport = true;
    boolean nerdFontSupport = true;
    int[] solution = null;

    System.out.println("\033[0;35mTic Tac Toe");

    // print turn and score
    {
      if (!this.turn) { // if x turn
        System.out.print("  \033[4;34m"); // underline
      } else { // if o turn
        System.out.print("  \033[2;34m"); // faint
      }

      if (!this.turn && faintSupport || this.turn && !faintSupport) {
        System.out.print("\033[0;34m"); // none
      }

      if (nerdFontSupport) {
        System.out.print("");
      } else {
        System.out.print("X");
      }
      System.out.print(":" + scores[0] + "\033[0m");

      if (this.turn) { // if o turn
        System.out.print(" \033[4;31m"); // underline
      } else { // if x turn
        System.out.print(" \033[2;31m"); // faint
      }

      if (this.turn && faintSupport || !this.turn && !faintSupport) {
        System.out.print("\033[0;31m"); // none
      }

      if (nerdFontSupport) {
        System.out.print("");
      } else {
        System.out.print("O");
      }
      System.out.print(":" + scores[1] + "\033[0m\n");
    }

    // print board
    {
      System.out.println(
          String.format(" %s │ %s │ %s ", formatSquare(board[0], solution), formatSquare(board[1], solution),
              formatSquare(board[2], solution)));
      System.out.println("───┼───┼───");
      System.out.println(
          String.format(" %s │ %s │ %s ", formatSquare(board[3], solution), formatSquare(board[4], solution),
              formatSquare(board[5], solution)));
      System.out.println("───┼───┼───");
      System.out.println(
          String.format(" %s │ %s │ %s ", formatSquare(board[6], solution), formatSquare(board[7], solution),
              formatSquare(board[8], solution)));
    }
  }

  public static String formatSquare(Boolean value, int[] solution) {
    boolean faintSupport = true;

    if (value == null) {
      return " ";
    } else if (!value) {
      if (solution == null) {
        return "\033[0;34mX\033[0m";
      }
      if (IntStream.of(solution).anyMatch(x -> x == 4)) {
        if (faintSupport) {
          return "\033[0;34mX\033[0m";
        } else {
          return "\033[1;34mX\033[0m";
        }
      }
      if (faintSupport) {
        return "\033[2;34mX\033[0m";
      } else {
        return "\033[0;34mX\033[0m";
      }
    } else {
      if (solution == null) {
        return "\033[0;31mO\033[0m";
      }
      if (IntStream.of(solution).anyMatch(x -> x == 4)) {
        if (faintSupport) {
          return "\033[0;31mO\033[0m";
        } else {
          return "\033[1;31mO\033[0m";
        }
      }
      if (faintSupport) {
        return "\033[2;31mO\033[0m";
      } else {
        return "\033[0;31mO\033[0m";
      }
    }
  }

  public static void clear() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public void resetBoard() {
    turn = false;
    board = new Boolean[9];
  }
}

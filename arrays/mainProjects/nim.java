package arrays.mainProjects;

public class nim {
  public int[] piles = new int[3];
  public boolean turn = false;
  public int[] scores = new int[2];

  public static void main(String[] args) {
    nim game = new nim();
    game.resetBoard();
    for (;;) {
      game.printBoard();
      if (game.promptMove()) {
        game.resetBoard();
        game.turn = false;
      }
    }
  }

  public void resetBoard() {
    this.piles[0] = 3;
    this.piles[1] = 5;
    this.piles[2] = 8;
  }

  public boolean promptMove() {
    System.out.print("Player " + (turn ? "2" : "1") + ", choose a pile: ");
    int pile = Integer.parseInt(System.console().readLine());
    // check if valid pile
    if (pile < 1 || pile > 3) {
      System.out.println("Invalid pile.");
      return this.promptMove();
    }
    System.out.println("How many to remove from pile " + pile + ": ");
    int amount = Integer.parseInt(System.console().readLine());
    // check if valid amount
    if (amount < 1 || amount > this.piles[pile - 1]) {
      System.out.println("Invalid amount.");
      return this.promptMove();
    }
    this.piles[pile - 1] -= amount;
    // if (this.check()) {

    // }
    this.printBoard();
    if (this.check()) {
      System.out.println("Player " + (turn ? "1" : "2") + " wins!");
      scores[turn ? 0 : 1]++;
      // wait for input
      System.console().readLine("\033[3;90mPress enter to continue.\033[0m");
    }
    this.turn = !this.turn;

    return this.check();
  }

  public static void clear() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public boolean check() {
    // check if all piles are empty
    for (int pile : this.piles) {
      if (pile > 0) {
        return false;
      }
    }
    return true;
  }

  public void printBoard() {
    clear();

    boolean faintSupport = true;
    boolean nerdFontSupport = true;

    System.out.println("\033[0;35m   N I M   \033[0m");

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

    // use # as a rock, space between the piles
    // bottom to top
    // System.out.println(" #");
    // System.out.println(" #");
    // System.out.println(" #");
    // System.out.println(" # #");
    // System.out.println(" # #");
    // System.out.println(" # # #");
    // System.out.println(" # # #");
    // System.out.println(" # # #");
    // System.out.println(" 1 2 3");
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 3; j++) {
        if ((8 - i) <= this.piles[j]) {
          System.out.print(" # ");
        } else {
          System.out.print("   ");
        }
      }
      System.out.println();
    }
    System.out.println(String.format(" %s  %s  %s", this.piles[0], this.piles[1], this.piles[2]));
  }
}
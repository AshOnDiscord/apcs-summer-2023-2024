package arrays.mainProjects;

public class theBigEZ {
  public static void main(String[] args) {
    long count = 0;
    for (int i = 0; i < 1000; i++) {
      int counter = 0;
      double sum = 0;
      while (sum <= 1) {
        sum += Math.random();
        counter++;
      }
      count += counter;
    }
    // System.out.println(count);
    System.out.println(count / 1000.0);
  }
}

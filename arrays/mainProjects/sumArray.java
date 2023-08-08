package arrays.mainProjects;

public class sumArray {
  public static void main(String[] args) {
    Integer[][] intValues = new Integer[5][4];
    inputLoop: for (int i = 0; i < intValues.length; i++) {
      for (int j = 0; j < intValues[i].length; j++) {
        String input = System.console().readLine("Enter a number: ");
        if (input.equals("q")) {
          break inputLoop;
        }
        intValues[i][j] = Integer.parseInt(input);
      }
    }
    calc(intValues);

    Double[][] doubleValues = new Double[5][4];
    inputLoop: for (int i = 0; i < doubleValues.length; i++) {
      for (int j = 0; j < doubleValues[i].length; j++) {
        String input = System.console().readLine("Enter a number: ");
        if (input.equals("q")) {
          break inputLoop;
        }
        doubleValues[i][j] = Double.parseDouble(input);
      }
    }
    calc(doubleValues);
  }

  public static void calc(Double[][] values) {
    Double[] subSum = new Double[values.length];
    double sum = 0.0;
    int count = 0;
    outerLoop: for (int i = 0; i < values.length; i++) {
      for (int j = 0; j < values[i].length; j++) {
        if (values[i][j] == null) {
          break outerLoop;
        }
        sum += values[i][j];
        if (subSum[i] == null) {
          subSum[i] = 0.0;
        }
        subSum[i] += values[i][j];
        count++;
      }
    }
    System.out.println("Results:");
    for (int i = 0; i < subSum.length; i++) {
      if (subSum[i] == null) {
        break;
      }
      System.out.println("Row " + (i + 1) + ": " + subSum[i]);
    }
    System.out.println("Sum: " + sum);
    System.out.println("Average: " + (sum / count));
  }

  public static void calc(Integer[][] values) {
    Double[][] converted = new Double[values.length][values[0].length];
    for (int i = 0; i < values.length; i++) {
      for (int j = 0; j < values[i].length; j++) {
        converted[i][j] = (double) values[i][j];
      }
    }
    calc(converted);
  }
}
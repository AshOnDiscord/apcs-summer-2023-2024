package arrays.mainProjects;

public class reverseAnArray {
  public static void main(String[] args) {
    int maxSize = Integer.MAX_VALUE - 8;
    int[] arr = new int[0];
    for (;;) {
      String input = prompt();
      if (input.equals("exit")) {
        break;
      }
      int num = Integer.parseInt(input);
      if (arr.length == maxSize) {
        System.out.println("Array is full.");
        continue;
      }
      int[] newArr = new int[arr.length + 1];
      for (int i = 0; i < arr.length; i++) {
        newArr[i] = arr[i];
      }
      newArr[arr.length] = num;
      arr = newArr;
    }
    System.out.println("Original array: ");
    printArray(arr);
    System.out.println("Reversed array: ");
    printArray(reverse(arr));
  }

  public static void printArray(int[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + (i == arr.length - 1 ? "" : ", "));
    }
    System.out.println("]");
  }

  public static int[] reverse(int[] arr) {
    int[] newArr = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      newArr[i] = arr[arr.length - 1 - i];
    }
    return newArr;
  }

  public static String prompt() {
    return System.console().readLine("Enter a number: ");
  }
}

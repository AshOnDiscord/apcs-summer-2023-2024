import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class ListOfNumbers {
  public static void main(String[] args) {
    boolean isInt = new Random().nextBoolean();
    if (isInt) {
      ArrayList<Integer> list = new ArrayList<Integer>();
      for (int i = 0; i < 10; i++) {
        list.add(new Random().nextInt(10));
        // list.add(new Random().nextInt());
      }
      int largest = list.get(0);
      int smallest = list.get(0);
      HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
      for (Integer e : list) {
        if (e > largest) {
          largest = e;
        }
        if (e < smallest) {
          smallest = e;
        }
        if (count.containsKey(e)) {
          count.put(e, count.get(e) + 1);
        } else {
          count.put(e, 1);
        }
        System.out.println(e);
      }
      System.out.println();
      System.out.println("Largest: " + largest);
      System.out.println("Smallest: " + smallest);
      System.out.println("Most Common: " + count.entrySet().stream()
          .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey() + " ("
          + count.entrySet().stream()
              .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getValue()
          + " times)");
    } else {
      ArrayList<Double> list = new ArrayList<Double>();
      for (int i = 0; i < 10; i++) {
        // list.add(Math.round(new Random().nextDouble() * 100) / 100.0);
        list.add(new Random().nextDouble());
      }
      double largest = list.get(0);
      double smallest = list.get(0);
      HashMap<Double, Integer> count = new HashMap<Double, Integer>();
      for (Double e : list) {
        if (e > largest) {
          largest = e;
        }
        if (e < smallest) {
          smallest = e;
        }
        if (count.containsKey(e)) {
          count.put(e, count.get(e) + 1);
        } else {
          count.put(e, 1);
        }
        System.out.println(e);
      }
      System.out.println();
      System.out.println("Largest: " + largest);
      System.out.println("Smallest: " + smallest);
      System.out.println("Most Common: " + count.entrySet().stream()
          .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey() + " ("
          + count.entrySet().stream()
              .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getValue()
          + " times)");
    }
  }
}
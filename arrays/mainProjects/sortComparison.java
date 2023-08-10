package arrays.mainProjects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class sortComparison {
  public static void main(String[] args) {
    // fill();
    try {
      int[] ints = new int[10000];
      float[] floats = new float[10000];
      double[] doubles = new double[10000];
      BufferedReader intReader = new BufferedReader(new FileReader(new File("arrays/mainProjects/ints.txt")));
      BufferedReader floatReader = new BufferedReader(new FileReader(new File("arrays/mainProjects/floats.txt")));
      BufferedReader doubleReader = new BufferedReader(new FileReader(new File("arrays/mainProjects/doubles.txt")));
      String line;
      int i = 0;
      while ((line = intReader.readLine()) != null) {
        ints[i] = Integer.parseInt(line);
        i++;
      }
      i = 0;
      while ((line = floatReader.readLine()) != null) {
        floats[i] = Float.parseFloat(line);
        i++;
      }
      i = 0;
      while ((line = doubleReader.readLine()) != null) {
        doubles[i] = Double.parseDouble(line);
        i++;
      }
      intReader.close();
      floatReader.close();
      doubleReader.close();
      long iBS = bubbleSort(ints);
      long fBS = bubbleSort(floats);
      long dBS = bubbleSort(doubles);
      long iAS = arraySort(ints);
      long fAS = arraySort(floats);
      long dAS = arraySort(doubles);
      System.out.println();

      // we really should be using nanoseconds or smt since for Arrays.sort as it uses
      // a dual-pivot quicksort which is usually O(nlogn) on average as with
      // milliseconds we'll get 0 or 1 a bunch.

      if (iBS < iAS) {
        System.out.println("Bubble sort for ints was faster by " + (iAS - iBS) + " milliseconds.");
      } else if (iBS > iAS) {
        System.out.println("Array sort for ints was faster by " + (iBS - iAS) + " milliseconds.");
      } else {
        System.out.println("Both sorts for ints took the same amount of time.");
      }
      if (fBS < fAS) {
        System.out.println("Bubble sort for floats was faster by " + (fAS - fBS) + " milliseconds.");
      } else if (fBS > fAS) {
        System.out.println("Array sort for floats was faster by " + (fBS - fAS) + " milliseconds.");
      } else {
        System.out.println("Both sorts for floats took the same amount of time.");
      }
      if (dBS < dAS) {
        System.out.println("Bubble sort for doubles was faster by " + (dAS - dBS) + " milliseconds.");
      } else if (dBS > dAS) {
        System.out.println("Array sort for doubles was faster by " + (dBS - dAS) + " milliseconds.");
      } else {
        System.out.println("Both sorts for doubles took the same amount of time.");
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static long bubbleSort(int[] numbers) {
    long start = System.currentTimeMillis();
    int temp;
    for (int i = 0; i < numbers.length; i++) {
      for (int j = 1; j < (numbers.length - i); j++) {
        if (numbers[j - 1] > numbers[j]) {
          temp = numbers[j - 1];
          numbers[j - 1] = numbers[j];
          numbers[j] = temp;
        }
      }
    }
    long end = System.currentTimeMillis();
    System.out.println("Bubble sort for ints took " + (end - start) + " milliseconds.");
    return end - start;
  }

  public static long bubbleSort(float[] numbers) {
    long start = System.currentTimeMillis();
    float temp;
    for (int i = 0; i < numbers.length; i++) {
      for (int j = 1; j < (numbers.length - i); j++) {
        if (numbers[j - 1] > numbers[j]) {
          temp = numbers[j - 1];
          numbers[j - 1] = numbers[j];
          numbers[j] = temp;
        }
      }
    }
    long end = System.currentTimeMillis();
    System.out.println("Bubble sort for floats took " + (end - start) + " milliseconds.");
    return end - start;
  }

  public static long bubbleSort(double[] numbers) {
    long start = System.currentTimeMillis();
    double temp;
    for (int i = 0; i < numbers.length; i++) {
      for (int j = 1; j < (numbers.length - i); j++) {
        if (numbers[j - 1] > numbers[j]) {
          temp = numbers[j - 1];
          numbers[j - 1] = numbers[j];
          numbers[j] = temp;
        }
      }
    }
    long end = System.currentTimeMillis();
    System.out.println("Bubble sort for doubles took " + (end - start) + " milliseconds.");
    return end - start;
  }

  public static long arraySort(int[] numbers) {
    long start = System.currentTimeMillis();
    Arrays.sort(numbers);
    long end = System.currentTimeMillis();
    System.out.println("Array sort for ints took " + (end - start) + " milliseconds.");
    return end - start;
  }

  public static long arraySort(float[] numbers) {
    long start = System.currentTimeMillis();
    Arrays.sort(numbers);
    long end = System.currentTimeMillis();
    System.out.println("Array sort for floats took " + (end - start) + " milliseconds.");
    return end - start;
  }

  public static long arraySort(double[] numbers) {
    long start = System.currentTimeMillis();
    Arrays.sort(numbers);
    long end = System.currentTimeMillis();
    System.out.println("Array sort for doubles took " + (end - start) + " milliseconds.");
    return end - start;
  }

  public static void fill() {
    try {
      FileWriter ints = new FileWriter("arrays/mainProjects/ints.txt");
      FileWriter floats = new FileWriter("arrays/mainProjects/floats.txt");
      FileWriter doubles = new FileWriter("arrays/mainProjects/doubles.txt");
      BufferedWriter intWriter = new BufferedWriter(ints);
      BufferedWriter floatWriter = new BufferedWriter(floats);
      BufferedWriter doubleWriter = new BufferedWriter(doubles);

      for (int i = 0; i < 10000; i++) {
        intWriter.write((int) (((Math.random() - 0.5) * 2) * Integer.MAX_VALUE) + "\n");
        floatWriter.write((float) (((Math.random() - 0.5) * 2) * Float.MAX_VALUE) + "\n");
        doubleWriter.write((double) (((Math.random() - 0.5) * 2) * Double.MAX_VALUE) + "\n");
      }
      intWriter.close();
      floatWriter.close();
      doubleWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error");
    }
  }
}

package arrays.mainProjects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class studentGrades {
  public static void main(String[] args) {
    File file = new File("arrays/mainProjects/grades.txt");
    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader(file));
      String line;

      Student[] students = new Student[25];
      for (int i = 0; i < students.length; i++) {
        if ((line = br.readLine()) == null) {
          break;
        }
        String[] split = line.split(" ");
        String name = split[0];
        double[] grades = new double[5];
        for (int j = 0; j < grades.length; j++) {
          grades[j] = Double.parseDouble(split[j + 1]);
        }
        students[i] = new Student(name, grades);
      }
      br.close();
      System.out.println("Loaded");
      for (Student student : students) {
        if (student == null) {
          break;
        }
        System.out.println(student);
      }
      // sort
      // Bubble sort
      System.out.println("\nBubble sort");

      Student[] temp = new Student[students.length];
      for (int i = 0; i < students.length; i++) {
        temp[i] = students[i];
      }

      for (int i = students.length; i > 0; i--) {
        for (int j = 0; j < i - 1; j++) {
          if (students[j] == null || students[j + 1] == null) {
            break;
          }
          if (students[j + 1].average > students[j].average) {
            Student tempStudent = students[j];
            students[j] = students[j + 1];
            students[j + 1] = tempStudent;
          }
        }
      }

      for (Student student : students) {
        if (student == null) {
          break;
        }
        System.out.println(student);
      }

      // Arrays.sort
      Arrays.sort(students, Comparator.nullsLast(Comparator.naturalOrder()));

      System.out.println("\nArrays.sort");

      for (Student student : students) {
        if (student == null) {
          break;
        }
        System.out.println(student);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.err.println("File not found");
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Error reading file");
    }
  }
}

class Student implements Comparable<Student> {
  public String name;
  public double[] grades;
  public double average;

  public Student(String name, double[] grades) {
    this.name = name;
    this.grades = grades;
    this.average = calcAverage();
  }

  public double calcAverage() {
    double sum = 0.0;
    for (int i = 0; i < grades.length; i++) {
      sum += grades[i];
    }
    return sum / grades.length;
  }

  @Override
  public int compareTo(Student o) {
    return (int) (o.average - this.average);
  }

  public String toString() {
    return name + ": " + average + " (" + grades[0] + ", " + grades[1] + ", " + grades[2] + ", " + grades[3] + ", "
        + grades[4] + ")";
  }
}
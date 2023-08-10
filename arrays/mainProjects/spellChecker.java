package arrays.mainProjects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class spellChecker {
  public static String[] words = new String[20];

  public static void main(String[] args) {
    // load words.txt
    try {
      File file = new File("arrays/mainProjects/words.txt");
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;

      int i = 0;
      while ((line = br.readLine()) != null) {
        words[i] = line.toLowerCase();
        i++;
      }

      for (;;) {
        String input = prompt();
        if (input.equals("exit")) {
          break;
        }
        int[][] errors = calculateErrors(input);

        int[] values = weighting(errors);

        // print the one with the largest value
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int j = 0; j < values.length; j++) {
          if (values[j] > max) {
            max = values[j];
            maxIndex = j;
          }
        }

        if (max <= 0) {
          System.out.println("No suggestions.");
        } else {
          System.out.println(words[maxIndex]);
          // check if they want another
          for (;;) {
            String response = System.console().readLine("Would you like another suggestion? (y/n) ");
            if (response.equals("n")) {
              break;
            }
            // keep removing the previous suggestion and printing a new one until they say
            // no or we run out of suggestions
            // set to INTEGER.MIN_VALUE since its an array and not an arraylist
            values[maxIndex] = Integer.MIN_VALUE;
            max = Integer.MIN_VALUE;
            maxIndex = 0;
            for (int j = 0; j < values.length; j++) {
              if (values[j] > max) {
                max = values[j];
                maxIndex = j;
              }
            }
            if (max <= 0) {
              System.out.println("No more suggestions.");
              break;
            }
            System.out.println(words[maxIndex]);
          }

        }
      }

      br.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static String prompt() {
    System.out.print("Enter a word: ");
    return System.console().readLine().trim().toLowerCase();
  }

  public static int[] weighting(int[][] errors) {
    // error type 3 is not important
    // error type 4 is the most important
    // error type 2 is the second most important
    // error type 1 is the least important

    // error type 4 is worth 4 points
    // error type 2 is worth 3 points
    // error type 1 is worth 2 point
    // error type 3 is worth 1 points
    // loop through the errors array
    int[] values = new int[20];
    for (int i = 0; i < 20; i++) {
      int four = errors[i][3] * 4;
      int two = errors[i][2] * 3;
      int one = errors[i][1] * 2;
      int three = errors[i][0];
      values[i] = four + two + one + three;
    }
    return values;
  }

  public static int[][] calculateErrors(String input) {

    // // if exact match, return 1 for all but the correct
    // for (int i = 0; i < words.length; i++) {
    // if (input.equals(words[i])) {
    // int[][] errors = new int[20][4];
    // for (int j = 0; j < errors.length; j++) {
    // for (int k = 0; k < errors[j].length; k++) {
    // errors[j][k] = Integer.MIN_VALUE / 4;
    // }
    // }
    // // set the correct word to 0
    // for (int j = 0; j < errors[i].length; j++) {
    // errors[i][j] = Integer.MAX_VALUE / 4;
    // }
    // return errors;
    // }
    // }

    // there are 4 possible errors:
    // 1. extra letter
    // 2. missing letter
    // 3. wrong letter
    // 4. swapped letters(adjacent)

    int[][] errors = new int[20][4];

    // generate all possible one letter edits
    // for each one, check against the dictionary
    // if it is, increment the error count for that type of error for that word
    // return the errors array

    // extra letter
    // for each letter in the input
    // remove that letter from the input
    for (int i = 0; i < input.length(); i++) {
      String word = input.substring(0, i) + input.substring(i + 1);
      for (int j = 0; j < words.length; j++) {
        if (word.equals(words[j])) {
          errors[j][0]++;
        }
      }
    }

    // missing letter
    // for each letter in the input
    // add each letter of the alphabet to the input

    // try before
    for (int i = 0; i < 26; i++) {
      String word = (char) (i + 97) + input;
      for (int j = 0; j < 20; j++) {
        if (word.equals(words[j])) {
          errors[j][1]++;
        }
      }
    }

    // try after each letter
    for (int i = 0; i < input.length(); i++) {
      for (int j = 0; j < 26; j++) {
        // for example:
        String word;
        if (i == input.length()) {
          word = input + (char) (j + 97);
        } else {
          word = input.substring(0, i + 1) + (char) (j + 97) + input.substring(i + 1);
        }
        for (int k = 0; k < words.length; k++) {
          if (word.equals(words[k])) {
            errors[k][1]++;
          }
        }
      }
    }

    // wrong letter
    // for each letter in the input
    // replace that letter with each letter of the alphabet
    for (int i = 0; i < input.length(); i++) {
      for (int j = 0; j < 26; j++) {
        String word = input.substring(0, i) + (char) (j + 97) + input.substring(i + 1);
        for (int k = 0; k < words.length; k++) {
          if (word.equals(words[k])) {
            errors[k][2]++;
          }
        }
      }
    }

    // swapped letters
    // for each letter in the input
    // swap that letter with the next letter
    for (int i = 0; i < input.length() - 1; i++) {
      String word = input.substring(0, i) + input.charAt(i + 1) + input.charAt(i) + input.substring(i + 2);
      for (int j = 0; j < words.length; j++) {
        if (word.equals(words[j])) {
          errors[j][3]++;
        }
      }
    }

    return errors;

    // return new int[0][0];
  }
}

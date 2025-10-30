import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner Input = new Scanner(System.in);
        String[] words = { "elephant", "java", "hangman", "notebook", "sunshine","banana", "computer",};
        Random rand = new Random();
        String word = words[rand.nextInt(words.length)];

        System.out.println("A random word has been selected!");

        boolean wordGuessed = false;
        int correctGuessesCount = word.length();
        boolean correctLetter = false;
        boolean[] revealed = new boolean[word.length()];
        int lives = 6;
        boolean invalidInput = true;


        while (lives > 0 && !wordGuessed) {
            correctLetter = false;

            System.out.println("\n-----------------------------------");
            System.out.println("Lives left: " + lives);
            System.out.println("Word length is: " + word.length());
            System.out.println("Word so far: " + render(word, revealed));

            try {
                System.out.print("Enter your guess: ");
                String input = Input.next();
                if (input.length() != 1) {
                    throw new IllegalArgumentException("Please enter only one letter");
                }
                char guess = input.charAt(0);
                if (!Character.isLetter(guess) ) {
                    throw new IllegalArgumentException("Only use letters! Nothing else is allowed.");
                }

                System.out.print("Enter your guess: ");

                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess && !revealed[i]) {
                        revealed[i] = true;
                        correctLetter = true;
                        System.out.println("Guess correct!");
                        correctGuessesCount--;
                    }
                }

                if (correctGuessesCount == 0) {
                    wordGuessed = true;
                    System.out.println("\n YOU GUESSED THE WORD! It was: " + word);
                    break;
                }

                if (!correctLetter) {
                    System.out.println("Guess wrong!");
                    lives--;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again");
                Input.nextLine();
            }
        }

        if (!wordGuessed) {
            System.out.println("\n GAME OVER! The word was: " + word);
        }
    }

    private static String render(String word, boolean[] revealed) {
        String result = "";

        for (int i = 0; i < word.length(); i++) {
            if (revealed[i]) {
                result = result + word.charAt(i);   // show the letter/ adds letter
            } else {
                result = result + "_";              // hide with underscore/ adds _
            }

            if (i < word.length() - 1) {
                result = result + " ";              // add a space between letters; not last letter yet, add a space
            }
        }

        return result;
    }

}

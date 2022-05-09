// Multiplication.java
// Program that helps primary grade students learn multiplication.
import java.security.SecureRandom;
import java.util.Scanner;

public class Multiplication
{
    // create a Scanner to obtain input from the command window
    private static Scanner input = new Scanner(System.in);
    // create a secure random number generator
    private static final SecureRandom randomNumbers = new SecureRandom();
    
    static int number1, // first number to multiply
               number2, // first number to multiply
               product; // product of number1 and number2
    

    public static void main(String[] args)
    {
        System.out.printf("\t\t%s%n%s%n%s%n%s%n  %s%n  %s%n%n",
            "Easy Multiplication",
            "-------------------------------------------------------",
            "Enter your answer to the question at the prompt.",
            "Type the end-of-file indicator to terminate the puzzle:",
            "On UNIX/Linux/Mac OS X type <Ctrl> d then press Enter",
            "On Windows type <Ctrl> z then press Enter");

        // generate question and prompt for input
        generateQuestion();
        int answer = 0;

        // loop until the end-of-file indicator is entered
        while (input.hasNext())
        {
            // read and check learner's answer
            answer = input.nextInt();
            if (answer == product)
            {
                // if answer is correct, generate another question
                System.out.println("Very good!");
                generateQuestion();           
            }
            else
                // if answer is incorrect, learner attempts same question
                System.out.print("No. Please try again: ");
        } // end while
    } // end main

    // method that generates a new question
    private static void generateQuestion()
    {
        // pick random positive one-digit integers
        number1 = 1 + randomNumbers.nextInt(9);
        number2 = 1 + randomNumbers.nextInt(9);
        // multiply the two integers then store result in product
        product = number1 * number2;

        // prompt learner with a multiplication question
        System.out.printf("How much is %d times %d? ", number1, number2);
    }
} // end class Multiplication
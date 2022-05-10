// Multiplication.java
// Program that helps primary grade students learn multiplication.
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.InputMismatchException;

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
            try
            {
                answer = input.nextInt();
                if (answer == product)
                {
                    // if answer is correct, 
                    // vary responses and generate another question
                    switch (1 + randomNumbers.nextInt(4))
                    {
                        case 1:
                            System.out.println("Very good!");
                            break;
                        case 2:
                            System.out.println("Excellent!");
                            break;
                        case 3:
                            System.out.println("Nice work!");
                            break;
                        case 4:
                            System.out.println("Keep up the good work!");
                            break;
                    }
                    System.out.println(); // skip a line                
                    generateQuestion();           
                }
                else // if answer is incorrect,
                {
                    // vary prompts asking the learner to attempt same question
                    switch (1 + randomNumbers.nextInt(4))
                    {
                        case 1:
                            System.out.print("No. Please try again: ");
                            break;
                        case 2:
                            System.out.print("Wrong. Try once more: ");
                            break;
                        case 3:
                            System.out.print("Don't give up! Please try again: ");
                            break;
                        case 4:
                            System.out.print("No. Give it another try: ");
                            break;
                    }
                }
            } // end try block
            catch (InputMismatchException inputMismatchException)
            {
                System.err.printf("%nException: %s%n", inputMismatchException);
                // discard input so user can try again
                input.nextLine();
                System.out.print("You must enter integers. Please try again: ");
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            } // end catch blocks
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
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
    // count number of correct and incorrect responses
    static int responsesCounter = 0,
               correctResponses = 0,
               incorrectResponses = 0;
    // store difficulty level
    static int difficultyLevel = 0;
    static String studentName; // learner's name

    public static void main(String[] args)
    {
        System.out.printf("Please enter your name: ");
        studentName = input.nextLine();
        chooseDifficultyLevel();

        System.out.printf("\t\t\t%s%n%s%n%s%n%s%n  %s%n  %s%n%n",
            "Easy Multiplication",
            "----------------------------------------------------------------",
            "Type your answer to the question at the prompt then press Enter",
            "Type the end-of-file indicator to terminate the puzzle:",
            "On UNIX/Linux/Mac OS X type <Ctrl> d then press Enter",
            "On Windows type <Ctrl> z then press Enter");

        // generate question and prompt for input
        generateQuestion(difficultyLevel);
        int answer = 0;

        // loop until the end-of-file indicator is entered
        while (input.hasNext())
        {
            // read and check learner's answer
            try
            {
                answer = input.nextInt();
                ++responsesCounter;

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
                    ++correctResponses; // increment number of correct responses
                    System.out.println(); // skip a line
                    if (responsesCounter == 10)
                    {
                        calculatePerformance();
                        break;
                    }
                    else
                        generateQuestion(difficultyLevel);           
                }
                else // if answer is incorrect,
                {
                    // increment number of incorrect responses
                    ++incorrectResponses;
                    if (responsesCounter == 10)
                    {
                        System.out.printf("%s%n", "Don't give up!");
                        calculatePerformance();
                        break;
                    }
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

    private static void chooseDifficultyLevel()
    {
        System.out.printf("%s%n%s%n%s%n", "Choose difficulty level:",
            "1 - Level 1", "2 - Level 2");
        while (difficultyLevel == 0)
        {
            try
            {
                int level = input.nextInt();
                if (level == 1 || level == 2)
                    difficultyLevel = level;
                else
                    System.out.println(
                      "Invalid input. Enter 1 for Level 1 or 2 for Level 2:");
            }
            catch (InputMismatchException inputMismatchException)
            {
                System.err.printf("Exception: %s%n", inputMismatchException);
                
                input.nextLine(); // discard input so user can try again
                System.out.println(
                    "You must enter an integer. Please try again: ");
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
                System.exit(1);
            }
        }
    } // end method chooseDifficultyLevel

    // method that generates a new question
    private static void generateQuestion(int difficultLevel)
    {
        switch (difficultLevel)
        {
            // difficult level 1: use one-digit random positive integers
            case 1:
                number1 = 1 + randomNumbers.nextInt(9);
                number2 = 1 + randomNumbers.nextInt(9);
                break;
            // difficult level 2: random positive ints as large as two digits
            case 2:
                number1 = 3 + randomNumbers.nextInt(10);
                number2 = 3 + randomNumbers.nextInt(10);
                break;
        }
        // multiply the two integers then store result in product
        product = number1 * number2;

        // prompt learner with a multiplication question
        System.out.printf("How much is %d times %d? ", number1, number2);
    } // end method generateQuestion

    // after 10 answers, calculate the percentage of correct responses
    private static void calculatePerformance()
    {
        System.out.printf("%n%s%n%s%n",
            "Your Performance", "----------------");
        System.out.printf("Correct answers: %d%n", correctResponses);
        System.out.printf("Incorrect answers: %d%n", incorrectResponses);

        double performance = (double) correctResponses / responsesCounter * 100;
        System.out.printf("Percentage of correct answers: %.2f%s%n",
            performance, "%");
        
        System.out.println();
        if (performance < 75)
            System.out.printf("%s%n%s%n", "Nice try, " + studentName + "!",
                "Please ask your teacher for extra help.");
        else
            System.out.printf("%s%n%s%n",
                "Congratulations, " + studentName + "!",
                "You are ready to go to Level " + (++difficultyLevel) + ".");
    } // end method calculatePerformance
} // end class Multiplication
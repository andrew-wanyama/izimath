// IziMathApplication.java
// The program generates Math quizzes based on user's skill level,
// and selected arithmetic operation.
import java.util.Scanner;
import java.util.InputMismatchException;
import java.security.SecureRandom;

public class IziMathApplication
{
    // create a Scanner to obtain input from the command window
    private static Scanner input = new Scanner(System.in);
    // create a secure random number generator
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static EquationGenerator equationGenerator;

    private static int difficultyLevel = 0; // skill level
    // count number of correct and incorrect responses
    private static int responsesCounter = 0,
               correctResponses = 0,
               incorrectResponses = 0;
    private static String studentName; // learner's name

    public static void main(String[] args)
    {
        System.out.printf("Please enter your name: ");
        studentName = input.nextLine();
        // choose skill level
        chooseDifficultyLevel();
        // TODO choose type of operation

        System.out.printf("\t\t\t%s%n%s%n%s%n%s%n  %s%n  %s%n%n",
            "Easy " + Operator.ADDITION.name(),
            "----------------------------------------------------------------",
            "Type your answer to the question at the prompt then press Enter",
            "Type the end-of-file indicator to terminate the Quiz:",
            "On UNIX/Linux/Mac OS X type <Ctrl> d then press Enter",
            "On Windows type <Ctrl> z then press Enter");

        // construct an equation in selected skill level and operation type
        equationGenerator = new EquationGenerator(difficultyLevel, Operator.ADDITION);
        generateQuestion();
        int answer = 0, userAnswer = 0;
        
        // loop until the end-of-file indicator is entered
        while (input.hasNext())
        {
            // obtain and store the correct answer
            answer = equationGenerator.checkAnswer();
            try
            {
                // read and check user's answer
                userAnswer = input.nextInt();
                answer = equationGenerator.checkAnswer();
                ++responsesCounter;

                if (userAnswer == answer)
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
                    }
                    // increment number of correct responses
                    ++correctResponses;
                    System.out.println(); // skip a line
                    if (responsesCounter == 10)
                    {
                        calculatePerformance();
                        break;
                    }
                    else
                        generateQuestion();           
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
        System.out.printf("%s%n%s%n", "Choose difficulty level:",
            "(Option 1 for Level 1, 2 for Level 2, 3 for Level 3, and so on)");
        while (difficultyLevel == 0)
        {
            try
            {
                int level = input.nextInt();
                if (level >= 1)
                    difficultyLevel = level;
                else
                    System.out.println(
                      "Invalid (Enter 1 for Level 1, 2 for Level 2, and so on):");
            }
            catch (InputMismatchException inputMismatchException)
            {
                System.err.printf("Exception: %s%n", inputMismatchException);
                
                input.nextLine(); // discard input so user can try again
                System.out.println("You must enter an integer. Please try again:");
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
                System.exit(1);
            }
        }
    } // end method chooseDifficultyLevel

    private static void generateQuestion()
    {
        equationGenerator = // construct a new equation
            new EquationGenerator(difficultyLevel, Operator.ADDITION);
        // prompt learner with selected arithmetic question
        System.out.printf("How much is %s? ",
            equationGenerator.generateQuestion());
    }

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
} // end class IziMathApplication
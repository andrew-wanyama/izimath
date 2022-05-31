// EquationGenerator.java
// This class generates an equation using random numbers
// in the selected difficulty level and using specified operation type.
import java.security.SecureRandom;

public class EquationGenerator
{
    // create a secure random number generator
    private static final SecureRandom randomNumbers = new SecureRandom();

    int number1, number2; // numbers on the left-hand side of the equation
    Equation equation; // reference to the left-hand side of the equation

    // no-argument constructor with default values
    public EquationGenerator()
    {
        this(1, Operator.ADDITION);
    }
    
    // main constructor that accepts difficulty level and operation type
    public EquationGenerator(int level, Operator operation)
    {
        // create random numbers based on difficulty level
        number1 = level + (level + 1) * randomNumbers.nextInt(5);
        number2 = (level + 1) + randomNumbers.nextInt(8);
        // set up a left-hand side of the equation
        equation = new Equation(number1, number2, operation);        
    }

    // perform operation and return left-hand side of the equation
    public String generateQuestion()
    {
        equation.performOperation();
        return equation.getLeftHandSide();
    }

    // return right-hand side of the equation as the correct answer
    public int checkAnswer()
    {
        return equation.getRightHandSide();
    }
} // end class EquationGenerator
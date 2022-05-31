// Equation.java
// This class contains information about an equation.
public class Equation
{
    // attributes of an equation
    private int leftOperand;
    private int rightOperand;
    private int result;
    private Operator operationType;

    // no-argument constructor with default values
    public Equation()
    {
        this(0, 0, Operator.ADDITION);
    }

    // main constructor that receives two operands and operation type
    public Equation(int leftOperand, int rightOperand, Operator operationType)
    {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.operationType = operationType;        
    }

    public void performOperation()
    {
        switch(operationType)
        {
            case ADDITION:
                result = leftOperand + rightOperand;
                break;
            case SUBTRACTION:
                result = leftOperand - rightOperand;
                break;
            case MULTIPLICATION:
                result = leftOperand * rightOperand;
                break;
        }
    }

    // set and get methods to access Equation class's private attributes
    public int getLeftOperand()
    {
        return leftOperand;
    }

    public void setLeftOperand(int leftOperand)
    {
        this.leftOperand = leftOperand;
    }

    public int getRightOperand()
    {
        return rightOperand;
    }

    public void setRightOperand(int rightOperand)
    {
        this.rightOperand = rightOperand;
    }

    public int getResult()
    {
        return result;
    }

    public void setResult(int result)
    {
        this.result = result;
    }

    public Operator getOperationType()
    {
        return operationType;
    }

    public void setOperationType(Operator operationType)
    {
        this.operationType = operationType;
    }

    // returns the left hand side of the equation as a String
    public String getLeftHandSide()
    {
        return leftOperand + " " + getOperationType().getSymbol() +
          " " + rightOperand;
    }

    // returns the right hand side of the equation as a String
    public int getRightHandSide()
    {
        return getResult();
    }

    // returns a String representation of an Equation
    @Override
    public String toString()
    {
        return getLeftHandSide() + " = " + getRightHandSide();
    }
} // end class Equation
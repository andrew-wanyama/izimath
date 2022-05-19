// Operator.java
// This enum class represents tokens used in arithmetic operations.

public enum Operator
{
    // constants of enum type
    ADDITION("add", "+"),
    SUBTRACTION("subtract", "-"),
    MULTIPLICATION("multiply", "*");

    // instance fields
    private final String action;
    private final String symbol;

    // enum constructor
    Operator(String action, String symbol)
    {
        this.action = action;
        this.symbol = symbol;
    }

    // accessor for operator action and symbol
    public String getAction()
    {
        return action;
    }

    public String getSymbol()
    {
        return symbol;
    }       
} // end enum Operator
/**
 * Created by Pierre on 06/11/2014.
 */
import java.util.Scanner;

public class FractionCalculator {

    private Fraction calculatorValue;
    private Operation calculatorMemory;
    private boolean errorState = false;
    private boolean finishedState = false;

    /* Error management:
        - errorState flag raised when inappropriate input. Triggers calculator 'reset' & "Error" message
        - finishedState flag raised when user inputs a "quit" or equivalent command. Triggers calculator exit but no "Goodbye" message
        - if the end of input exception is identified with no input, it triggers a "Goodbye" and the calculator exit
        - program does not handle 'CTRL C' input, as assumption is that exception handling would then be required
    */

    // Getter(s) & Setters() for all private attributes of the Class

    public FractionCalculator(Fraction fraction, Operation memory) {
        this.calculatorValue = fraction;
        this.calculatorMemory = memory;
    }

    public Fraction getCalculatorValue() {
        return(calculatorValue);
    }

    public void setCalculatorValue(Fraction fraction) {
        calculatorValue = fraction;
    }

    public Operation getCalculatorMemory() {
        return(calculatorMemory);
    }

    public void setCalculatorMemory(Operation operation) {
        calculatorMemory = operation;
    }

    public boolean getErrorState() {
        return(errorState);
    }

    public void setErrorState(boolean state) {
        errorState = state;
    }

    public boolean getFinishedState() {
        return(finishedState);
    }

    public void setFinishedState(boolean state) {
        finishedState = state;
    }

    public static void main(String[] args) {
        FractionCalculator fc = new FractionCalculator(new Fraction(0,1),Operation.NIL);
        fc.run();
    }

    /*
    'run' method
    Description:
        - Gets line of input from user
        - Passes on the user line of input to the 'evaluate' method for evaluation and calculation
        - Triggers appropriate calculator behavior when an error state is raised.
     */

    public void run() {
        System.out.println("Welcome Pierre Meyer!");
        do {
            Scanner scanner = new Scanner(System.in);
            String commandLine = scanner.nextLine();
            if (commandLine.equals("")) {
                System.out.println("Goodbye");
                setFinishedState(true);
            } else {
                setCalculatorValue(evaluate(getCalculatorValue(), commandLine));
            }
            if (getErrorState()) {
                System.out.println("Error");
                reset();
            }
            if (!getFinishedState())
                System.out.println(calculatorValue.toString());
        } while (!getFinishedState());
    }

    /*
    'evaluate' method
    Description:
        - splits user line of input using the space delimiter
        - validates each substring
        - calculates the resulting fraction after each substring validation
        - return the final fraction at the end of the user line input
        - raise errorState flag if inappropriate input identified
        - raise finishedState flag if a user command to quit is found
    Returns:
        - result of the calculation as a fraction
     */

    public Fraction evaluate(Fraction fraction, String inputString) {

        Fraction newFraction = new Fraction(0,1);
        Fraction outputFraction = fraction;

        String[] commandItem = inputString.split("\\s");

        for (int i=0; i<commandItem.length && !getErrorState() && !getFinishedState(); i++) {
            Operation input = Operation.NIL;
            Fraction inputFraction = outputFraction;
            Boolean errorInput = true;

            /*
            Sequence of tests to validate and recognise user input
            If no test is valid, errorInput remains true, and the calculator error state is raised.
            */

            // commands such as "q" or "quit" or "exit" (case ignored) trigger the flag finishedState
            if ((commandItem[i].equalsIgnoreCase("q")) || (commandItem[i].equalsIgnoreCase("quit")) || (commandItem[i].equalsIgnoreCase("exit"))) {
                errorInput = false;
                setFinishedState(true);
            }

            // operator '*' is stored in the calculator memory, if empty. if not empty, it triggers the flag errorState
            if (commandItem[i].equals("*")) {
                errorInput = false;
                if (getCalculatorMemory() == Operation.NIL) {
                    setCalculatorMemory(Operation.MULTIPLY);
                } else {
                    System.out.println("Error: operator already provided");
                    setErrorState(true);
                }
            }

            // operator '+' is stored in the calculator memory, if empty. if not empty, it triggers the flag errorState
            if (commandItem[i].equals("+")) {
                errorInput = false;
                if (getCalculatorMemory() == Operation.NIL) {
                    setCalculatorMemory(Operation.ADD);
                } else {
                    System.out.println("Error: operator already provided");
                    setErrorState(true);
                }
            }

            // operator '-' is stored in the calculator memory, if empty. if not empty, it triggers the flag errorState
            if (commandItem[i].equals("-")) {
                errorInput = false;
                if (getCalculatorMemory() == Operation.NIL) {
                    setCalculatorMemory(Operation.SUBTRACT);
                } else {
                    System.out.println("Error: operator already provided");
                    setErrorState(true);
                }
            }

            // operator '/' is stored in the calculator memory, if empty. if not empty, it triggers the flag errorState
            if (commandItem[i].equals("/")) {
                errorInput = false;
                if (getCalculatorMemory() == Operation.NIL) {
                    setCalculatorMemory(Operation.DIVIDE);
                } else {
                    System.out.println("Error: operator already provided");
                    setErrorState(true);
                }
            }

            // checking if string can be parsed into an integer. If so, converts to a fraction. Setting operator as required based on memory state.
            if (isInteger(commandItem[i])) {
                errorInput = false;
                newFraction = new Fraction(parseInteger(commandItem[i]), 1);
                if (getCalculatorMemory() == Operation.NIL) {
                    input = Operation.STORE_VALUE;
                } else {
                    input = getCalculatorMemory();
                    setCalculatorMemory(Operation.NIL);
                }
            }

            // checking if string can be parsed into a fraction. If so, converts to a fraction. Setting operator as required based on memory state.
            if (isFraction(commandItem[i])) {
                errorInput = false;
                newFraction = parseFraction(commandItem[i]);
                if (getCalculatorMemory() == Operation.NIL) {
                    input = Operation.STORE_VALUE;
                } else {
                    input = getCalculatorMemory();
                    setCalculatorMemory(Operation.NIL);
                }
            }

            // checking and setting operator as required, if need to negate the last fraction in memory
            if (commandItem[i].equalsIgnoreCase("n") || commandItem[i].equalsIgnoreCase("neg")) {
                errorInput = false;
                input = Operation.NEGATE;
            }

            // checking and setting operator as required, if need to calculate the absolute value of the last fraction in memory
            if (commandItem[i].equalsIgnoreCase("a") || commandItem[i].equalsIgnoreCase("abs")) {
                errorInput = false;
                input = Operation.ABSOLUTE;
            }

            // checking and setting operator as required, if need to clear the value held by the calculator to zero
            if (commandItem[i].equalsIgnoreCase("c") || commandItem[i].equalsIgnoreCase("clear")) {
                errorInput = false;
                input = Operation.CLEAR_VALUE;
            }

            // if it was not possible to process/recognise the content of a substring, it triggers the flag errorState
            if (errorInput) {
                setErrorState(true);
            }

            // Calculates a new value as a fraction, based on the previous value in memory and the memorised operator

            switch (input) {
                case ADD:
                    outputFraction = inputFraction.add(newFraction);
                    break;
                case SUBTRACT:
                    outputFraction = inputFraction.subtract(newFraction);
                    break;
                case MULTIPLY:
                    outputFraction = inputFraction.multiply(newFraction);
                    break;
                case DIVIDE:
                    outputFraction = inputFraction.divide(newFraction);
                    break;
                case ABSOLUTE:
                    outputFraction = inputFraction.absValue();
                    break;
                case NEGATE:
                    outputFraction = inputFraction.negate();
                    break;
                case STORE_VALUE:
                    outputFraction = newFraction;
                    break;
                case CLEAR_VALUE:
                    outputFraction = new Fraction(0, 1);
                    break;
                default:
                    outputFraction = inputFraction;
                    break;
            }
        }
        setCalculatorMemory(Operation.NIL); // End of entry line - remembered operation reset
        return(outputFraction);
    }

    /*
    'reset' method
    Description:
        - resets all calculator object attributes to their initial state
    Returns:
        - nothing
     */

    private void reset() {
        setCalculatorValue(new Fraction(0,1));
        setCalculatorMemory(Operation.NIL);
        setErrorState(false);
        setFinishedState(false);
    }

    /*
    'isInteger' method
    Description:
        - check the format of the string to assess if it can be converted into an integer
        - an integer is recognised by identifying one set of numerical digits only, not followed by any characters, but with a possible sign prefix.
        - a sign prefix would be one digit long and either include one '+' or one '-' character.
    Returns:
        - 'true' if the string can be parsed into an integer
        - 'false' if the string cannot be parsed into an integer
     */

    private boolean isInteger(String input) {
        boolean isInteger = false;
        if (input.matches("[-,+]?\\d+"))
            isInteger = true;
        return(isInteger);
    }

     /*
    'isFraction' method
    Description:
        - check the format of the string to assess if it can be converted into a fraction
        - a fraction is recognised by identifying two sets of numerical digits only, separated by a '/' sign, with no space in-between
        - both sets of numerical digits can have a sign prefix (one digit long): either '+' or '-' are accepted.
    Returns:
        - 'true' if the string can be parsed into a fraction
        - 'false' if the string cannot be parsed into a fraction
     */

    private boolean isFraction(String input) {
        boolean isFraction = false;
        if (input.matches("[-,+]?\\d+[/][-,+]?\\d+")) {
            isFraction = true;
        }
        return(isFraction);
    }

     /*
    'parseInteger' method
    Description:
        - converts a string of the appropriate format into an integer
        - recommended to check the string structure with the 'isInteger' method before attempting parsing
    Returns:
        - resulting integer
    */

    private int parseInteger(String input) {
        return(Integer.parseInt(input));
    }

     /*
    'parseFraction' method
    Description:
        - converts a string of the appropriate format into a fraction
        - recommended to check the string structure with the 'isFraction' method before attempting parsing
    Returns:
        - resulting fraction
    */

    private Fraction parseFraction(String input) {
        String[] fractionItem = input.split("/");
        int numerator = Integer.parseInt(fractionItem[0]);
        int denominator = Integer.parseInt(fractionItem[1]);
        return(new Fraction(numerator, denominator));
    }
}

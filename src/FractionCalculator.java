/**
 * Created by Pierre on 06/11/2014.
 */
import java.util.Scanner;

public class FractionCalculator {

    private Fraction calculatorValue;
    private Operation calculatorMemory;
    private boolean errorState = false;         // Raised for general exceptions not requiring app. exit - Spec. page 2
    private boolean fatalErrorState = false;    // Raised when unexpected input requiring app. exit - Spec. page 1

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

    public boolean getFatalErrorState() {
        return(fatalErrorState);
    }

    public void setFatalErrorState(boolean state) {
        fatalErrorState = state;
    }


    public static void main(String[] args) {
        FractionCalculator fc = new FractionCalculator(new Fraction(0,1),Operation.NIL);
        fc.run();
    }

    public void run() {
        boolean finished = false;
        System.out.println("Welcome Pierre Meyer!");
        do {
            Scanner scanner = new Scanner(System.in);
            String commandLine = scanner.nextLine();
            String[] commandItem = commandLine.split("\\s");
            for (int i=0; i<commandItem.length && !getErrorState(); i++) {
                if ((commandItem[i].equalsIgnoreCase("q")) || (commandItem[i].equalsIgnoreCase("quit"))) {
                    finished = true;
                    break;
                }
                setCalculatorValue(evaluate(getCalculatorValue(), commandItem[i]));
            }
            if (getErrorState()) {
                if (getFatalErrorState()) {
                    System.out.println("Error: incorrect entry");
                    finished = true;
                }
                reset();
            }
            System.out.println(calculatorValue.toString());
        } while (!finished);
        System.out.println("Goodbye");
    }

    public Fraction evaluate(Fraction fraction, String inputString) {
        Operation input = Operation.NIL;
        Fraction newFraction = new Fraction(0,1);
        Fraction outputFraction;
        Boolean errorInput = true;

        if (inputString.equals("*")) {
            errorInput = false;
            if (getCalculatorMemory() == Operation.NIL) {
                setCalculatorMemory(Operation.MULTIPLY);
            } else {
                System.out.println("Error: operator already provided");
                setErrorState(true);
            }
        }

        if (inputString.equals("+")) {
            errorInput = false;
            if (getCalculatorMemory() == Operation.NIL) {
                setCalculatorMemory(Operation.ADD);
            } else {
                System.out.println("Error: operator already provided");
                setErrorState(true);
            }
        }

        if (inputString.equals("-")) {
            errorInput = false;
            if (getCalculatorMemory() == Operation.NIL) {
                setCalculatorMemory(Operation.SUBTRACT);
            } else {
                System.out.println("Error: operator already provided");
                setErrorState(true);
            }
        }

        if (inputString.equals("/")) {
            errorInput = false;
            if (getCalculatorMemory() == Operation.NIL) {
                setCalculatorMemory(Operation.DIVIDE);
            } else {
                System.out.println("Error: operator already provided");
                setErrorState(true);
            }
        }

        if (isInteger(inputString)) {
            errorInput = false;
            newFraction = new Fraction(parseInteger(inputString),1);
            if (getCalculatorMemory() == Operation.NIL) {
                input = Operation.STORE_VALUE;
            } else {
                input = getCalculatorMemory();
                setCalculatorMemory(Operation.NIL);
            }
        }

        if (isFraction(inputString)) {
            errorInput = false;
            newFraction = parseFraction(inputString);
            if (getCalculatorMemory() == Operation.NIL) {
                input = Operation.STORE_VALUE;
            } else {
                input = getCalculatorMemory();
                setCalculatorMemory(Operation.NIL);
            }
        }

        if (inputString.equalsIgnoreCase("n")||inputString.equalsIgnoreCase("neg")) {
            errorInput = false;
            input = Operation.NEGATE;
        }

        if (inputString.equalsIgnoreCase("a")||inputString.equalsIgnoreCase("abs")) {
            errorInput = false;
            input = Operation.ABSOLUTE;
        }

        if (inputString.equalsIgnoreCase("c")||inputString.equalsIgnoreCase("clear")) {
            errorInput = false;
            input = Operation.CLEAR_VALUE;
        }

        if (errorInput) {
            setErrorState(true);
            setFatalErrorState(true);
        }

        switch(input) {
            case ADD:
                outputFraction = fraction.add(newFraction);
                break;
            case SUBTRACT:
                outputFraction = fraction.subtract(newFraction);
                break;
            case MULTIPLY:
                outputFraction = fraction.multiply(newFraction);
                break;
            case DIVIDE:
                outputFraction = fraction.divide(newFraction);
                break;
            case ABSOLUTE:
                outputFraction = fraction.absValue();
                break;
            case NEGATE:
                outputFraction = fraction.negate();
                break;
            case STORE_VALUE:
                outputFraction = newFraction;
                break;
            case CLEAR_VALUE:
                outputFraction = new Fraction(0,1);
                break;
            default:
                outputFraction = fraction;
                break;
        }
        return(outputFraction);
    }

    private void reset() {
        setCalculatorValue(new Fraction(0,1));
        setCalculatorMemory(Operation.NIL);
        setErrorState(false);
        setFatalErrorState(false);
    }

    private boolean isInteger(String input) {
        boolean isInteger = false;
        if (input.matches("[-,+]?\\d+"))
            isInteger = true;
        return(isInteger);
    }

    private boolean isFraction(String input) {
        boolean isFraction = false;
        if (input.matches("[-,+]?\\d+[/][-,+]?\\d+")) {
            isFraction = true;
        }
        return(isFraction);
    }

    private int parseInteger(String input) {
        return(Integer.parseInt(input));
    }

    private Fraction parseFraction(String input) {
        String[] fractionItem = input.split("/");
        int numerator = Integer.parseInt(fractionItem[0]);
        int denominator = Integer.parseInt(fractionItem[1]);
        return(new Fraction(numerator, denominator));
    }
}

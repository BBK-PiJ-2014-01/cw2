/**
 * Created by Pierre on 06/11/2014.
 */
import java.util.Scanner;

public class FractionCalculator {

    private Fraction calculatorValue;
    private Operation calculatorMemory;

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
            for (int i=0; i<commandItem.length; i++) {
                if ((commandItem[i].equalsIgnoreCase("q")) || (commandItem[i].equalsIgnoreCase("quit"))) {
                    finished = true;
                    break;
                }
                setCalculatorValue(evaluate(getCalculatorValue(), commandItem[i]));
            }
            System.out.println(calculatorValue.toString()); // Remove later
        } while (!finished);
        System.out.println("Goodbye");
    }

    public Fraction evaluate(Fraction fraction, String inputString) {
        Operation input = Operation.NIL;
        Fraction outputFraction = new Fraction(0,1);

        if (inputString.equals("*")) {
            if (getCalculatorMemory() == Operation.NIL) {
                setCalculatorMemory(Operation.MULTIPLY);
            } else {
                System.out.println("Error: operator already provided");
                reset(); // check other occurrences
            }
        }

        if (inputString.equals("+")) {
            if (getCalculatorMemory() == Operation.NIL) {
                setCalculatorMemory(Operation.ADD);
            } else {
                System.out.println("Error: operator already provided");
                this.reset();
            }
        }

        if (inputString.equals("-")) {
            if (getCalculatorMemory() == Operation.NIL) {
                setCalculatorMemory(Operation.SUBTRACT);
            } else {
                System.out.println("Error: operator already provided");
                this.reset();
            }
        }

        if (inputString.equals("/")) {
            if (getCalculatorMemory() == Operation.NIL) {
                setCalculatorMemory(Operation.DIVIDE);
            } else {
                System.out.println("Error: operator already provided");
                this.reset();
            }
        }

        if (isFraction(inputString) || isInteger(inputString)) {
            if (getCalculatorMemory() == Operation.NIL) {
                input = Operation.STORE_VALUE;
            } else {
                input = getCalculatorMemory();
                setCalculatorMemory(Operation.NIL);
            }
        }

        if (inputString.equalsIgnoreCase("n")||inputString.equalsIgnoreCase("neg"))
            input = Operation.NEGATE;
        else
            if (inputString.equalsIgnoreCase("a")||inputString.equalsIgnoreCase("abs"))
               input = Operation.ABSOLUTE;
            else
                if (inputString.equalsIgnoreCase("c")||inputString.equalsIgnoreCase("clear"))
                    input = Operation.CLEAR_VALUE;

        switch(input) {
            case ADD: outputFraction = calculatorValue.add(parseFraction(inputString));
                break;
            case ABSOLUTE: outputFraction = fraction.absValue();
                break;
            case NEGATE: outputFraction = fraction.negate();
                break;
            case STORE_VALUE: outputFraction = parseFraction(inputString);
                break;
            case CLEAR_VALUE: outputFraction = new Fraction(0,1);
                break;
            default: outputFraction = fraction;
                break;
        }
        return(outputFraction);
    }

    private void reset() {
        setCalculatorValue(new Fraction(0,1));
        setCalculatorMemory(Operation.NIL);
    }

    private boolean isInteger(String input) {
        boolean isInteger = false;
        if (input.matches("[-]?\\d+"))
            isInteger = true;
        return(isInteger);
    }

    private boolean isFraction(String input) {
        boolean isFraction = false;
        if (input.matches("[-]?\\d+[/][-]?\\d+")) {
            isFraction = true;
        }
        return(isFraction);
    }

    private Fraction parseFraction(String input) {
        String[] fractionItem = input.split("/");
        int numerator = Integer.parseInt(fractionItem[0]);
        int denominator = Integer.parseInt(fractionItem[1]);
        return(new Fraction(numerator, denominator));
    }
}

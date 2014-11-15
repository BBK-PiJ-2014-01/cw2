/**
 * Created by Pierre on 06/11/2014.
 */
import java.util.Scanner;

public class FractionCalculator {

    Fraction value;
    Operation operationMemory;

    public FractionCalculator(Fraction fraction, Operation memory) {
        this.value = fraction;
        this.operationMemory = memory;
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
                value = evaluate(value, commandItem[i]);
            }
            System.out.println(value.toString()); // Remove later
        } while (!finished);
        System.out.println("Goodbye");
    }

    public Fraction evaluate(Fraction fraction, String inputString) {
        Operation input = Operation.NIL;
        Fraction outputFraction = new Fraction(0,1);
        if (inputString.equalsIgnoreCase("n")||inputString.equalsIgnoreCase("neg"))
            input = Operation.NEGATE;
        else
            if (inputString.equalsIgnoreCase("a")||inputString.equalsIgnoreCase("abs"))
               input = Operation.ABSOLUTE;
            else
                if (inputString.equalsIgnoreCase("c")||inputString.equalsIgnoreCase("clear"))
                    input = Operation.CLEAR;
                else
                    if ((inputString.length()>1) && (inputString.contains("\\"))) {
                        System.out.println("Fraction");
                    }

        switch(input) {
            case ABSOLUTE: outputFraction = fraction.absValue();
                break;
            case CLEAR: outputFraction = new Fraction(0,1);
                break;
            case NEGATE: outputFraction = fraction.negate();
                break;
            default:
                break;
        }
        return(outputFraction);
    }

    private void reset() {
        this.value = new Fraction(0,1);
        this.operationMemory = Operation.NIL;
    }

    private boolean isInteger(String input) {
        boolean isInteger = false;
        if (input.matches("-?\\d+"))
            isInteger = true;
        return(isInteger);
    }

    private boolean isFraction(String input) {

    }
}

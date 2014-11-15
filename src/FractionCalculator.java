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
                if ((commandItem[i].equals("q")) || (commandItem[i].equals("quit"))) {
                    finished = true;
                    break;
                }
                if ((commandItem[i].equals("c")) || (commandItem[i].equals("clear"))) {
                    value.setNumerator(0);
                    continue;
                }
                if ((commandItem[i].equals("*")) )
                value = evaluate(value, commandItem[i]);
            }
            System.out.println(value.toString());
        } while (!finished);
        System.out.println("Goodbye");
    }

    public Fraction evaluate(Fraction fraction, String inputString) {
        Character commandStd = ' ';
        Fraction outputFraction = new Fraction(0,1);
        if (inputString.equalsIgnoreCase("n")||inputString.equalsIgnoreCase("neg"))
            commandStd = 'n';
        else
            if (inputString.equalsIgnoreCase("a")||inputString.equalsIgnoreCase("abs"))
                commandStd = 'a';
            else
                if (inputString.equalsIgnoreCase("c")||inputString.equalsIgnoreCase("clear"))
                    commandStd = 'c';
                else
                    if ((inputString.length()>1) && (inputString.contains("\\"))) {
                        System.out.println("Fraction");
                    }

        switch(commandStd) {
            case 'a': outputFraction = fraction.absValue();
                break;
            case 'c': outputFraction = new Fraction(0,1);
                break;
            case 'n': outputFraction = fraction.negate();
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

}

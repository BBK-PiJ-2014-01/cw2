/**
 * Created by Pierre on 06/11/2014.
 */
import java.util.Scanner;

public class FractionCalculator {

    public static void main(String[] args) {
        FractionCalculator fc = new FractionCalculator();
        fc.run();
    }

    public void run() {
        boolean finished = false;
        Fraction value = new Fraction(1,1);
        Character operatorMemory = ' ';
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
                value = evaluate(value, commandItem[i]);
                value.toString();
            }
        } while (!finished);
        System.out.println("Goodbye");
    }

    public Fraction evaluate(Fraction fraction, String inputString) {
        Character commandStd = ' ';
        Fraction outputFraction = new Fraction(0,1);
        if (inputString.equalsIgnoreCase("n")||inputString.equalsIgnoreCase("neg"))
            commandStd = 'n';
        switch(commandStd) {
            case 'n': outputFraction = fraction.negate();
                break;
            default:
                break;
        }
        return(outputFraction);
    }
}

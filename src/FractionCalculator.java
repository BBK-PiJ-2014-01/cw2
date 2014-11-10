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
        Fraction value = new Fraction(0,1);
        Character operation = ' ';
        System.out.println("Welcome Pierre Meyer!");
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println(scanner.next());
            System.out.println(scanner.next());
            System.out.println(scanner.next());
            System.out.println(scanner.next());
        } while (!finished);
        System.out.println("Goodbye");
    }
/*
    public Fraction evaluate(Fraction fraction, String inputString) {
        return();
    }
*/
}

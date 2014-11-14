/**
 * Created by Pierre on 06/11/2014.
 */
public class FractionCalculatorTest {
    public static void main(String[] args) {
        FractionCalculator c = new FractionCalculator();

        FractionTest.test(new Fraction(-1, 2),c.evaluate(new Fraction(-1,2),"NEG"),"Test the Test");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"NEG"),"Error in 'evaluate method' with 'negate' command");
    }
}

/**
 * Created by Pierre on 06/11/2014.
 */
public class FractionCalculatorTest {
    public static void main(String[] args) {
        FractionCalculator c = new FractionCalculator(new Fraction(0,1),Operation.NIL);

        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"n"),"Error NEG-T1: in 'evaluate method' with 'negate' command");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"N"),"Error NEG-T2 in 'evaluate method' with 'negate' command");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"neg"),"Error NEG-T3 in 'evaluate method' with 'negate' command");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"Neg"),"Error NEG-T4 in 'evaluate method' with 'negate' command");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"NEG"),"Error NEG-T5 in 'evaluate method' with 'negate' command");

        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"a"),"Error ABS-T1: in 'evaluate method' with 'absolute' command");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"A"),"Error ABS-T2: in 'evaluate method' with 'absolute' command");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"abs"),"Error ABS-T3: in 'evaluate method' with 'absolute' command");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"Abs"),"Error ABS-T4: in 'evaluate method' with 'absolute' command");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"ABS"),"Error ABS-T5: in 'evaluate method' with 'absolute' command");

        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"a"),"Error ABS-T1: in 'evaluate method' with 'absolute' command");

    }
}

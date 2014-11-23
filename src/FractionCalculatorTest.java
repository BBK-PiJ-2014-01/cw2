/**
 * Created by Pierre on 06/11/2014.
 */
public class FractionCalculatorTest {
    public static void main(String[] args) {
        FractionCalculator c = new FractionCalculator(new Fraction(0,1),Operation.NIL);

        System.out.println("Testing Negate input: (no error message should print)");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"n"),"Error NEG-T1: in 'evaluate method' with 'negate' command");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"N"),"Error NEG-T2 in 'evaluate method' with 'negate' command");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"neg"),"Error NEG-T3 in 'evaluate method' with 'negate' command");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"Neg"),"Error NEG-T4 in 'evaluate method' with 'negate' command");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"NEG"),"Error NEG-T5 in 'evaluate method' with 'negate' command");
        System.out.println("");

        System.out.println("Testing Absolute input: (no error message should print)");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"a"),"Error ABS-T1: in 'evaluate method' with 'absolute' command");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"A"),"Error ABS-T2: in 'evaluate method' with 'absolute' command");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"abs"),"Error ABS-T3: in 'evaluate method' with 'absolute' command");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"Abs"),"Error ABS-T4: in 'evaluate method' with 'absolute' command");
        FractionTest.test(new Fraction(1, 2),c.evaluate(new Fraction(-1,2),"ABS"),"Error ABS-T5: in 'evaluate method' with 'absolute' command");
        System.out.println("");

        System.out.println("Testing Operation input with operation already in memory: (4 error messages should print)");
        c = new FractionCalculator(new Fraction(3,5),Operation.ADD);
        FractionTest.test(new Fraction(0, 1),c.evaluate(new Fraction(-1,2),"*"),"Error MEM-T1: in 'evaluate method'");
        c = new FractionCalculator(new Fraction(3,5),Operation.MULTIPLY);
        FractionTest.test(new Fraction(0, 1),c.evaluate(new Fraction(-1,2),"/"),"Error MEM-T2: in 'evaluate method'");
        c = new FractionCalculator(new Fraction(3,5),Operation.DIVIDE);
        FractionTest.test(new Fraction(0, 1),c.evaluate(new Fraction(-1,2),"+"),"Error MEM-T3: in 'evaluate method'");
        c = new FractionCalculator(new Fraction(3,5),Operation.SUBTRACT);
        FractionTest.test(new Fraction(0, 1),c.evaluate(new Fraction(-1,2),"-"),"Error MEM-T4: in 'evaluate method'");
        System.out.println("");

        System.out.println("Testing Operation input no operation in memory: (no error messages should print)");
        c = new FractionCalculator(new Fraction(0,1),Operation.NIL);
        FractionTest.test(new Fraction(-1, 2),c.evaluate(new Fraction(-1,2),"*"),"Error MEM-T1: in 'evaluate method'");
        c = new FractionCalculator(new Fraction(0,1),Operation.NIL);
        FractionTest.test(new Fraction(-1, 2),c.evaluate(new Fraction(-1,2),"/"),"Error MEM-T2: in 'evaluate method'");
        c = new FractionCalculator(new Fraction(0,1),Operation.NIL);
        FractionTest.test(new Fraction(-1, 2),c.evaluate(new Fraction(-1,2),"+"),"Error MEM-T3: in 'evaluate method'");
        c = new FractionCalculator(new Fraction(0,1),Operation.NIL);
        FractionTest.test(new Fraction(-1, 2),c.evaluate(new Fraction(-1,2),"-"),"Error MEM-T4: in 'evaluate method'");
        System.out.println("");

        System.out.println("Testing fraction numbers are accepted : (no error message, except 'testing the test', should print)");
        c = new FractionCalculator(new Fraction(0,1),Operation.ADD);
        FractionTest.test(new Fraction(13, 5),c.evaluate(new Fraction(10,5),"3/5-"),"Error FRAC-Test: testing the test");
        c = new FractionCalculator(new Fraction(0,1),Operation.ADD);
        FractionTest.test(new Fraction(13, 5),c.evaluate(new Fraction(10,5),"3/5"),"Error FRAC-T1: in 'evaluate method'");
        c = new FractionCalculator(new Fraction(0,1),Operation.ADD);
        FractionTest.test(new Fraction(13, 5),c.evaluate(new Fraction(10,5),"+3/5"),"Error FRAC-T2: in 'evaluate method'");
        c = new FractionCalculator(new Fraction(0,1),Operation.ADD);
        FractionTest.test(new Fraction(13, 5),c.evaluate(new Fraction(10,5),"3/+5"),"Error FRAC-T3: in 'evaluate method'");
        c = new FractionCalculator(new Fraction(0,1),Operation.ADD);
        FractionTest.test(new Fraction(7, 5),c.evaluate(new Fraction(10,5),"-3/5"),"Error FRAC-T4: in 'evaluate method'");
        c = new FractionCalculator(new Fraction(0,1),Operation.ADD);
        FractionTest.test(new Fraction(7, 5),c.evaluate(new Fraction(10,5),"3/-5"),"Error FRAC-T5: in 'evaluate method'");
        c = new FractionCalculator(new Fraction(0,1),Operation.ADD);
        FractionTest.test(new Fraction(13, 5),c.evaluate(new Fraction(10,5),"-3/-5"),"Error FRAC-T6: in 'evaluate method'");
        System.out.println("");

        System.out.println("Testing whole numbers are accepted : (no error message, except 'testing the test', should print)");
        c = new FractionCalculator(new Fraction(0,1),Operation.ADD);
        FractionTest.test(new Fraction(28, 5),c.evaluate(new Fraction(3,5),"5+"),"Normal Error WHOLE-Test: testing the test'");
        c = new FractionCalculator(new Fraction(0,1),Operation.ADD);
        FractionTest.test(new Fraction(28, 5),c.evaluate(new Fraction(3,5),"5"),"Error WHOLE-T1: in 'evaluate method'");
        c = new FractionCalculator(new Fraction(0,1),Operation.ADD);
        FractionTest.test(new Fraction(28, 5),c.evaluate(new Fraction(3,5),"+5"),"Error WHOLE-T2: in 'evaluate method'");
        c = new FractionCalculator(new Fraction(0,1),Operation.ADD);
        FractionTest.test(new Fraction(-22, 5),c.evaluate(new Fraction(3,5),"-5"),"Error WHOLE-T3: in 'evaluate method'");
        System.out.println("");

        System.out.println("Testing that complex input are processed correctly  : (no error message, except 'testing the test', should print)");
        c = new FractionCalculator(new Fraction(0,1),Operation.NIL);
        FractionTest.test(new Fraction(28, 5),c.evaluate(new Fraction(3,5),"5 + 5 * 2 + 1/2 NEG"),"Normal Error COMPLEX-Test: testing the test");
        c = new FractionCalculator(new Fraction(0,1),Operation.NIL);
        FractionTest.test(new Fraction(41, -2),c.evaluate(new Fraction(3,5),"5 + 5 * 2 + 1/2 NEG"),"Error COMPLEX-T1: in 'evaluate method'");
        c = new FractionCalculator(new Fraction(0,1),Operation.NIL);
        FractionTest.test(new Fraction(117, -10),c.evaluate(new Fraction(3,5),"+ 5 * 2 + 1/2 NEG"),"Error COMPLEX-T2: in 'evaluate method'");
        c = new FractionCalculator(new Fraction(0,1),Operation.NIL);
        FractionTest.test(new Fraction(1, 5),c.evaluate(new Fraction(3,5),"3 * 2/5 - 1 ABS"),"Error COMPLEX-T3: in 'evaluate method'");
        System.out.println("");
    }
}

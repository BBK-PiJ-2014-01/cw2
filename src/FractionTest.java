/**
 * Created by keith for the second coursework assignment.
 * Extended by Pierre to test methods: add, subtract, divide, absValue, negate
 */
public class FractionTest {
    public static void main(String[] args) {

        // test divide by zero - should print an error and exit
        System.out.println("TEST1: divide by zero");
        new Fraction(1, 0);

        // test multiply
        System.out.println("TEST2: multiply two fractions");
        Fraction mulResult = new Fraction(3,10);
        Fraction mulA = new Fraction(1,2);
        Fraction mulB = new Fraction(3,5);
        System.out.println(mulResult.toString() + " = " + mulA.toString() + " * " + mulB.toString());
        if (!mulResult.equals(mulA.multiply(mulB)))
            System.out.println("--- Multiply failed");
        else
            System.out.println("--- Multiply succeeded");

        // test add
        System.out.println("TEST3: add two fractions");
        Fraction addResult = new Fraction(1,3);
        Fraction addA = new Fraction(-4,2);
        Fraction addB = new Fraction(7,3);
        System.out.println(addResult.toString() + " = " + addA.toString() + " + " + addB.toString());
        if (!addResult.equals(addA.add(addB)))
            System.out.println("--- Add failed");
        else
            System.out.println("--- Add succeeded");

        // test subtract
        System.out.println("TEST4: subtract two fractions");
        Fraction subResult = new Fraction(-29,6);
        Fraction subA = new Fraction(-5,2);
        Fraction subB = new Fraction(7,3);
        System.out.println(subResult.toString() + " = " + subA.toString() + " - " + subB.toString());
        if (!subResult.equals(subA.subtract(subB)))
            System.out.println("--- Subtract failed");
        else
            System.out.println("--- Subtract succeeded");

        // test divide
        System.out.println("TEST5: divide two fractions");
        Fraction divResult = new Fraction(-27,35);
        Fraction divA = new Fraction(-3,5);
        Fraction divB = new Fraction(7,9);
        System.out.println(divResult.toString() + " = " + divA.toString() + " / " + divB.toString());
        if (!divResult.equals(divA.divide(divB)))
            System.out.println("--- Divide failed");
        else
            System.out.println("--- Divide succeeded");

        // test absValue
        System.out.println("TEST6: absolute value of a fraction");
        Fraction absAResult = new Fraction(1,7);
        Fraction absA = new Fraction(1,7);
        Fraction absBResult = new Fraction(1,7);
        Fraction absB = new Fraction(-1,7);
        Fraction absCResult = new Fraction(1,7);
        Fraction absC = new Fraction(1,-7);
        Fraction absDResult = new Fraction(1,7);
        Fraction absD = new Fraction(-1,-7);
        Fraction absEResult = new Fraction(-1,7);
        Fraction absE = new Fraction(1,7);
        test(absA.absValue(),absAResult,"error test 1");
        test(absB.absValue(),absBResult,"error test 2");
        test(absC.absValue(),absCResult,"error test 3");
        test(absD.absValue(),absDResult,"error test 4");
        test(absE.absValue(),absEResult,"error test 5 - EXPECTED");

        // test negate
        System.out.println("TEST7: negate a fraction");
        Fraction negAResult = new Fraction(-1,7);
        Fraction negA = new Fraction(1,7);
        Fraction negBResult = new Fraction(1,7);
        Fraction negB = new Fraction(-1,7);
        Fraction negCResult = new Fraction(1,7);
        Fraction negC = new Fraction(1,-7);
        Fraction negDResult = new Fraction(-1,7);
        Fraction negD = new Fraction(-1,-7);
        Fraction negEResult = new Fraction(1,7);
        Fraction negE = new Fraction(1,7);
        test(negA.negate(),negAResult,"error test 1");
        test(negB.negate(),negBResult,"error test 2");
        test(negC.negate(),negCResult,"error test 3");
        test(negD.negate(),negDResult,"error test 4");
        test(negE.negate(),negEResult,"error test 5 - EXPECTED");

        // test equals
        System.out.println("TEST8: test equals");
        test(new Fraction(1, 2),new Fraction(1, 2),"error test 1");
        test(new Fraction(1, 2),new Fraction(3, 6),"error test 2");
        test(new Fraction(-1, 2),new Fraction(1, -2),"error test 3");
        test(new Fraction(-1, -2),new Fraction(1, 2),"error test 4");
        test(new Fraction(4, -8),new Fraction(1, 2),"error test 5 - EXPECTED");
    }

    static void test(Fraction f1, Fraction f2, String msg){
        if (! f1.equals(f2))
            System.out.println(msg);
    }
}

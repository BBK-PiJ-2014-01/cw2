/*
    Created by keith for the second coursework assignment.
    Extended by Pierre:
    - New methods: add, subtract, divide, absValue, negate, sign
*/
public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int num, int denom) {
        if (denom == 0) {
            System.out.println("Invalid fraction with denominator 0");
            // this should use exceptions
            return;
        }
        int gcd = myGcd(num, denom);
        setNumerator(num / gcd);
        setDenominator(denom / gcd);
    }

    @Override
    /*
    'toString' method
    Description:
        - returns a representation of a fraction
    Returns:
        - a String with both numerator and denominator separated by a '/', if the denominator != 1
        - a String with the numerator only, if the denominator = 1
     */
    public String toString() {
        if (this.getDenominator() == 1)
            return("" + getNumerator());
        else
            return("" + getNumerator() + '/' + getDenominator());
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int num) {
        numerator = num;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int num) {
        denominator = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (getDenominator() != fraction.getDenominator()) return false;
        if (getNumerator() != fraction.getNumerator()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }

    /*
    Description:
        - multiplies a fraction by another fraction
    Returns:
        - resulting fraction of the multiplication
     */
    public Fraction multiply(Fraction other) {
        int num = this.getNumerator() * other.getNumerator();
        int denom = this.getDenominator() * other.getDenominator();
        return new Fraction(num, denom);
    }

    /*
    'add' method
    Description:
        - adds a fraction to another fraction
    Returns:
        - resulting fraction of the addition
     */
    public Fraction add(Fraction other) {
        int num = (this.getNumerator() * other.getDenominator()) + (other.getNumerator() * this.getDenominator());
        int denom = this.getDenominator() * other.getDenominator();
        return new Fraction(num, denom);
    }

    /*
    'divide' method
    Description:
        - subtracts a fraction from another fraction
    Returns:
        - resulting fraction of the subtraction
 */
    public Fraction subtract(Fraction other) {
        int num = (this.getNumerator() * other.getDenominator()) - (other.getNumerator() * this.getDenominator());
        int denom = this.getDenominator() * other.getDenominator();
        return new Fraction(num, denom);
    }

    /*
    'divide' method
    Description:
        - divides a fraction by another fraction
    Returns:
        - resulting fraction of the division
     */
    public Fraction divide(Fraction other) {
        int num = this.getNumerator() * other.getDenominator();
        int denom = this.getDenominator() * other.getNumerator();
        return new Fraction(num, denom);
    }

    /*
    'absValue' method
    Description:
        - calculate the absolute value of a fraction
    Returns:
        - absolute value of a fraction
     */
    public Fraction absValue() {
        int num = (int) Math.abs(this.getNumerator());
        int denom = (int) Math.abs(this.getDenominator());
        return new Fraction(num, denom);
    }

    /*
    'negate' method
    Description:
        - changes the sign of a fraction
        - makes sure the resulting fraction hasn't got both numerator & denominator with a negative sign
    Returns:
        - fraction with sign changed
     */
    public Fraction negate() {
        int num = this.getNumerator();
        int denom = this.getDenominator();
        if ((sign(this.getNumerator()) == 1) && (sign(this.getDenominator()) == -1))
            denom *= -1;
        else
            num *= -1;
        return new Fraction(num, denom);
    }

    /*
    'sign' method
    Description:
        - identifies the sign of an integer
    Parameters;
        int a (an integer)
    Returns:
        -1  if a is negative
        1   if a is positive
        0   if a = 0
     */
    private int sign(int a) {
        if (a == 0)
            return (0);
        if (a > 0)
           return (1);
        else
           return (-1);
    }

    private int myGcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}

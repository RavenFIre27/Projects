import java.util.Scanner;

public class FracCalc
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter an equation: ");
        String input = in.nextLine();
        produceAnswer(input);
    }
    public static String produceAnswer(String in) {
        //split up input into two numbers and an operator
        Scanner input = new Scanner(in);
        input.useDelimiter(" ");
        String num1 = input.next();
        String operator = input.next();
        String num2 = input.next();
        
        if (operator.equals("+")) {
            return add(num1, num2);
        }
        if (operator.equals("-")) {
            return subtract(num1, num2);
        }
        if (operator.equals("*")) {
            return multiply(num1, num2);
        }
        if (operator.equals("/")) {
            return divide(num1, num2);
        }
        return "error";
    }
    
    //method to get a whole number from any input number (except for improper fractions)
    public static int getWhole(String in) {
        Scanner num = new Scanner(in);
        int whole;

        //if it's a mixed number
        if (in.contains("_")) {
            num.useDelimiter("_");
            whole = Integer.parseInt(num.next());
        }
        //if it's a whole number
        else if(in.contains("/") == false) {
            whole = Integer.parseInt(in);
        }
        //if it's a fraction with no whole number
        else {
            whole = 0;
        }
        return whole;
    }

    //method to get the numerator from any fraction
    public static int getNumer(String in) {
        Scanner num = new Scanner(in);
        String frac;

        //if it's a mixed number (account for the whole number)
        if (in.contains("_")) {
            num.useDelimiter("_");
            num.next();
            frac = num.next();
        }
        //if it's a normal fraction
        else if (in.contains("/")) {
            frac = in;
        }
        //if it's just a whole number with no fraction
        else {
            return 0;
        }

        Scanner fraction = new Scanner(frac);
        fraction.useDelimiter("/");
        return Integer.parseInt(fraction.next());
    }

    //method to get the denominator from any fraction
    public static int getDenom(String in) {
        Scanner num = new Scanner(in);
        String frac;

        //if it's a mixed number (account for the whole number)
        if (in.contains("_")) {
            num.useDelimiter("_");
            num.next();
            frac = num.next();
        }
        //if it's a normal fraction
        else if (in.contains("/")) {
            frac = in;
        }
        //if it's just a whole number with no fraction
        else {
            return 1;
        }
        Scanner fraction = new Scanner(frac);
        fraction.useDelimiter("/");
        fraction.next();
        return Integer.parseInt(fraction.next());
    }

    //method to convert a mixed number to an improper fraction
    public static String toImproperFrac(String mixed) {
        int numer;

        //if whole number is negative, account for the fact that the numerator is considered positive
        if (getWhole(mixed) < 0) {
            numer = (getWhole(mixed) * getDenom(mixed)) - getNumer(mixed);
        }
        //if whole number is positive or if there is no whole number
        else {
            numer = (getWhole(mixed) * getDenom(mixed)) + getNumer(mixed);
        }

        return numer + "/" + getDenom(mixed);
    }

    //method to convert an improper fraction to a mixed number
    public static String toMixedNum(String improper) {

        //get components of mixed number from the improper fraction
        int whole = getNumer(improper) / getDenom(improper);
        int numer = getNumer(improper) - (whole * getDenom(improper));
        int denom = getDenom(improper);

        //convert improper fractions to mixed number form
        if (whole != 0) {
            return whole + "_" + numer + "/" + denom;
        }
        //if it's a normal fraction, keep it as is
        else {
            return improper;
        }
    }
    
    //method to simplify large fractions & account for any errors
    public static String simplify(String answer) {
        int denom = getDenom(answer);
        int numer = getNumer(answer);

        //makes sure mixed number doesn't have negative signs in numerator or denominator
        //prevents numbers like -2_3/-4
        if (getWhole(answer) != 0) {
            if (denom < 0) {
                denom *= -1;
            }
            if (numer < 0) {
                numer *= -1;
            }
        }

        //makes sure the negative sign is in the numerator, not the denominator
        //prevents fractions like 1/-2
        if((numer > 0) && (denom < 0)) {
            numer *= -1;
            denom *= -1;
        }

        int i = numer;
        //accounts for negative numerators
        if (numer < 0) {
            i *= -1;
        }
        //simplifies fractions by dividing them by a list of numbers from 1 to the numerator number
        //ensuring all common factors are divided out
        while(i > 0) {
            if ((denom % i == 0) && (numer % i == 0)) {
                numer /= i;
                denom /= i;
            }
            i--;
        }

        //for numbers like 0/3, return 0
        if (getNumer(answer) == 0) {
            if (getWhole(answer) == 0) {
                return "0";
            }
            else {
                return "" + getWhole(answer);
            }
        }

        //for numbers like 3/1, return 3
        if (getDenom(answer) == 1) {
            return "" + getNumer(answer);
        }

        //if there's a whole number, put the simplified numerator and denominator into the mixed fraction
        if (getWhole(answer) != 0) {
            return getWhole(answer) + "_" + numer + "/" + denom;
        }
        //if it's a fraction with no whole number
        return numer + "/" + denom;
    }

    //method to add numbers
    public static String add(String frac1, String frac2) {
        //convert any mixed numbers to improper fractions
        frac1 = toImproperFrac(frac1);
        frac2 = toImproperFrac(frac2);

        //do the fraction addition with the improper fractions
        int sumNumer = (getNumer(frac1)*getDenom(frac2)) + (getNumer(frac2)*getDenom(frac1));
        int sumDenom = getDenom(frac1)*getDenom(frac2);
        String sum = sumNumer + "/" + sumDenom;

        //convert the sum to a mixed number and simplify it
        return simplify(toMixedNum(sum));
    }

    ////method to subtract numbers
    public static String subtract(String frac1, String frac2) {
        //convert any mixed numbers to improper fractions
        frac1 = toImproperFrac(frac1);
        frac2 = toImproperFrac(frac2);

        //do the fraction subtraction with the improper fractions
        int differNumer = (getNumer(frac1)*getDenom(frac2)) - (getNumer(frac2)*getDenom(frac1));
        int differDenom = getDenom(frac1)*getDenom(frac2);
        String difference = differNumer + "/" + differDenom;

        //convert the difference to a mixed number and simplify it
        return simplify(toMixedNum(difference));
    }

    //method to multiply numbers
    public static String multiply(String frac1, String frac2) {
        //convert any mixed numbers to improper fractions
        frac1 = toImproperFrac(frac1);
        frac2 = toImproperFrac(frac2);

        //do the fraction multiplication with the improper fractions
        int productNumer = getNumer(frac1) * getNumer(frac2);
        int productDenom = getDenom(frac1) * getDenom(frac2);
        String product = productNumer + "/" + productDenom;

        //convert the product to a mixed number and simplify it
        return simplify(toMixedNum(product));
    }

    //method to divide numbers
    public static String divide(String frac1, String frac2) {
        //convert any mixed numbers to improper fractions
        frac1 = toImproperFrac(frac1);
        frac2 = toImproperFrac(frac2);

        //do the fraction division with the improper fractions
        int quotientNumer = getNumer(frac1) * getDenom(frac2);
        int quotientDenom = getDenom(frac1) * getNumer(frac2);
        String quotient = quotientNumer + "/" + quotientDenom;

        //convert the quotient to a mixed number and simplify it
        return simplify(toMixedNum(quotient));
    }
}
}
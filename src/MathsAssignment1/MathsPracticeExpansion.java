package MathsAssignment1;

import java.util.Scanner;

/**
 * 
 * @author Dariusz Halasa
 */

public class MathsPracticeExpansion {

    public static void main(String[] args) {
        Scanner kbInt = new Scanner(System.in);
        Scanner kbDoub = new Scanner(System.in);
        Scanner kbStr = new Scanner(System.in);
        String finish = "";
        do {
            System.out.println("Please input number of trials");
            int n = kbInt.nextInt();

            System.out.println("\nPlease input number of successes");
            int x = kbInt.nextInt();

            System.out.println("Please input probability of a success");
            double p = kbDoub.nextDouble();
            System.out.println("");
            int[][] data = binomialExpansion(n);
            
            System.out.print("\nProbability of " + x + " out of " + n + " successes, at probability: " + p + " is: ");
            System.out.println(binomialProbability(data, x, p));

            System.out.println("Cumulative Probability for at least " + x + " successes is: " + cumProbability(data, x, p));

            System.out.println("\nWould you like to exit program y/n?");
            finish = kbStr.next();
        } while (!finish.equals("y"));

    }//main

    // method to return factorial of a number to be used for nCr method.
    static int factorial(int num) {
        int total = 1;
        for (int index = 1; index <= num; index++) {
            total *= index;
        }

        return total;
    }//factorial

    //nCr method using the factorial method.
    static int nCr(int n, int r) {
        int ncr;
        //n! / r! x (n-r)! this is the formula
        ncr = factorial(n) / (factorial(r) * factorial(n - r));
        return ncr;
    }//nCr

    //binomial expansion method that returns 2d array for next part and also prints the binomial expansion in form of (a+b)^n
    static int[][] binomialExpansion(int n) {
        int[][] data = new int[n + 1][3];

        for (int row = 0; row <= data.length - 1; row++) {
            data[row][0] = nCr(n, row);
            data[row][1] = (n - row);
            data[row][2] = row;
        }

        for (int index = 0; index <= n; index++) {
            System.out.print("" + ((index == 0 || index == n) ? "" : nCr(n, index) + "")
                    + ((index == n) ? "" : "(a^" + (n - index) + ")")
                    + ((index == 0) ? "" : "(b^" + index + ")")
                    + ((index != n) ? " + " : ""));
        }

        return data;
    }//binomialExpansion

    //binomial probability method to calculate the chance of an event occurring a certain amount of time at a cetain probability.
    static double binomialProbability(int[][] data, int x, double p) {
        double probTotal = 0;
        for (int row = 0; row <= data.length - 1; row++) {
            if (data[row][1] == x) {
                probTotal = data[row][0] * (Math.pow(p, data[row][1])) * (Math.pow((1 - p), data[row][2]));
            }
        }
        return probTotal;

    }

    //cumulative probability method to find the probability of an event for at least that number of events.
    static double cumProbability(int[][] data, int x, double p) {
        double cumTotal = 0;
        int test = 0;
        int row;

        for (row = 0; row <= data.length - 1; row++) {
            if (data[row][1] == x) {
                test = row; // gets the row number that the number of successes is at. This is used for the next for loop.
            }
        }
        // The for loop starts at the index that the value of x is at and goes backwards to add on remaining probabilities.
        for (int index = test; index >= 0; index--) {
            cumTotal += data[index][0] * (Math.pow(p, data[index][1])) * (Math.pow((1 - p), data[index][2]));
        }
        return cumTotal;
    }//cumProbability

}//class

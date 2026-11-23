package challenges;

// SECTION C: Arrays. for loops over an array, and a 2D array.
// The test is src/test/java/challenges/ArrayChallengesTest.java.
// Change only the lines marked YOUR CODE. Each method says what to give back.
// Refresh: Part 08 (for loops and nested loops), Part 10 (arrays and 2D arrays).

public  class ArrayChallenges {
    public static double cheapest(double[] prices) {
        if (prices.length == 0) {
            return 0.0;
        }

        double lowest = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < lowest) {
                lowest = prices[i];
            }
        }

        return lowest;
    }


    // How many prices are MORE than limit? (Equal to limit does not count.)
    //   countOver({3.25, 2.00, 4.50}, 3.00) -> 2
    //   countOver({3.25, 2.00, 4.50}, 4.50) -> 0
    public static int countOver(double[] prices, double limit) {
        int count = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > limit) {
                count++;
            }
        }

        return count;
    }

    public static int busiestDay(int[][] cups) {
        if (cups.length == 0) {
            return -1;
        }

        int busiest = 0;
        int highestTotal = 0;

        for (int i = 0; i < cups.length; i++) {
            int total = 0;

            for (int j = 0; j < cups[i].length; j++) {
                total += cups[i][j];
            }

            if (total > highestTotal || i == 0) {
                highestTotal = total;
                busiest = i;
            }
        }

        return busiest;
    }
}

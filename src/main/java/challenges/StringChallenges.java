package challenges;

// SECTION B: Strings. length, charAt, substring, indexOf, toUpperCase, trim, equals.
// The test is src/test/java/challenges/StringChallengesTest.java.
// Change only the lines marked YOUR CODE. Each method says what to give back.
// Refresh: Part 11 (String methods and wrapper classes), Part 14 (printf).

public class StringChallenges {


    // A short code for the drink and size.
    public static String orderCode(String name, char size) {
        String firstThree;

        if (name.length() < 3) {
            firstThree = name;
        } else {
            firstThree = name.substring(0, 3);
        }

        return firstThree.toUpperCase() + "-" + Character.toUpperCase(size);
    }

    // Formats the customer's name.
    public static String cupName(String name) {
        name = name.trim();

        if (name.length() == 0) {
            return "Guest";
        }

        return name.substring(0, 1).toUpperCase()
                + name.substring(1).toLowerCase();
    }

    // Counts how many times a word appears in an order.
    public static int countWord(String order, String word) {
        if (word.length() == 0) {
            return 0;
        }

        int count = 0;

        order = order.toLowerCase();
        word = word.toLowerCase();

        int index = order.indexOf(word);

        while (index != -1) {
            count++;
            order = order.substring(index + word.length());
            index = order.indexOf(word);
        }

        return count;
    }

    // Creates a receipt line with dots and the price.
    public static String receiptLine(String name, double price) {
        String result = name;

        while (result.length() < 20) {
            result += ".";
        }

        return result + "$" + String.format("%.2f", price);
    }
}
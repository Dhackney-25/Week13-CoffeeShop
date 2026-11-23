package coffeeshop;

import java.util.ArrayList;

// STEP 4 · Order: one customer's order.
//
// Open UML.md and find "Step 4 · Order". This is the biggest class. Build it one method at a
// time, and run the test after each one.
//   2 constants:      COUPON_CODE, COUPON_PERCENT   (public static final)
//   1 static field:   nextOrderNumber, starts at 1
//   3 private fields: orderNumber, items, discountPercent
//   1 constructor:    Order()
//   10 methods:       getOrderNumber(), addItem(), removeItem(), getItemCount(), isEmpty(),
//                     getSubtotal(), applyCoupon(), getDiscount(), getTotal(), toString()
//
// Then drag check/coffeeshop/OrderTest.java into src/test/java/coffeeshop/ and run it.
// Commit message:  step 4: Order

public class Order {
    private static int nextOrderNumber = 1;

    private int orderNumber;
    private ArrayList<MenuItem> items;
    private double discountPercent;

    // Creates a new order with a unique order number.
    public Order() {
        orderNumber = nextOrderNumber;
        nextOrderNumber++;

        items = new ArrayList<>();
        discountPercent = 0;
    }

    // Returns the order number.
    public int getOrderNumber() {
        return orderNumber;
    }

    // Returns the items in the order.
    public ArrayList<MenuItem> getItems() {
        return items;
    }

    // Returns the discount percentage.
    public double getDiscountPercent() {
        return discountPercent;
    }

    // Adds an item to the order.
    public void addItem(MenuItem item) {
        items.add(item);
    }

    // Removes an item using its one-based position.
    public boolean removeItem(int number) {
        if (number < 1 || number > items.size()) {
            return false;
        }

        items.remove(number - 1);
        return true;
    }

    // Returns how many items are in the order.
    public int getItemCount() {
        return items.size();
    }

    // Checks whether the order has no items.
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Calculates the subtotal before discounts.
    public double getSubtotal() {
        double subtotal = 0;

        for (MenuItem item : items) {
            subtotal += item.getPrice();
        }

        return subtotal;
    }

    // Applies the coupon if the code is correct.
    public boolean applyCoupon(String code) {
        if (code != null && code.trim().equalsIgnoreCase("HORNET10")) {
            discountPercent = 10;
            return true;
        }

        return false;
    }

    // Calculates the discount amount.
    public double getDiscountAmount() {
        return getSubtotal() * discountPercent / 100;
    }

    // Calculates the final total after discount and tip.
    public double getTotal(double tipPercent) {
        if (tipPercent < 0) {
            tipPercent = 0;
        }

        double discountedSubtotal = getSubtotal() - getDiscountAmount();
        double tip = discountedSubtotal * tipPercent / 100;

        return discountedSubtotal + tip;
    }

    // Displays the order details and subtotal.
    public String toString() {
        String result = "Order #" + orderNumber + "\n";

        for (int i = 0; i < items.size(); i++) {
            result += (i + 1) + ". " + items.get(i).toString() + "\n";
        }

        result += "Subtotal: $" + String.format("%.2f", getSubtotal()) + "\n";

        if (discountPercent > 0) {
            result += "Discount (" + String.format("%.0f", discountPercent)
                    + "%): -$" + String.format("%.2f", getDiscountAmount()) + "\n";
        }

        return result;
    }
}



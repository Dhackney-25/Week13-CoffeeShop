package coffeeshop;

import java.util.ArrayList;

// STEP 5 · CoffeeShop: the shop, its menu, and every order it finished today.
//
// Open UML.md and find "Step 5 · CoffeeShop".
//   4 private fields: name, menu, completedOrders, totalSales
//   1 constructor:    CoffeeShop(String name)
//   7 methods:        getName(), getMenu(), checkout(), getOrdersCompleted(),
//                     getTotalSales(), getItemsSold(), salesReport()
//
// Then drag check/coffeeshop/CoffeeShopTest.java into src/test/java/coffeeshop/ and run it.
// Commit message:  step 5: CoffeeShop

public class CoffeeShop {
    private String name;
    private Menu menu;
    private ArrayList<Order> completedOrders;
    private double totalSales;

    // Creates a coffee shop with a name, menu, and empty sales records.
    public CoffeeShop(String name) {
        this.name = name;
        this.menu = new Menu();
        this.completedOrders = new ArrayList<>();
        this.totalSales = 0.0;
    }

    // Returns the coffee shop's name.
    public String getName() {
        return name;
    }

    // Returns the coffee shop's menu.
    public Menu getMenu() {
        return menu;
    }

    // Returns the number of orders that have been completed.
    public int getCompletedOrderCount() {
        return completedOrders.size();
    }

    // Returns the total money earned from completed orders.
    public double getTotalSales() {
        return totalSales;
    }

    // Returns the total number of items sold across all completed orders.
    public int getItemsSold() {
        int itemsSold = 0;

        for (Order order : completedOrders) {
            itemsSold += order.getItemCount();
        }

        return itemsSold;
    }

    // Completes an order, adds its total to sales, and saves the order.
    public double checkout(Order order, int tipPercent) {
        if (order == null || order.isEmpty()) {
            return 0.0;
        }

        double total = order.getTotal(tipPercent);

        completedOrders.add(order);
        totalSales += total;

        return total;
    }

    // Creates a report showing the shop's completed orders and sales.
    public String salesReport() {
        return "Sales Report: " + name + "\n"
                + "Completed orders: " + getCompletedOrderCount() + "\n"
                + "Items sold: " + getItemsSold() + "\n"
                + "Total sales: $" + String.format("%.2f", totalSales);
    }
}


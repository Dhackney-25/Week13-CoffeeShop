package coffeeshop;

import java.util.Scanner;

// STEP 6 · CoffeeShopApp: the game you play. This is the class with main.
//
// It already runs. Click the green arrow next to main and try it.
//
// In Step 6 you finish it. Open UML.md ("Step 6") and the README ("Step 6") for the
// screens and the exact sentences. You add:
//   2 more private fields: shop and order
//   the game loop in run()
//   printBanner(), printMainMenu(), and one method for each menu choice
//
// Then drag check/coffeeshop/CoffeeShopAppTest.java into src/test/java/coffeeshop/ and run it.
// Commit message:  step 6: game loop

public class CoffeeShopApp {

    private Scanner input;
    private CoffeeShop shop;
    private Order order;

    // Creates the coffee shop and the first order.
    public CoffeeShopApp(Scanner input) {
        this.input = input;
        this.shop = new CoffeeShop("Hornet Coffee");
        this.order = new Order();
    }

    // Runs the game until the user chooses 0.
    public void run() {
        printBanner();

        int choice;

        do {
            printMainMenu();
            choice = readNumber("Choose an option: ");

            switch (choice) {
                case 1:
                    System.out.println(shop.getMenu());
                    break;

                case 2:
                    addItem();
                    break;

                case 3:
                    removeItem();
                    break;

                case 4:
                    viewOrder();
                    break;

                case 5:
                    useCoupon();
                    break;

                case 6:
                    checkout();
                    break;

                case 7:
                    System.out.println(shop.salesReport());
                    break;

                case 0:
                    System.out.println("Shop closed. Goodbye!");
                    break;

                default:
                    System.out.println("That is not on the list. Pick 0 to 7.");
            }

        } while (choice != 0);
    }

    // Prints the coffee shop welcome banner.
    private void printBanner() {
        System.out.println("==============================");
        System.out.println("       HORNET COFFEE");
        System.out.println("==============================");
    }

    // Prints the main menu options.
    private void printMainMenu() {
        System.out.println("\nWelcome to " + shop.getName());
        System.out.println("Order #" + order.getOrderNumber());
        System.out.println("Items: " + order.getItemCount());
        System.out.println("1. View Menu");
        System.out.println("2. Add Item");
        System.out.println("3. Remove Item");
        System.out.println("4. View Order");
        System.out.println("5. Use Coupon");
        System.out.println("6. Checkout");
        System.out.println("7. Sales Report");
        System.out.println("0. Exit");
    }

    // Allows the customer to add an item to the order.
    private void addItem() {
        System.out.println(shop.getMenu());

        int number = readNumber("Item number: ");
        MenuItem picked = shop.getMenu().getItem(number);

        if (picked == null) {
            System.out.println("That item number is not valid.");
            return;
        }

        if (picked.isDrink()) {
            String sizeText = readText("Size (S, M, L): ");

            char size = 'S';

            if (!sizeText.isEmpty()) {
                size = sizeText.toUpperCase().charAt(0);
            }

            Drink drink = new Drink(picked.getName(), picked.getPrice(), size);
            order.addItem(drink);

        } else {
            String answer = readText("Heat it up? (y/n): ");

            boolean heated = answer.equalsIgnoreCase("y");

            Food food = new Food(picked.getName(), picked.getPrice(), heated);
            order.addItem(food);
        }

        System.out.println("Item added to your order!");
    }

    // Removes an item from the current order.
    private void removeItem() {
        if (order.isEmpty()) {
            System.out.println("Your order is empty.");
            return;
        }

        System.out.println(order);

        int number = readNumber("Which item number would you like to remove? ");

        if (order.removeItem(number)) {
            System.out.println("Item removed.");
        } else {
            System.out.println("That item number is not valid.");
        }
    }

    // Displays the current order.
    private void viewOrder() {
        if (order.isEmpty()) {
            System.out.println("Your order is empty.");
        } else {
            System.out.println(order);
        }
    }

    // Allows the customer to enter a coupon code.
    private void useCoupon() {
        String code = readText("Coupon code: ");

        if (order.applyCoupon(code)) {
            System.out.println("Coupon applied!");
        } else {
            System.out.println("Invalid coupon code.");
        }
    }

    // Completes the order and starts a new one.
    private void checkout() {
        if (order.isEmpty()) {
            System.out.println("Your order is empty.");
            return;
        }

        System.out.println(order);

        double tip = readNumber("Tip percent (0, 10, 15, 20): ");

        double total = shop.checkout(order, tip);

        System.out.println("Total: $" + String.format("%.2f", total));

        order = new Order();
    }

    // GIVEN CODE: Reads a whole number from the user.
    private int readNumber(String prompt) {
        System.out.print(prompt);

        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.print("Please type a number: ");
        }

        int number = input.nextInt();
        input.nextLine();

        return number;
    }

    // GIVEN CODE: Reads text from the user.
    private String readText(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    // Starts the coffee shop game.
    public static void main(String[] args) {
        CoffeeShopApp app = new CoffeeShopApp(new Scanner(System.in));
        app.run();
    }
}
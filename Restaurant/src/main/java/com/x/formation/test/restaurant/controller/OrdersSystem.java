
package com.x.formation.test.restaurant.controller;

import com.x.formation.test.restaurant.entity.ProcessStage;
import com.x.formation.test.restaurant.entity.ExtraDemand;
import com.x.formation.test.restaurant.entity.Item;
import com.x.formation.test.restaurant.entity.Category;
import com.x.formation.test.restaurant.entity.Order;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A controlling class which has the starter function and processing methods
 * @author aabum
 */
public class OrdersSystem {

    private static OrdersSystem system;
    private final ArrayList<Order> orders;
    private final Scanner scanner;
    private Category currentCateogy;
    private Order currentOrder;
    private Item currentItem;
    private ProcessStage stage;

    /**
     * private constructor to make it singleton
     */
    private OrdersSystem() {
        orders = new ArrayList<>();
        scanner = new Scanner(System.in);
        stage = ProcessStage.MAIN_PAGE;
    }

    /**
     * @return the predefined instance of the system
     */
    public static OrdersSystem getInstatnce() {
        if (system == null) {
            system = new OrdersSystem();
        }
        return system;
    }

    /**
     * Temporary method to add the define the menu and its items
     * <b>
     * It can be replaced by database query or file system, but for now it's out
     * of the purpose scope
     */
    private void initiateMenu() {
        Category drinkCat = MenuBuilder.addCategory(Menu.getInstance(), "Drink");
        Category lunchCat = MenuBuilder.addCategory(Menu.getInstance(), "Lunch");

        MenuBuilder.addItem(drinkCat, "Cola", 5.0f).setExtraDemandsList(ExtraDemand.DRINK);
        MenuBuilder.addItem(drinkCat, "Tea", 6.0f).setExtraDemandsList(ExtraDemand.DRINK);
        MenuBuilder.addItem(drinkCat, "Coffee", 7.5f).setExtraDemandsList(ExtraDemand.DRINK);

        Category dessertCat = MenuBuilder.addCategory(lunchCat, "Dessert");
        Category mainCourseCat = MenuBuilder.addCategory(lunchCat, "Main Course");

        MenuBuilder.addItem(dessertCat, "Cake", 10.0f);
        MenuBuilder.addItem(dessertCat, "Ice Cream", 15.0f);
        MenuBuilder.addItem(dessertCat, "Cheesecake", 12.0f);

        Category polishCuisinesCat = MenuBuilder.addCategory(mainCourseCat, "Polish Cuisines");
        Category mexicanCuisinesCat = MenuBuilder.addCategory(mainCourseCat, "Mexican Cuisines");
        Category italianCuisinesCat = MenuBuilder.addCategory(mainCourseCat, "Italian Cuisines");

        MenuBuilder.addItem(polishCuisinesCat, "Pierogi", 10.0f);
        MenuBuilder.addItem(polishCuisinesCat, "Bigos", 15.0f);
        MenuBuilder.addItem(polishCuisinesCat, "Żurek", 20.0f);

        MenuBuilder.addItem(mexicanCuisinesCat, "Aguachile", 10.0f);
        MenuBuilder.addItem(mexicanCuisinesCat, "Burrito", 12.0f);
        MenuBuilder.addItem(mexicanCuisinesCat, "Chalupa", 8.0f);

        MenuBuilder.addItem(italianCuisinesCat, "Pizza 1", 10.0f);
        MenuBuilder.addItem(italianCuisinesCat, "Pizza 2", 10.0f);
        MenuBuilder.addItem(italianCuisinesCat, "Pizza 3", 10.0f);

    }

    /**
     * Display a category of the menu to allow the user to choose the items from
     * it.
     */
    private void printCategory(Category category) {
        System.out.println("*********************");
        System.out.print(category.getName());
        ArrayList<Category> subCategories = category.getSubCategories();
        if (subCategories.size() > 0) {
            for (int i = 0; i < subCategories.size(); i++) {
                System.out.format("\n\t %s- %s", i + 1, subCategories.get(i).getName());
            }
        } else {
            ArrayList<Item> items = category.getItems();
            for (int i = 0; i < items.size(); i++) {
                System.out.format("\n\t %s- %s\t\t\t\t(%s)", i + 1, items.get(i).getName(), items.get(i).getPrice());
            }
        }
        if (category.getParent() != null) {
            System.out.println("\n0- Back");
        } else {
            System.out.println("\n0- Finish");
        }
    }

    /**
     * Display the current selected category
     */
    private void printCurrentCategory() {
        printCategory(currentCateogy);
    }

    /**
     * Display the main options of the program
     */
    private void printMainPage() {
        System.out.println("1- New Order");
        System.out.println("2- List of orders");
        System.out.println("3- Exit");
    }

    /**
     * Display the list of all finished orders as a list, to allow the user to
     * check their contents
     *
     */
    private void printListOfOrders() {
        System.out.print("\nList of orders:");
        for (int i = 0; i < orders.size(); i++) {
            System.out.format("\n\t%s- %s", (i + 1), orders.get(i).getDate());
        }
        System.out.println("\n0- back");
    }

    /**
     * Display an order with its items and their prices and the total price at
     * the end it.
     */
    private void printOrder(Order order) {
        System.out.format("\nOrder number: %s\t%s", order.getId(), order.getDate());
        ArrayList<Item> items = order.getItems();
        for (int i = 0; i < order.getItems().size(); i++) {
            System.out.format("\n\t*%s", items.get(i));
        }
        System.out.format("\nTotal Price: " + order.getTotalPrice());
        System.out.println("\n0- back");
    }

    /**
     * Display the extra requests which it might be required for some items
     */
    private void printExtraDemandsList(Item item) {
        System.out.print("\nYou can add one or more of the following:");
        for (int i = 0; i < item.getExtraDemandsList().length; i++) {
            System.out.format("\n\t%s- %s", (i + 1), item.getExtraDemandsList()[i]);
        }
        System.out.println("\n0- back");
    }

    /**
     * Get the user's request from their input
     */
    private int getRequest() {
        System.out.print("\nPlease enter your choose number: ");

        String input = scanner.next();
        try {
            return Integer.parseInt(input);
        } catch (java.lang.NumberFormatException ex) {
            System.out.print("\nError input, only numbers allowed!");
            return getRequest();
        }
    }

    /**
     * Find and correct meaning of the request depending on the current stage.
     */
    private void processRequest(int request) {
        switch (stage) {
            case MAIN_PAGE:
                processMainPage(request);
                break;
            case ORDERS_LIST:
                processOrdersList(request);
                break;
            case ORDER_DETAIL:
                processOrderDetail(request);
                break;
            case ORDER_PROCESSING:
                processOrder(request);
                break;
            case EXTRA_DEMANDS:
                processExtraDemans(request);
            default:
                break;

        }
    }

    /**
     * process a request in case the user chooses a request while the main page
     * is displayed, and now we have three options
     * <p>
     * 1. make a new order
     * <p>
     * 2. list all previous orders
     * <p>
     * 3. exit the application the next stage depends on the user's choice in
     * case of out rage choice, an error is displayed
     */
    private void processMainPage(int request) {
        switch (request) {
            case 1:
                currentCateogy = Menu.getInstance();
                currentOrder = new Order();
                orders.add(currentOrder);
                printCurrentCategory();
                stage = ProcessStage.ORDER_PROCESSING;
                break;
            case 2:
                printListOfOrders();
                stage = ProcessStage.ORDERS_LIST;
                break;
            case 3:
                System.exit(0);
            default:
                System.err.println("\tWrong choice, plaese choose from the displayed range");
        }
    }

    /**
     * process a request in case the user chooses a request while the orders
     * list is displayed.
     * <p>
     * in case the user chooses an order's number then the order details are
     * displayed
     * <p>
     * in case the user chooses 0, the application goes back to the main page
     */
    private void processOrdersList(int request) {
        switch (request) {
            case 0:
                printMainPage();
                stage = ProcessStage.MAIN_PAGE;
                break;
            default:
                if (request > orders.size()) {
                    System.err.println("\tWrong choice, plaese choose from the displayed range");
                } else {
                    printOrder(orders.get(request - 1));
                    stage = ProcessStage.ORDER_DETAIL;
                }
        }
    }

    /**
     * process a request in case the user chooses a request while the order's
     * Details are displayed.
     * <p>
     * the user has only one choice to select: 0 to go back to the main page
     */
    private void processOrderDetail(int request) {
        switch (request) {
            case 0:
                printMainPage();
                stage = ProcessStage.MAIN_PAGE;
                break;
            default:
                System.err.println("\tWrong choice, plaese choose from the displayed range");
        }
    }

    /**
     * process a request while the order is in processing
     * <p>
     * The user can either choose an item to be add the list, or they can
     * navigate to sub-category of the menu.
     * <p>
     * in case the user chooses an item with an extra options, the program goes
     * to the stage of extra demands
     * <p>
     * in all order processing time, 0 goes back
     */
    private void processOrder(int request) {
        if (request == 0) {
            if (currentCateogy == Menu.getInstance()) {
                currentCateogy = null;
                printMainPage();
                stage = ProcessStage.MAIN_PAGE;
            } else {
                currentCateogy = currentCateogy.getParent();
                printCurrentCategory();
            }
        } else {
            if (request <= currentCateogy.getSubCategories().size()) {
                currentCateogy = currentCateogy.getSubCategories().get(request - 1);
                printCurrentCategory();
            } else if (request <= currentCateogy.getItems().size()) {
                currentItem = currentCateogy.getItems().get(request - 1);
                currentOrder.getItems().add(currentItem);
                if (currentItem.hasExtraDemands()) {
                    stage = ProcessStage.EXTRA_DEMANDS;
                    printExtraDemandsList(currentItem);
                } else {
                    System.out.format("\t%s has been added to the order", currentCateogy.getItems().get(request - 1).getName());
                }
            } else {
                System.err.println("\tError: your selection out of range");
            }
        }
    }

    /**
     * process the extra options of a selected item
     * <p>
     * The user can add one or more extra option, but with no duplication.
     * <p>
     * 0 goes back
     */
    private void processExtraDemans(int request) {
        if (request == 0) {
            stage = ProcessStage.ORDER_PROCESSING;
            System.out.format("\t%s has been added to the order\n", currentItem.getName());
            printCurrentCategory();
        } else if (request > currentItem.getExtraDemandsList().length) {
            System.err.println("\tError: your selection out of range");
        } else {
            if (currentItem.getExtraDemands().add(currentItem.getExtraDemandsList()[request - 1])) {
                System.out.format("\t%s has been added", currentItem.getExtraDemandsList()[request - 1]);
            } else {
                System.out.format("\t%s already added, pleas choose another one or back", currentItem.getExtraDemandsList()[request - 1]);
            }
        }
    }

    /**
     * Start the processing system by taking the request from the user and then
     * find the proper way to process
     * <p>
     * Initially, it displays the main options of the system.
     */
    public void start() {
        initiateMenu();
        printMainPage();
        while (true) {
            int request = getRequest();
            processRequest(request);
        }
    }

}

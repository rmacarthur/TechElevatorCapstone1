package com.techelevator;
import com.techelevator.view.Menu;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;
import com.techelevator.view.UsersChange;
public class VendingMachineCLI {
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT};
	private static Logger logger;

	private Menu menu;
	private UsersChange change;
	//private Logger logger;
	private List<Product> list = new ArrayList<>();
	private Scanner userInput = new Scanner(System.in);
	private BigDecimal balance = new BigDecimal("0.00");
	//

	public VendingMachineCLI(Menu menu, UsersChange change, Logger logger) {
		this.menu = menu;
		this.change = change;
		this.logger = logger;
	}
	public void run() {
		BigDecimal totalPrice = new BigDecimal(0.00);
		File newFile = new File("vendingmachine.csv");
		Scanner inputScanner = null;
		try {
			inputScanner = new Scanner(newFile);
			while (inputScanner.hasNextLine()) {
				String lineInput = inputScanner.nextLine();
				String[] wordsOnLine = lineInput.split(Pattern.quote("|"));
				String slot1 = wordsOnLine[0];
				String name1 = wordsOnLine[1];
				BigDecimal price1 = new BigDecimal(wordsOnLine[2]);
				if (wordsOnLine[3].equals("Chip")) {
					list.add(new Chip(slot1, name1, price1, 5));
				} else if (wordsOnLine[3].equals("Drink")) {
					list.add(new Drink(slot1, name1, price1, 5));
				} else if (wordsOnLine[3].equals("Candy")) {
					list.add(new Candy(slot1, name1, price1, 5));
				} else {
					list.add(new Gum(slot1, name1, price1, 5));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		boolean shouldLoop = true;
		while (shouldLoop) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				for (Product item : list) {
					System.out.printf("%5s  %-18s %10.2f %5d\n", item.getSlot(),
							item.getName(), item.getPrice(), item.getQuantity());
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				purchaseMenu();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				shouldLoop = false;
			} else {
				shouldLoop = false;
			}
		}
	}

	public BigDecimal purchaseMenu() {
		final String FEED_MONEY = "Feed Money";
		final String SELECT_PRODUCT = "Select Product";
		final String FINISH_TRANSACTION = "" +
				"Finish Transaction";

		//
		final String[] PURCHASE_MENU_OPTIONS = {FEED_MONEY, SELECT_PRODUCT, FINISH_TRANSACTION};


		BigDecimal price = new BigDecimal("0.0");
		boolean purchaseLoop = true;
		while (purchaseLoop) {
			System.out.print("Current Balance: $" + balance.setScale(2, RoundingMode.HALF_UP));
			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

			switch (choice) {
				case FEED_MONEY:
					addMoney();
					break;
				case SELECT_PRODUCT:
					selectItem();
					break;
				case FINISH_TRANSACTION:
					System.out.println("Thank you for visiting our vending machine, you will find your change below");
					List<Integer> listOfChange = change.makeChange(balance);
					List<String> denominations = change.retrieveCurrencyDenominations();
					System.out.println("Change Returned: ");
					for (int i = 0; i < listOfChange.size(); i++) {
						System.out.print(listOfChange.get(i));
						System.out.print(denominations.get(i) + "\n");
					}
					balance = new BigDecimal(0.00);
					listOfChange.clear();
					purchaseLoop = false;

			}

		}
		return balance;
	}

	public void addMoney() {

		System.out.print("Please enter money in denominations of $1, $5, or $10: ");
		String response = userInput.nextLine();
		if (response.contains("-")) {									// NEED TO PREVENT NEGATIVE INPUT
			System.out.println("Please enter a positive number");
		}


		balance = balance.add(new BigDecimal(response));
		System.out.println(balance);
	//	logger.write("Feed Money: / " + response + " / " + balance);

	}
	public void selectItem() {

		for (Product item : list) {
			System.out.printf("%5s  %-18s %10.2f %5d\n", item.getSlot(),
					item.getName(), item.getPrice(), item.getQuantity());
//			logger.write(item.getName() + item.getPrice() + balance);

		}
		System.out.print("Please enter the alphanumeric code of the item you wish to purchase: ");
		String response = userInput.nextLine();
		boolean isFound = false;
		for (Product slot : list) {
			if (slot.getSlot().equals(response.toUpperCase())) {
				isFound = true;
				if (slot.getQuantity() == 0) {
					System.out.println("Sold out!");
					break;
				}
				if (balance.compareTo(slot.getPrice()) < 0) {
					System.out.println("Please insert more money!");
					break;
				}
				slot.decreaseQuantity();
				balance = balance.subtract(slot.getPrice());
				System.out.print(slot.getName() + " ");
				System.out.print(slot.getPrice() + " ");
				System.out.println(slot.getSound());
				logger.write (logger.getTimeStamp() + "  " + slot.getName() + "  " + slot.getPrice() + " / " + balance);

			}
		}
		if (!isFound)
			System.out.println("Invalid code!");
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		UsersChange change = new UsersChange();
		Logger logger = new Logger("C:\\Users\\Student\\workspace\\mod1-capstone-green-t3\\java\\Log.txt");
		VendingMachineCLI cli = new VendingMachineCLI(menu, change, logger);
		cli.run();

	}
}

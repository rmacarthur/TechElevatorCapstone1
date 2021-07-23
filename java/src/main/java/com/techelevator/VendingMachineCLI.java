package com.techelevator;
import com.techelevator.view.Menu;

import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
public class VendingMachineCLI {
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT};

	private Menu menu;
	private List<Product> list = new ArrayList<>();
	private Scanner userInput = new Scanner(System.in);
	private BigDecimal balance = new BigDecimal("0.00");

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		BigDecimal totalPrice = new BigDecimal(0.00);
		File newFile = new File("C:\\Users\\Student\\workspace\\mod1-capstone-green-t3\\java\\vendingmachine.csv");
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
		public BigDecimal purchaseMenu () {
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
						System.out.println("Select to receive change and exit to main menu");
						// RETURN CHANGE TO USER AND EXIT TO MAIN MENU
					//	purchaseLoop = false;

				}
				// THIS WILL CHANGE TO SOMETHING DEPENDING ON HOW WE BUILD OUT THE PURCHASE SYSTEM
			} return balance;
		}

	public void addMoney() {      // NEED MONEY TO ACCUMULATE AND ADD TO BALANCE

		System.out.print("Please enter money in denominations of $1, $5, or $10: ");
		String response = userInput.nextLine();
		balance = balance.add(new BigDecimal(response));
		System.out.println(balance);
	}

	public void selectItem() {
		for (Product item : list) {
			System.out.printf("%5s  %-18s %10.2f %5d\n", item.getSlot(),
					item.getName(), item.getPrice(), item.getQuantity());
		}

		System.out.print("Please enter the alphanumeric code of the item you wish to purchase: ");
		String response = userInput.nextLine();
		boolean isFound = false;

		for (Product slot : list) {
			if (slot.getSlot().equals(response.toUpperCase())) {
				isFound = true;
				if (slot.getQuantity() == 0){
					System.out.println("Sold out!");
					break;
				}
				if (balance.compareTo(slot.getPrice()) < 0){
					System.out.println("Please insert more money!");
					break;
				}
				slot.decreaseQuantity();
				balance = balance.subtract(slot.getPrice());
				System.out.println(slot.getSound());
			}
		}
		if (!isFound)
			System.out.println("Invalid code!");
	}



	/*	public void paymentMenu (BigDecimal totalPrice){
				System.out.println("Your total cost is: $" + totalPrice.setScale(2, RoundingMode.HALF_UP));
			}*/

		public static void main (String[]args) throws FileNotFoundException {
			Menu menu = new Menu(System.in, System.out);
			VendingMachineCLI cli = new VendingMachineCLI(menu);
			cli.run();
		}
	}

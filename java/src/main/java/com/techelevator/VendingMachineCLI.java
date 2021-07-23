package com.techelevator;
import com.techelevator.view.Menu;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
public class VendingMachineCLI {
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE};

	private Menu menu;
	private List<Product> list = new ArrayList<>();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
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

				/*	System.out.println(lineInput);
					}*/
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			while (true) {
				String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
				if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {


					for (Product item : list) {
//						System.out.println(item.getSlot()+ " " + item.getName());
						//
						System.out.printf("%5s  %-18s %10.2f %5d\n", item.getSlot(),
								item.getName(), item.getPrice(), item.getQuantity());
					}

				} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				}    // do purchase
			}
		}

		public static void main (String[]args) throws FileNotFoundException {
			Menu menu = new Menu(System.in, System.out);
			VendingMachineCLI cli = new VendingMachineCLI(menu);
			cli.run();
		}
	}

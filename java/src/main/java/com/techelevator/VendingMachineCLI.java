package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

				File newFile = new File("vendingmachine.csv");
				Scanner inputScanner = null;
				try {
					inputScanner = new Scanner(newFile);

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}


				List<String> productList = new ArrayList<>();

				while (inputScanner.hasNextLine()) {
					String lineInput = inputScanner.nextLine();
					String[] wordsOnLine = lineInput.split(Pattern.quote("|"));
					productList.add(inputScanner.nextLine());

					for (String line : wordsOnLine) {
						System.out.println(lineInput);
					}	}
					// display vending machine items
				} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
					// do purchase
				} }
		}

	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();

		}
}

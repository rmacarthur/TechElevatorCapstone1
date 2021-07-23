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
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE};
	private Menu menu;
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				File newFile = new File("C:\\Users\\Student\\workspace\\mod1-capstone-green-t3\\java\\vendingmachine.csv");
				Scanner inputScanner = null;
				try {
					inputScanner = new Scanner(newFile);
					while (inputScanner.hasNextLine()) {
						String lineInput = inputScanner.nextLine();
						String[] wordsOnLine = lineInput.split(Pattern.quote("|"));
               /* String slot1 = wordsOnLine[0];
                  String name1 = wordsOnLine[1];
                  String price1 = wordsOnLine[2];
                  String type1 = wordsOnLine[3];
*/             // *************** WE ARE HERE TRYING TO FIGURE OUT THIS DUMB ARRAYLIST
/*                List<List> trialList = new ArrayList();
                  for (String wordsAsString: wordsOnLine) {
                     String[] moreWordsOnLine = wordsAsString.split(",");
                     String slot = moreWordsOnLine[0].trim();
                     String name = moreWordsOnLine[1].trim();
                     String price = moreWordsOnLine[2].trim();
                     String type = moreWordsOnLine[3].trim();
                     List triallist1 = new ArrayList<>();
                     trialList.add(triallist1);
                  }*/
					}
            /* System.out.println(lineInput);
               }*/
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} break;
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
			}    // do purchase
		}
	}
	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
package com.techelevator.view;

import java.io.*;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);

			File newFile = new File("vendingmachine.csv");
			Scanner inputScanner = null;
			try {
				inputScanner = new Scanner(newFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			while (inputScanner.hasNextLine()) {
				String lineInput = inputScanner.nextLine();
				String [] wordsOnline = lineInput.split("");

				for (String word : wordsOnline) {
					System.out.println(word + ">>>");
				}
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
}}

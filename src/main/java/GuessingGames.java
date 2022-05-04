import Exceptions.InvalidUserChoiceException;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class GuessingGames {

    private static Scanner userKeyboardDevice = new Scanner(System.in);

    /**
     * Menu options
     */
    private static final String NUMBER_GAME = "Guess my number";
    private static final String EXIT = "Exit";

    /**
     * Array of menu  options
     */
    private static final String[] mainMenuOptions = { NUMBER_GAME,
            EXIT
    };

    /**
     * Application controller
     */
    public void run() {
        startOfApplication();
        String userChoice;
        boolean shouldLoop = true;

        while (shouldLoop) {
            try {
                userChoice = displayMenuAndGetChoice();
                System.out.println("You chose: " + userChoice);

                switch(userChoice) {

                    case NUMBER_GAME: {
                        numberGuessingGame();
                        break;
                    }
                    case EXIT: {
                        shouldLoop = false;
                        break;
                    }
                    default: {
                        throw new InvalidUserChoiceException("Invalid menu choice: " + userChoice);
                    }

                }

            } catch (InvalidUserChoiceException exception) {
                System.out.println("\n Invalid menu choice, please select again.");
            }
        }

        endOfApplication();

    }

    /**
     * Menu choice methods
     */
    public void numberGuessingGame() {
        int numberOfGuesses = 0;
        int userInt;
        String yesOrNo;
        String userGuess;
        Random random = new Random();

        int targetNumber = random.nextInt(101);

        while (numberOfGuesses < 5) {
            System.out.println("\nNumber of Guesses Remaining:" + (5-numberOfGuesses));
            System.out.println("Guess a number between 0-100:");
            userGuess = userKeyboardDevice.nextLine();

            try {
                userInt = Integer.parseInt(userGuess);
                numberOfGuesses ++;
                if (userInt > targetNumber) {
                    System.out.println(userGuess + " is too high!");

                } else {
                    if (userInt == targetNumber) {
                        System.out.println("Correct! You won in " + numberOfGuesses + " guesses!");
                    } else {
                        System.out.printf(userGuess + " is too low!");

                    }
                }
            } catch (NumberFormatException exception) {
                System.out.println("\n Invalid input, please enter an integer between 0-100");
            }
        }

        System.out.println("Defeat! The number was: " + targetNumber);

        System.out.println("Would you like to play again? (y/n)");
        yesOrNo = userKeyboardDevice.nextLine();

        if (yesOrNo.toLowerCase().startsWith("y")) {
            numberGuessingGame();
        }

    }


    /**
     * Helper methods
     */
    public void  startOfApplication() {
        System.out.println("Let's play a guessing game!");
    }

    public String displayMenuAndGetChoice() {
        int choice = -1;
        System.out.println("Select a game:");

        for (int i = 0; i < mainMenuOptions.length; i++) {
            System.out.println(i + 1 + ". " + mainMenuOptions[i]);
        }

        System.out.println("\n Enter choice:");
        try {
            choice = Integer.parseInt(userKeyboardDevice.nextLine());
            return mainMenuOptions[choice -1];
        } catch (NumberFormatException exception) {
            throw new InvalidUserChoiceException("Invalid menu option " + choice + "entered.");
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new InvalidUserChoiceException("Invalid menu option " + choice + "entered");
        }
    }

    public void endOfApplication() {
        System.out.println("Thank you for playing Guessing Games!");
    }

}

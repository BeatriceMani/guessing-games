import Exceptions.InvalidUserChoiceException;

import java.sql.SQLOutput;
import java.util.Scanner;

public class GuessingGames {

    private static Scanner userKeyboardDevice = new Scanner(System.in);

    /**
     * Menu options
     */
    private static final String NUMBER_GAME = "Guess my number";
    private static final String EXIT = "Exit Guessing Games Application";

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

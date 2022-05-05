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
    private static final String COIN_TOSS = "Flip some coins";
    private static final String EXIT = "Exit";

    /**
     * Array of menu  options
     */
    private static final String[] mainMenuOptions = { NUMBER_GAME,
            COIN_TOSS,
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

                    case COIN_TOSS: {
                        flipACoin();
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

    public void flipACoin() {

        String userInput;
        int coins = 0;
        int roundsPlayed = 1;
        int correctGuesses = 0;

        System.out.println("Gathering coins...");
        System.out.println("How many coins would you like to flip?");
        System.out.println("Enter a number 0-10:");

        userInput = userKeyboardDevice.nextLine();

        try{
            coins = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println("\n Invalid input, please try again.");
            flipACoin();
        }

        while (roundsPlayed <= coins) {
            Random random = new Random();
            System.out.println("\n Round #" + roundsPlayed);
            Boolean theCoin = random.nextBoolean();


            String theCoinString;

            if (theCoin) {
                theCoinString = "heads";
            } else {
                theCoinString = "tails";
            }

            System.out.println("Will it be heads or tails?");
            userInput = userKeyboardDevice.nextLine().trim();

            if ((userInput.startsWith("h") && theCoin) || (userInput.startsWith("t") && !theCoin) ) {
                System.out.println("Correct!");
                correctGuesses ++;
            } else {
                System.out.println("Incorrect!");
            }

            System.out.println("\n You guessed " + userInput +". The coin was " + theCoinString);

            if (roundsPlayed == coins) {
                System.out.println("\n You won " + correctGuesses + " out of " + coins + " rounds.");
            }

            roundsPlayed ++;
        }

        System.out.println("\n Would you like to play again?" );

        userInput = userKeyboardDevice.nextLine();

        if (userInput.startsWith("y")) {
            flipACoin();
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

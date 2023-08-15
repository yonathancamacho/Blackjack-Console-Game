package ca.sheridancollege.project;

import java.util.Scanner;

/**
 * The main entry point of the Blackjack game application.
 * 
 * @author Yonathan Camacho, 2023
 */
public class Main {

    /** A scanner object to handle user input throughout the application. */
    public static final Scanner SCANNER = new Scanner(System.in);

    /** Player manager to manage players and their respective operations. */
    public static final PlayerManager playerManager = new PlayerManager();

    /**
     * The main method which drives the game.
     * 
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("\nWelcome to Blackjack!");

        // Main game loop to continually provide user options until they choose to exit.
        while (true) {
            printPlayers(); // Display the current list of players
            System.out.println("Options:");
            System.out.println("1. Add New Player");
            System.out.println("2. Start Game");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = SCANNER.nextInt();
            SCANNER.nextLine(); // Consume the newline character after nextInt
            clearScreen(); // Clear the terminal/console screen for clean display

            // Handle user choice
            switch (choice) {
                case 1:
                    System.out.print("Enter player name: ");
                    String playerName = SCANNER.nextLine();
                    clearScreen();
                    if (playerManager.addPlayer(playerName)) {
                        System.out.println(playerName + " has been added.");
                    }
                    break;
                case 2:
                    if (playerManager.startGame()) {
                        for (BlackjackPlayer player : playerManager.getPlayers()) {
                            player.resetHand();
                        }
                        BlackjackGame game = new BlackjackGame("Blackjack", playerManager.getPlayers());
                        game.play(); // Start the game
                    }
                    break;
                case 3:
                    System.out.println("Thanks for playing!");
                    SCANNER.close(); // Close the scanner object
                    System.exit(0); // Terminate the application
                default:
                    System.out.println("Invalid choice!"); // Handle invalid user input
            }
        }
    }

    /**
     * Displays the list of current players.
     * If no players have been added, it indicates that instead.
     */
    private static void printPlayers() {
        if (playerManager.getPlayers().isEmpty()) {
            System.out.println("\nNo players added yet.\n");
        } else {
            System.out.println("\nList of Players:");
            for (BlackjackPlayer player : playerManager.getPlayers()) {
                System.out.println(" -" + player.getName());
            }
            System.out.println();
        }
    }

    /**
     * Clears the terminal or console screen to provide a clean slate for further
     * interactions.
     */
    public static void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

//    public static void clearScreen() {
//        try {
//            if (System.getProperty("os.name").contains("Windows")) {
//                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//            } else {
//                System.out.print("\033[H\033[2J");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

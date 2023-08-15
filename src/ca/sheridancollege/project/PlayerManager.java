package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * Manages the players for a game of Blackjack. Ensures unique player names
 * and enforces a maximum number of players.
 * 
 * @author Yonathan Camacho, 2023
 */
public class PlayerManager {

    /** List of players participating in the game. */
    private final ArrayList<BlackjackPlayer> players;

    /** Maximum number of players allowed in a game. */
    private static final int MAX_PLAYERS = 4; // Can change this based on your requirements.

    /**
     * Constructs a new PlayerManager with an empty list of players.
     */
    public PlayerManager() {
        this.players = new ArrayList<>();
    }

    /**
     * Adds a new player to the game.
     * 
     * @param name The name of the new player.
     * @return true if the player was successfully added, false otherwise.
     */
    public boolean addPlayer(String name) {
        if (players.size() >= MAX_PLAYERS) {
            System.out.println("Maximum number of players reached!");
            return false;
        }

        for (BlackjackPlayer player : players) {
            if (player.getName().equals(name)) {
                System.out.println("Player name already exists!");
                return false;
            }
        }

        players.add(new BlackjackPlayer(name));
        return true;
    }

    /**
     * Checks if the game can be started based on the number of players.
     * 
     * @return true if the game can be started, false otherwise.
     */
    public boolean startGame() {
        if (players.size() < 1) {
            System.out.println("At least one player needs to be added to start the game!");
            return false;
        }

        return true;
    }

    /**
     * Retrieves the list of players in the game.
     * 
     * @return An ArrayList containing the players.
     */
    public ArrayList<BlackjackPlayer> getPlayers() {
        return players;
    }
}

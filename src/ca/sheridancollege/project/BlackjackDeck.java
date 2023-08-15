package ca.sheridancollege.project;

/**
 * Represents a standard deck of cards specifically for a game of Blackjack.
 * This class extends GroupOfCards to provide Blackjack-specific deck behavior.
 * 
 * @author Yonathan Camacho, 2023
 */
public class BlackjackDeck extends GroupOfCards {

    /**
     * Constructs a new standard Blackjack deck with 52 cards.
     */
    public BlackjackDeck() {
        super(52);
        initializeDeck();
    }

    /**
     * Initializes the deck with 52 standard Blackjack cards.
     */
    private void initializeDeck() {
        for (Suit suit : Suit.values()) {
            for (int i = 1; i <= 13; i++) {
                BlackjackCard card = new BlackjackCard(suit, i);
                getCards().add(card);
            }
        }
    }

    /**
     * Draws a card from the top of the deck.
     *
     * @return The top card from the deck, or null if the deck is empty.
     */
    public BlackjackCard drawCard() {
        if (!getCards().isEmpty()) {
            return (BlackjackCard) getCards().remove(0);
        }
        return null;
    }
}

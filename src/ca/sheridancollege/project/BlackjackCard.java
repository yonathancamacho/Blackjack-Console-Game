package ca.sheridancollege.project;

/**
 * Represents a card in a game of Blackjack. This class extends the general Card
 * class to provide
 * specific properties and behavior related to Blackjack.
 * 
 * @author Yonathan Camacho, 2023
 */
public class BlackjackCard extends Card {

    // Enum representing the card's suit (HEARTS, DIAMONDS, CLUBS, SPADES)
    private Suit suit;

    // Numeric value of the card (1-13)
    private int value;

    // Display representation of the card's value (e.g., "A", "J", "Q", "K", or
    // "2-10")
    private String displayValue;

    /**
     * Constructs a new Blackjack card with the specified suit and value.
     *
     * @param suit  The suit of the card.
     * @param value The numeric value of the card (1-13).
     */
    public BlackjackCard(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
        setDisplayValue(value);
    }

    /**
     * Sets the display value based on the card's numeric value.
     *
     * @param value The numeric value of the card.
     */
    private void setDisplayValue(int value) {
        switch (value) {
            case 1:
                this.displayValue = "A";
                break;
            case 11:
                this.displayValue = "J";
                break;
            case 12:
                this.displayValue = "Q";
                break;
            case 13:
                this.displayValue = "K";
                break;
            default:
                this.displayValue = String.valueOf(value);
        }
    }

    /**
     * Provides a string representation of the card, e.g., "A of HEARTS".
     *
     * @return String representation of the card.
     */
    @Override
    public String toString() {
        return displayValue + " of " + suit;
    }

    // Getter for the card's suit
    public Suit getSuit() {
        return suit;
    }

    // Setter for the card's suit
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    // Getter for the card's numeric value
    public int getValue() {
        return value;
    }

    /**
     * Sets the card's numeric value and updates its display value accordingly.
     *
     * @param value The numeric value to set.
     */
    public void setValue(int value) {
        this.value = value;
        setDisplayValue(value);
    }

    // Getter for the card's display value
    public String getDisplayValue() {
        return displayValue;
    }
}

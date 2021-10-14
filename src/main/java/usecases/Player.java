package usecases;

import entities.Card;
import entities.Hand;

public class Player {
    private Hand hand;
    private String name;

    public Player(String name) {
        this.hand = new Hand();
        this.name = name;
    }

    public void addToHand(Card card) {
        this.hand.addCard(card);
    }

    public void removeFromHand(Card card) {
        this.hand.removeCard();
    }

    // Return this player's hand
    public Hand getHand() {
        return this.hand;
    }
}
package usecases;

import controllers.InOut;
import entities.Card;
import entities.Hand;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CrazyEights extends Game{
    private final Stack<Card> playingField;
    private final InOut controller;

    // Creates a game of CrazyEights, numPlayers must be less than 6
    public CrazyEights(int numPlayers, InOut controller) {
        super(numPlayers);
        this.controller = controller;
        this.currPlayerIndex = 0;
        this.playingField = new Stack<>();
        this.deck.shuffle();
        for (Player player : this.players) {
            for (int i=0; i < 5; i++) {
                player.addToHand(this.deck.drawCard());
            }
        }
        this.playingField.add(this.deck.drawCard());
    }

    @Override
    public void startGame() {
        while (!checkWin()) {
            this.currPlayer = this.players[this.currPlayerIndex];
            Card card = null;
            String crd;
            do {
                this.controller.sendOutput("Top card: " + this.playingField.peek());
                this.controller.sendOutput(this.currPlayer.getName() + "'s Hand: " + this.currPlayer.getHand().toString());
                if (!hasValidMove(currPlayer.getHand())){
                    card = null;
                    this.controller.sendOutput("Card Drawn from Deck because there are no cards to play");
                }
                else if (!controller.drawCard()) {
                    crd = this.controller.getCard();
                    card = new Card(crd.substring(1), crd.charAt(0));
                }
            } while (card != null && !checkMove(card));
            if (card == null) {
                currPlayer.addToHand(deck.drawCard());
            } else {
                makeMove(card);
            }
            this.currPlayerIndex = (this.currPlayerIndex + 1) % this.players.length;
            this.controller.sendOutput("");
        }
    }

    @Override
    public boolean checkMove(Card card) {
        int ind = currPlayer.getHand().getCards().indexOf(card);
        if (ind == -1) {
            return false;
        } else {
            return (card.getSuit() == this.playingField.peek().getSuit()) || card.getRank().equals(this.playingField.peek().getRank());
        }
    }

    public boolean hasValidMove(Hand hand){
        List<Card> cards = hand.getCards();
        for (Card card : cards){
            if (card.getSuit() == this.playingField.peek().getSuit() || card.getRank().equals(this.playingField.peek().getRank())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void makeMove(Card card) {
        this.playingField.add(card);
        this.currPlayer.removeFromHand(card);
    }

    @Override
    public boolean checkWin() {
        return currPlayer.getHand().getSize() == 0;
    }
}

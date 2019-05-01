package SwingGames.BlackJack;

import java.util.ArrayList;

//Deck, follows a stack data structure
public class CardDeck {

    private int deckSize;
    private ArrayList<Card> deck;

    public CardDeck() {
        this.deck = new ArrayList<>();

        for (Suite suite : Suite.values()) {



        }


    }

    public CardDeck(int deckSize) {
        this.deckSize = deckSize;
    }





}

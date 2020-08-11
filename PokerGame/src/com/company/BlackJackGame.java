package com.company;

import java.util.List;

public class BlackJackGame {
    private CardStuck cardStuck = new CardStuck();
    private List<Card> playerCards;

    public void Begin() {

    }
    private void deliveryPlayerCard() {
        playerCards = cardStuck.takeCards(2);
    }

    private void deliveryDealerCard() {
        playerCards = cardStuck.takeCards(2);
    }
}

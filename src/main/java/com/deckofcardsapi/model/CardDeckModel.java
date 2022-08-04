package com.deckofcardsapi.model;

import com.deckofcardsapi.CardPiles;
import com.deckofcardsapi.PlayingCard;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDeckModel {
    public boolean success;

    @JsonProperty("deck_id")
    public String deckID;

    @JsonProperty("remaining")
    public int numberOfCardsRemaining;

    public boolean shuffled;

    @JsonProperty("error")
    public String errorMessage;

    public List<PlayingCard> getDrawnCards() {
        return drawnCards;
    }

    @JsonProperty("cards")
    List<PlayingCard> drawnCards = new ArrayList<>();

    public CardPiles piles;


    public String cardsForMyHand;
    public String cardsForDiscard;

    public String getCardsForMyHand() {
        return cardsForMyHand;
    }

    public String getCardsForDiscard() {
        return cardsForDiscard;
    }

    public String getDeckID() {
        return deckID;
    }

    public int getNumberOfCardsRemaining() {
        return numberOfCardsRemaining;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public boolean isSuccessful() {
        return success;
    }

}

package com.deckofcardsapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pile {
    @JsonProperty("remaining")
    public int noOfCardsInPile;

    List<PlayingCard> cards = new ArrayList<>();
}

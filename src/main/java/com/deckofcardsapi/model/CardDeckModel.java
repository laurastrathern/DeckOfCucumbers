package com.deckofcardsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDeckModel {
    //public boolean success;


    @JsonProperty("remaining")
    public int numberOfCardsRemaining;

    @JsonProperty("deck_id")
    public String deckID;
}

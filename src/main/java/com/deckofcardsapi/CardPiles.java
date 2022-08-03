package com.deckofcardsapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardPiles {
    public Pile myHand;

    public Pile discard;
}

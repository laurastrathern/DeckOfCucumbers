package com.deckofcardsapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayingCard {
    public String code;
    public String image;
    public String value;
    public String suit;
}

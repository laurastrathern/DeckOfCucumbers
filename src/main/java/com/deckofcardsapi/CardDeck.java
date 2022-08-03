package com.deckofcardsapi;

import com.deckofcardsapi.model.CardDeckModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import io.restassured.RestAssured;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDeck {

    public final String BASE_URL = "https://deckofcardsapi.com/api/deck/";
    private ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY).
            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    public boolean success;

    @JsonProperty("deck_id")
    public String deckID;

    @JsonProperty("remaining")
    public int numberOfCardsRemaining;

    public boolean shuffled;

    @JsonProperty("error")
    public String errorMessage;

    @JsonProperty("cards")
    List<PlayingCard> drawnCards = new ArrayList<>();

    public CardPiles piles;

    public List drawnCardsCodeList = new ArrayList();
    public String cardsForMyHand;
    public String cardsForDiscard;

    public String getCardsForMyHand() {
        return cardsForMyHand;
    }

    public String getCardsForDiscard() {
        return cardsForDiscard;
    }

    public CardDeck () {

    }

    public CardDeckModel createCardDeck (int decks, boolean jokersEnabled) {
        Response response = RestAssured.get(BASE_URL + "new/?deck_count=" + decks + "&jokers_enabled=" + jokersEnabled);
        try {
            return objectMapper.readValue(response.getBody().asInputStream(), CardDeckModel.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //return response.getBody().jsonPath().getObject("$", CardDeck.class);
    }

    public CardDeckModel shuffleDeck() {
        return RestAssured.get(BASE_URL + this.getDeckID() + "/shuffle/?remaining=false")
                .as(CardDeckModel.class);
       /* try {
            return objectMapper.readValue(response.getBody().asInputStream(), CardDeckModel.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    public CardDeckModel drawFromDeck(int drawCount) {
        Response response = RestAssured.get(BASE_URL + this.getDeckID() + "/draw/?count=" + drawCount);
               // .as(CardDeckModel.class);
          try {
            return objectMapper.readValue(response.getBody().asInputStream(), CardDeckModel.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public CardDeck createPileFromDrawnCards(int drawCount, int addToMyHandCount) {
        this.drawnCardsCodeList = this.getCardCodeList(drawCount);
        this.cardsForMyHand = this.setPileList(addToMyHandCount);
        return RestAssured.get(BASE_URL + this.getDeckID() + "/pile/myHand/add/?cards=" + this.getCardsForMyHand())
                .as(CardDeck.class);
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

    public List getCardCodeList(int drawCount) {
        List cards = new ArrayList();

        for (int i = 0; i < drawCount; i++) {
            cards.add(this.drawnCards.get(i).code);
        }

        System.out.println("Cards drawn: " + cards);
        return cards;
    }

    public String setPileList(int addToPileCount) {
        String cards = "";

        for (int i = 0; i < addToPileCount; i++) {
            cards = cards + this.drawnCardsCodeList.get(i) + ",";
        }
        for (int i = 0; i < addToPileCount; i++) {
            this.drawnCardsCodeList.remove(0);
        }
        System.out.println("Cards added to pile: " + cards);
        System.out.println("No of cards remaining in draw pile: " + this.drawnCardsCodeList.toArray().length);
        return cards;
    }
}

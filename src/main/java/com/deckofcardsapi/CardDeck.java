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

    public List drawnCardsCodeList = new ArrayList();


    public CardDeck () {

    }

    public CardDeckModel createCardDeck (int decks, boolean jokersEnabled) {
        Response response = RestAssured.get(BASE_URL + "new/?deck_count=" + decks + "&jokers_enabled=" + jokersEnabled);
        try {
            return objectMapper.readValue(response.getBody().asInputStream(), CardDeckModel.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CardDeckModel shuffleDeck(CardDeckModel deck) {
        Response response = RestAssured.get(BASE_URL + deck.getDeckID() + "/shuffle/?remaining=false");
        try {
            return objectMapper.readValue(response.getBody().asInputStream(), CardDeckModel.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CardDeckModel drawFromDeck(int drawCount, CardDeckModel deckModel) {
        Response response = RestAssured.get(BASE_URL + deckModel.getDeckID() + "/draw/?count=" + drawCount);
          try {
            return objectMapper.readValue(response.getBody().asInputStream(), CardDeckModel.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public CardDeckModel createPileFromDrawnCards(int addToMyHandCount, CardDeckModel deckModel) {
        deckModel.cardsForMyHand = this.setPileList(addToMyHandCount);
        Response response = RestAssured.get(BASE_URL + deckModel.getDeckID() + "/pile/myHand/add/?cards=" + deckModel.getCardsForMyHand());
        try {
            return objectMapper.readValue(response.getBody().asInputStream(), CardDeckModel.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List getCardCodeList(CardDeckModel deckModel) {
        List cards = new ArrayList();

        for (int i = 0; i < deckModel.getDrawnCards().size(); i++) {
            cards.add(deckModel.getDrawnCards().get(i).code);
        }

        System.out.println("Cards drawn: " + cards);
        return cards;
    }

    public void setDrawnCardsCodeList(List drawnCardsCodeList) {
        this.drawnCardsCodeList = drawnCardsCodeList;
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

package com.deckofcardsapi;

import com.deckofcardsapi.model.CardDeckModel;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class DrawingCardsStepDefinitions {

    private CardDeckModel deckModel;

    private CardDeck deck;


    @Given("^there are (\\d+) decks of cards with jokers (.*)")
    public void there_are_decks_of_cards_with_jokers(Integer decks, String jokers) {
        boolean jokersEnabled = jokers.equalsIgnoreCase("included");
        deckModel = new CardDeckModel();
        deck = new CardDeck();
        deckModel = deck.createCardDeck(decks, jokersEnabled);
    }

    @When("{int} cards are drawn")
    public void cards_are_drawn(Integer drawCount) {
        deckModel = deck.drawFromDeck(drawCount, deckModel);
        deck.setDrawnCardsCodeList(deck.getCardCodeList(deckModel));
    }

    @Then("the deck will have {int} cards remaining.")
    public void the_deck_will_have_cards_remaining(Integer remainingCards) {
        assertThat(deckModel.getNumberOfCardsRemaining()).isEqualTo(remainingCards);
    }

    @When("{int} of the drawn cards are placed in my hand")
    public void of_the_drawn_cards_are_placed_in_my_hand(Integer cards){
        deckModel = deck.createPileFromDrawnCards(cards, deckModel);
    }

    @Then("my hand will have {int} cards in it.")
    public void my_hand_will_have_remaining_cards_in_it(Integer cards) {
        assertThat(deckModel.piles.myHand.noOfCardsInPile).isEqualTo(cards);
    }

}

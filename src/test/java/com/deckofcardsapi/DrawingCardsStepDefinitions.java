package com.deckofcardsapi;

import com.deckofcardsapi.model.CardDeckModel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class DrawingCardsStepDefinitions {

    private CardDeckModel deckModel;

    private CardDeck deck;

    @Given("^there are (\\d+) decks of cards with jokers (.*)")
    public void there_are_decks_of_cards_with_jokers(Integer decks, String jokers) {
        boolean jokersEnabled = jokers=="included";
        deckModel = new CardDeckModel();
        deck = new CardDeck();
        deckModel = deck.createCardDeck(decks, jokersEnabled);
    }

    @When("{int} cards are drawn")
    public void cards_are_drawn(Integer drawCount) {
        deckModel = deck.drawFromDeck(drawCount);
    }

    @Then("the deck will have {int} cards remaining")
    public void the_deck_will_have_cards_remaining(Integer remainingCards) {
        assertThat(deck.getNumberOfCardsRemaining()).isEqualTo(remainingCards);
    }

    @Then("the deck will have {int} cards remaining.")
    public void the_deck_will_have_cards_remaining(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
//
//    private int remainingDrinks;
//    @Given("There are {int} hot chocolates and {int} customers")
//    public void there_are_hot_chocolates_and_customers(Integer drinks, Integer customers) {
//        remainingDrinks = drinks;
//    }
//    @When("^Laura orders (\\d+) hot chocolate")
//    public void laura_orders_hot_chocolate(Integer drinks) {
//        remainingDrinks = remainingDrinks - drinks;
//    }
//    @Then("there are {int} hot chocolates left.")
//    public void there_are_hot_chocolates_left(Integer drinksLeftOver) {
//        assertThat(remainingDrinks).isEqualTo(drinksLeftOver);
//    }
}

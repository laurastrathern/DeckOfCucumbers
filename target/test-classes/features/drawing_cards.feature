Feature: Drawing Cards

  Drawing cards from the deck reduces the number of cards in the deck.

  Scenario Outline: Creating new decks of cards
    Given the deck of cards api exists
    When there are <Decks> decks of cards with jokers <Jokers>
    Then the deck will have <Cards> cards.

    Examples:

  Scenario Outline: Drawing cards from new decks of cards
    Given there are <Decks> decks of cards with jokers <Jokers>
    When <Drawn Cards> cards are drawn
    Then the deck will have <Remaining Cards> cards remaining.

    Examples:
      | Decks | Jokers   | Drawn Cards | Remaining Cards |
      | 1     | excluded | 10          | 42              |
      | 2     | included | 10          | 98              |
      | 4     | excluded | 10          | 198             |
#
#  Scenario Outline:
#    Given There are <Drinks> hot chocolates and <Customers> customers
#    When Laura orders <Order> hot chocolate
#    Then there are <RemainingDrinks> hot chocolates left.
#
#    Examples:
#    | Drinks | Customers | Order | RemainingDrinks |
#    | 3      | 1         | 1     | 2               |
#    | 2      | 1         | 2     | 0               |

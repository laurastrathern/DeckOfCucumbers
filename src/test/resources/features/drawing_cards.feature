Feature: Drawing Cards

  Drawing cards from the deck reduces the number of cards in the deck.

  Scenario Outline: Creating new decks of cards
    Given there are <Decks> decks of cards with jokers <Jokers>
    Then the deck will have <Cards> cards remaining.

    Examples:
      | Decks | Jokers   | Cards |
      | 1     | excluded | 52    |
      | 2     | included | 108   |
      | 4     | excluded | 208   |

  Scenario Outline: Drawing cards from new decks
    Given there are <Decks> decks of cards with jokers <Jokers>
    When <Drawn Cards> cards are drawn
    Then the deck will have <Remaining Cards> cards remaining.

    Examples:
      | Decks | Jokers   | Drawn Cards | Remaining Cards |
      | 1     | excluded | 10          | 42              |
      | 2     | included | 10          | 98              |
      | 4     | excluded | 10          | 198             |

  Scenario Outline: Adding cards to my hand from the drawn cards
    Given there are <Decks> decks of cards with jokers <Jokers>
    And <Drawn Cards> cards are drawn
    When <Hand Cards> of the drawn cards are placed in my hand
    Then my hand will have <Hand Cards> cards in it.

    Examples:
      | Decks | Jokers   | Drawn Cards | Hand Cards |
      | 1     | excluded | 10          | 1          |
      | 2     | included | 10          | 5          |
      | 4     | excluded | 10          | 10         |
      | 1     | excluded | 10          | 10          |
      | 2     | excluded | 10          | 1          |


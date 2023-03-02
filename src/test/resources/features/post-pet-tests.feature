Feature: Post new pet to the store

  Scenario: post new pet
    When a new pet of type kitty with status available is posted
    Then the new pet is correctly listed in the pet store
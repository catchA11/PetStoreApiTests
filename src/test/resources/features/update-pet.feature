Feature: update pet by id

  Scenario: Update a pet status by id
    Given a new pet of type parrot with status available is posted
    When the pet status is updated to sold
    Then the new pet status is sold

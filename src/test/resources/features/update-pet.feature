Feature: update pet by id

  @CleanUp
  Scenario: Update a pet status by id
    Given a new pet of type parrot with status available is posted
    When the pet status is updated to sold
    Then the new pet status is sold

  @CleanUp
  Scenario Outline: Add tag to existing pet
    Given a new pet of type parrot with status available is posted
    When a tag with id <id> and name <name> is added to the pet
    Then the pet is updated wih a tag with id <id> and name <name>
    Examples:
      | id    | name |
      | 12345 | test |

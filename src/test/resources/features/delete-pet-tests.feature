Feature: Delete pet

  Scenario: delete pet by id
    Given a pet exists in the pet store
    When the pet is deleted by id
    Then the pet is not listed in the pet store
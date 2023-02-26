Feature: Get Pet By Status

  Scenario: verify number of available doggies
    When a request is sent to the pet store of available pets
    Then the number of pets of type doggie found is 10

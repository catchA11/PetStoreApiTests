Feature: Get Pet By Status

  Scenario Outline: verify number of <status> pets of type <pet type>
    When a request is sent to the pet store of <status> pets
    Then the number of pets of type <pet type> found is <expected number>
    Examples:
      | status    | pet type | expected number |
      | available | doggie   | 10              |
      | available | kitty    | 1               |
      | sold      | doggie   | 1               |



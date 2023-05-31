Feature: Add product to basket

  Scenario Outline: Add a product to the basket and verify
    Given the user is on the  Nespresso homepage
    When  the user selects <article> from the <type> menu
    And   adds it to the basket
    Then  the product  <productTitle>  should be listed in the basket <productName>
    When  the user proceeds to checkout
    Then  the login page displays <productName>


    Examples:
      | type     | article               | productTitle                       | productName           |
      | machines | creatista-pro         | creatista-pro                      | Creatista Pro         |
      | capsules | jamaica-blue-mountain | jamaica-blue-mountain-capsule-cafe | Jamaica Blue Mountain |

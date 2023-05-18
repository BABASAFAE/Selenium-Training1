Feature: Add product into basket

  The user wants to add an article to the basket and verify its presence

  Scenario Outline: Add a product to the basket and verify
    Given the user is on the homepage
    When the user hovers over the <type> menu
    And the user clicks on the submenu
    And the user clicks on the article <article>
    And The user clicks on the Add to Basket button
    And the user selects the quantity
    And The user clicks on the Your Basket button
    Then the product should be listed in the basket <productTitle> and product name <productName>
    And The user clicks on Proceed to Checkout button
    Then The login page displays <productName>
    Examples:
      | type     | article               | productTitle                       | productName           |
      | machines | creatista-pro         | creatista-pro                      | Creatista Pro         |
      | capsules | jamaica-blue-mountain | jamaica-blue-mountain-capsule-cafe | Jamaica Blue Mountain |

Feature: Login Feature
  Verify if user is able to Login in the site
  Scenario Outline: login with incorrect credentials
    Given user is on homepage
    And user navigates to Login Page
    When user fill username <username> and password <password>
    Then Error message is displayed
    Examples:
      | username                   | password   |  |
      | "accountexist@yopmail.com" | "Test"     |  |
      | "test@yopmail.com"         | "Test1234" |  |
    Scenario: Login with correct credentials
      Given user is on homepage
      And user navigates to Login Page
      When user fill username "correct@yopmail.com" and password "Test1234"
      Then Error message is displayed

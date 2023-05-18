Feature: Login Functionality
@Ready
  Scenario Outline: login with incorrect credentials
    Given the user is on the homepage
    When user fill username <username> and password <password>
    Then Error message is displayed
    Examples:
      | username             | password     |  |
      | safae.baba@ump.ac.ma | Safae12345@  |  |
      | test@yopmail.com     | Test1234     |  |
      | safae.baba@ump.ac.ma | Safae123456@ |  |
      | safae.baba@ump.ac.ma | Safae123456@ |  |

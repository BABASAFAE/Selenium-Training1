Feature: Hello world feature
  Background: 
    * url 'https://jsonplaceholder.typicode.com'
  Scenario: Get request
    Given path  'users'
    When method GET
    Then status 404

  Scenario: Run a sample Get API
    Given urle is 'https://reqres.in/api/users?page=2'
    When method GET
    Then status 200
    And print response


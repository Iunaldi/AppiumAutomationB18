Feature: As etsy user, I want to be able to login with credentials, via google and facebook accounts

  Scenario: Login with etsy credentials
    Given user click on get started button
    When user logs in with etsy credentials
    Then user verifies that etsy logo is displayed
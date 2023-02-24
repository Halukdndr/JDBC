@wip
  Feature: Home page feature

    Scenario: Search for Cloud from home page and navigate to 'Automate Your Life with Cypress' page
      Given the user is on the home page
      When the user click on search on home page
      And enters "cloud" to search box on home page
      Then user should see "30" results found on search page
      And user should see "Automate Your Life with Cypress" on search page

    Scenario: Scroll until 'Services' label is visible on home page
      Given the user is on the home page
      When the user scrolls down to see 'Services' on home page
      Then the user should see  'Services' label
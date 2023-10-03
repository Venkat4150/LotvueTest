Feature: To validate the community type and status

  Scenario Outline: To validate the community type and status based on Product and According type
    Given Enter url of the website 'https://abrazohomes.stg.lotvue.com/'
    Then I should see 'Login' page with all the details
    When I click on the Login button without entering the login credentials
    Then I should see user validation text messages
    When I enter the uname 'gousebashas@lotvue.com' and password '1'
    Then based on the entered login details it should show Error Message or Username
    When After login click on the operations dropdown list
    Then I should be able to see the list of product type options
    Then Select the '<ProductType>' product type from the list
    When I click on the manage settings icon
    Then I should able to see the list of settings options
    When I selects 'Website Filter Configuration' setting option from the list
    Then I should be able to see 'Website Filter Configuration' page with all the information
    When I click on the '<AccordionType>' accordion
    Then I should be able to see all the communities in '<AccordionType>'
    Then Click on the '<Community>' specific Sales Statuses drop-downs, and from the statuses drop-down list, click on the '<Status>' checkbox
    And Save the details

    Examples: 
      | ProductType | Community   | Status | AccordionType                  |
      | Marketing   | Mil Colores | Sold   | Lots to show in Inventory Lots |

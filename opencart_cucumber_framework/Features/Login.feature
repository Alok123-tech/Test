Feature: Login with Valid Credentials

@new1
  Scenario Outline: Successful Login with Valid Credentials
    Given User Launch browser
    And opens URL "https://www.flipkart.com/search?q=iphone%2014&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off"
    #When user finds the "<iphone>"
    Then user verify the mobile cost "<cost>"
    #When User navigate to MyAccount menu
    #And click on Login
    #And User enters Email as "pavanoltraining@gmail.com" and Password as "test@123"
    #And Click on Login button
    #Then User navigates to MyAccount Page
    
    Examples:
    |cost|
    |₹54,999|

  @fresh
    Scenario: User Login to Google
        Given User Launch browser
        And opens URL "https://google.com"
        And user search for "Selenium Automation"



    
        @new4
  Scenario: Successful Login with Valid Credentials
    Given User Launch browser
    And opens URL "https://www.flipkart.com/furniture/beds-more/beds/pr?sid=wwe%2C7p7%2C4yf&p%5B%5D=facets.price_range.from%3D8999&p%5B%5D=facets.price_range.to%3DMax"
    #When user finds the "<iphone>"
    Then user mouse hover
    
          @Drag
  Scenario: Successful Login with Valid Credentials
    Given User Launch browser
    And opens URL "https://jqueryui.com/droppable/"
    #When user finds the "<iphone>"
    Then user perform drag and drop
    
    @new2
#  Scenario Outline: Successful Login with Valid Credentials
#    Given User Launch browser
#    And opens URL "https://rahulshettyacademy.com/loginpagePractise/#"
#    #When user finds the "<iphone>"
#    Then user click on Link
#    Then user verify the title of open window
    
    
#  @new5
#  Scenario Outline: Successful Login with Valid Credentials
#    Given User Launch browser
#    And opens URL "https://demoqa.com/alerts"
#    Then user perform alert
   
#  @newscenario
#  Scenario Outline: Successful Login with Valid Credentials
#    Given User Launch browser
#    And opens URL "https://demoqa.com/alerts"
#    Then user perform alert
#  @new6
#  Scenario Outline: Successful Login with Valid Credentials
#    Given User Launch browser
#    And opens URL "https://demoqa.com/alerts"
#    Then user perform alert
    
    @new
  Scenario: To identify duplicate elemtnt in list box 
    Given User Launch browser
    And opens URL "https://testautomationpractice.blogspot.com/"
    Then user verify the duplicate list
    #When User navigate to MyAccount menu
    #And click on Login
    #And User enters Email as "pavanoltraining@gmail.com" and Password as "test@123"
    #And Click on Login button
    #Then User navigates to MyAccount Page
    

    
  
@sanity @regression
Scenario Outline: Search Experience for product search in both home and Offers page

Given User is on GreenCart Landing page
When user searched with Shortname <Name> and extracted actual name of product
And Added "3" items of the selected product to cart
Then User proceeds to Checkout and validate the <Name> items in checkout page
And verify user has ability to enter promo code and place the order

Examples:
| Name  |
| Tom 	|






    
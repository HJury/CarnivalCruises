Feature: Smoke Testing

  Background: A new browser is opened at "Carnival Cruise" and a search is made.
    Given The user gets into "Carnival Cruise" page
    When The user enters the needed information
    And Click on "SEARCH CRUISES" button


#Searching for cruises filtering by the duration.
  #As a user I want to search cruises to The Bahamas with duration
  #between 6 and 9 days so that I will visualize all the options to choose
  #one. Right now, I don’t care about departure port.

  Scenario: The result set is shown as a grid.
    Given The user is redirected to the result page
    When The user visualizes the result
    Then These are shown as a grid.

  Scenario: The results are filter by price.
    Given The result are already shown
    When  The user filters by price using the Slide Bar
    Then The results change into the range.

  Scenario: The results are sorted by price.
    Given The result are already shown
    When The user clicks on "Sort By Price" button
    Then The default value will be cheapest first.
 #----------------------------------------------------------------------
# Selecting a cruise and getting more info about it.
    #As a user I want to choose one sail and learn more about the trip, so
    #that I will get more info about itinerary

  Scenario: The itinerary is loaded
    Given The user selects one of cruise of the result set
    Then The user is redirected towards the "Itinerary" page

  Scenario: The about information is available.
    Given The user selects one of cruise of the result set
    And The user is redirected towards the "Itinerary" page
    When The user clicks on "Learn More" button of each day
    Then The information of that day is displayed.

  Scenario: The "Book Now" button is shown.
    Given The user selects one of cruise of the result set
    And The user is redirected towards the "Itinerary" page
    Then An "Book now" button is  displayed.
#----------------------------------------------------------------------
#Redirection towards Booking is possible.

  Scenario: The user is redirected towards Booking
    Given The user selects one of cruise of the result set
    And clicks at "Book Now" button
    Then The user is redirected towards "Booking" page.

  Scenario: The first question is about Staterooms quantity.
    Given The user selects one of cruise of the result set
    And clicks at "Book Now" button
    Then A staterooms quantity question is shown.

#----------------------------------------------------------------------
#Contacting the Carnival Cruise support
  #As a user I want to be able to contact support in case I have questions
  #and prefer to talk to a person, a link to contact a representative must
  #be in the footer of all the booking stages.

  Scenario: A link to support is on the footer.
    Given The user selects one of cruise of the result set
    And clicks at "Book Now" button
    Then A support link on the footer of the page is Shown

  Scenario: A new tab with further instructions is opened when clicking on the link.
    Given The user selects one of cruise of the result set
    And clicks at "Book Now" button
    When The user click on the link
    Then A new tab is opened with more information.

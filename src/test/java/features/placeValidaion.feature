Feature: Validate the Google API's
@AddGetPlaceAPI @Regression
  Scenario Outline: Verify the ADDplaceAPIis working fine
    Given Add place payload "<name>" "<language>" "<address>"
     When user calls "AddPlaceAPI" with "Post" http request
     Then API call got success with status code as 200
      And "status" in response body is "OK"
      And "scope" in response body is "APP"
      And verify above created place is having same name as "<name>" using "getPlaceAPI" method
     

    Examples: 
      | name    | language | address              |
      | AAHoues | English  | World coss centre    |
      #| BBHoues | German   | Tieuppur coss centre |

@DeletePlaceAPI   @Regression      
  Scenario: Verify if DeleteAPI is working fine
  
  Given DeletePlace Payload created
  When user calls "deletePlaceAPI" with "Post" http request 
  Then API call got success with status code as 200
  And "status" in response body is "OK"   
 
 @UpdatePlaceAPI  @Regression
  Scenario: Verify if UpdatePlaceAPI is working fine
 
  Given UpdatePlaceAPI Payload created "70, summer sreet,UK"
  When user calls "updatePlaceAPI" with "PUT" http request 
  Then API call got success with status code as 200
  And "msg" in response body is "Address successfully updated"  
  
  
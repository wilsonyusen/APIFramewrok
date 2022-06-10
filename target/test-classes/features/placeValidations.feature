Feature: Validaing Place API's

@AddPlace
Scenario Outline: Verify if place is being Succesfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user call "addPlaceAPI" with "post" http request 
	Then API call is success with status code 200
	And "status" in response body is "OK"
  And "scope" in response body is "APP"
  And verify place_id created maps to "<name>" using "getPlaceAPI"
  
Examples: 
  |name   | language  | address 			  | 
  |Ahouse | English | World cross center|
  |Chouse | English | Sea cross center  |
 
@DeletePlace
Scenario Outline: Verify if delete functionality is working
	Given Delete Place Payload 
	When user call "deletePlaceAPI" with "POST" http request 
	Then API call is success with status code 200
	And "status" in response body is "OK"
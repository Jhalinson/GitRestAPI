Feature: Validating Place API's 
@AddPlace @Regression
Scenario Outline: Verify is Place is being successfuly added using AddPlaceAPI 
	Given Add place payload with "<name>" "<language>" "<address>"
	When User calls "AddPlaceAPI" with "POST" http request
	Then The API call us success with status code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP" 
	And verify place_id created maps to "<name>" using "GetPlaceAPI"
Examples:
	|  name	  | language      |                address				          |
	|Jhalinson|   English     |Street Ramon Vidarl #54, Prado Oriental        |
#	|Juan	  |France, English|Street Juan Horacio Ma No. 102, Mirador del sur|
@DeletePlace @Regression
Scenario:
	Given DeletePlace Payload
	When User calls "DeletePlaceAPI" with "POST" http request
	Then The API call us success with status code 200
	And "status" in response body is "OK"
	
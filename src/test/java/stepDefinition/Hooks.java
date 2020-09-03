package stepDefinition;

import cucumber.api.java.Before;

public class Hooks {

	@Before
	public void BeforeScenario() throws Throwable {
		// code that will give a place_id
		// execute this code only when place_id will be null
		PlaceStepDefinition placeStepDefinition = new PlaceStepDefinition();

		if (placeStepDefinition.place_id == null) {
			placeStepDefinition.add_place_payload_with("Jhalinson", "French", "Europe");
			placeStepDefinition.user_calls_with_http_request("AddPlaceAPI", "POST");
			placeStepDefinition.verify_place_id_created_maps_to_using("Jhalinson", "GetPlaceAPI");
		}
	}
}
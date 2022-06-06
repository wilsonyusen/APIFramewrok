 package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		if(StepDefinition.placeId == null) {
			StepDefinition m = new StepDefinition();
			m.add_place_payload_with("Shetty", "Chinese", "Asia");
			m.user_call_with_post_http_request("addPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");
		}
	}
	

}

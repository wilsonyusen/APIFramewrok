package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class StepDefinition extends Utils{
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	static String placeId;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		 res = given().spec(requestSpecification())
				 .body(TestDataBuild.addPlacePayLoad(name, language, address));
	}
	@When("user call {string} with {string} http request")
	public void user_call_with_post_http_request(String resource, String method) {
		APIResources resourceApi = APIResources.valueOf(resource);
		//resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST")) {
			response = res.when().post(resourceApi.getResource());
		}else if(method.equalsIgnoreCase("GET")) {
			response = res.when().get(resourceApi.getResource());
		}
				
	}
	@Then("API call is success with status code {int}")
	public void api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 300);
		
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    
	    assertEquals(getJsonPath(response, keyValue), expectedValue );
	}
	

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String recource) throws IOException {
		placeId =  getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_call_with_post_http_request(recource, "GET");
		//System.out.println(new JsonPath(response.asString()).get("name"));
		assertEquals(getJsonPath(response, "name"), name);
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecification()).body(TestDataBuild.deletePlacePayLoad(placeId));
	}

}

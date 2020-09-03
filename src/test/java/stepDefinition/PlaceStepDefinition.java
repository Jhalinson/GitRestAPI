package stepDefinition;

import java.util.Properties;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static io.restassured.RestAssured.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResource;
import resources.TestDataBuild;
import resources.Utils;
import static org.junit.Assert.*;

public class PlaceStepDefinition extends Utils {

	RequestSpecification requestSpecification2;
	ResponseSpecification responseSpecification;
	ResponseSpecBuilder responseSpecBuilder;
	TestDataBuild testDataBuild = new TestDataBuild();
	Response response;
	JsonPath jP;
	Properties prop;
	public static String place_id;

	@Given("^Add place payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_place_payload_with(String arg1, String arg2, String arg3) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		requestSpecification2 = given().spec(requestSpecification())
				.body(testDataBuild.AddPlacePayload(arg1, arg2, arg3));

	}

	@When("^User calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_with_http_request(String resource, String methodType) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		APIResource resourceAPI = APIResource.valueOf(resource);
		System.out.println(resourceAPI.GetResource());
		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();
		if (methodType.equalsIgnoreCase("POST"))
			response = requestSpecification2.when().post(resourceAPI.GetResource());
		else if (methodType.equalsIgnoreCase("GET"))
			response = requestSpecification2.when().post(resourceAPI.GetResource());
	}

	@Then("^The API call us success with status code (\\d+)$")
	public void the_API_call_us_success_with_status_code(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);

	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String keyValue, String expectValue) throws Throwable {
		
		assertEquals(GetJsonPath(response, keyValue), expectValue);
	}
	
	@Then("^verify place_id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		place_id = GetJsonPath(response, "place_id");
		requestSpecification2 = given().spec(requestSpecification()).queryParam("place_id", place_id);
		
		user_calls_with_http_request(resource, "GET");
		String actualName = GetJsonPath(response, "name");
		assertEquals(actualName,  expectedName);
				
	    }
	@Given("^DeletePlace Payload$")
	public void deleteplace_Payload() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		requestSpecification2 = given().spec(requestSpecification()).body(testDataBuild.DeletePlacePayload(place_id));
	    
	}
}

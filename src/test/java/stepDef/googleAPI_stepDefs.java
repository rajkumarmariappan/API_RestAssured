package stepDef;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

import TestData.testData;
import Utilities.enum_Resources;
import Utilities.utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class googleAPI_stepDefs extends utils {
	RequestSpecification request;
	ResponseSpecification resp;
	Response response;
	testData json_data = new testData();
	   JsonPath js;
	  static String place_id_genertaed;
	
	@Given("Add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		
//request spec comes from utils
//test  data comes from test data class		

// request =  given().spec(requestSpec()).body(json_data.addPlaceAPI_payload());	
		request =  given().spec(requestSpec()).body(testData.post_data(name, language, address));	
 
	   
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_a_http_request(String resource, String method) {
		
		resp = 	new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		
		enum_Resources get_resource = enum_Resources.valueOf(resource);
		String res_obtaned = get_resource.getResource();   // resource value is returned as String from ENUM
		
		if(method.equalsIgnoreCase("Post")) {
		 response =  request.when().post(res_obtaned);
		 if(res_obtaned.contains("delete"))
		 {
			 place_id_genertaed = null;
		 }
		}else if(method.equalsIgnoreCase("Get"))
		{
			 response =  request.when().get(res_obtaned);
		}else if(method.equalsIgnoreCase("Put"))
		{
			 response =  request.when().put(res_obtaned);
			 
		}
		 System.out.println("check response-"+response.asString());
		
		
	    
	}
	@SuppressWarnings("deprecation")
	@Then("API call got success with status code as {int}")
	public void api_call_got_success_with_status_code_as(Integer expected_code) {
		response = response.then().spec(resp).extract().response();
		
		
		Integer actual_code = response.getStatusCode();
	
		Assert.assertEquals(expected_code, actual_code);
	   
	}
	
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expected_Key_value) {
//	   String output_json =  response.asString();
//	   System.out.println("Final outpt - "+output_json);
//	   
//	    js = new JsonPath(output_json);
//	   
//	   String actual_key_value = js.getString(key);
	   
	   String actual_key_value=   utils.getJsonPath(response, key);
	 
	   Assert.assertEquals(expected_Key_value, actual_key_value);
	   
	}
	
	@Then("verify above created place is having same name as {string} using {string} method")
	public void verify_above_created_place_is_having_same_name_as_using_method(String name, String resource) throws IOException {
//	    String place_id_genertaed =js.getString(name);
	    
		 place_id_genertaed=   utils.getJsonPath(response, "place_id");
		request =  given().spec(requestSpec()).queryParam("place_id", place_id_genertaed);
//re-using the possible method from step defs list as below line		
		user_calls_with_a_http_request( resource, "GET");
		String actual_name_from_response = utils.getJsonPath(response, "name");
		 Assert.assertEquals(name, actual_name_from_response);
	}
	
	@Given("DeletePlace Payload created")
	public void delete_place_payload_created() throws IOException {
		
		request= given().spec(requestSpec()).body(json_data.delete_data(place_id_genertaed));
	}
	
	@Given("UpdatePlaceAPI Payload created {string}")
	public void update_place_api_payload_created(String address) throws IOException {
		
		request = given().spec(requestSpec()).body(json_data.update_data(place_id_genertaed, address));
	   
	}



}

package stepDef;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@UpdatePlaceAPI  or @DeletePlaceAPI")
	public void createPlaceID() throws IOException
{
	
	googleAPI_stepDefs m =new googleAPI_stepDefs();
	if(googleAPI_stepDefs.place_id_genertaed == null) {
	m.add_place_payload("Rajkumar", "Tamil", "15,Park sreet, UK");
	m.user_calls_with_a_http_request("AddPlaceAPI", "Post");
	m.verify_above_created_place_is_having_same_name_as_using_method("Rajkumar", "getPlaceAPI");
	}
}
 
}

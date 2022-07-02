package TestData;

import java.util.ArrayList;

import io.restassured.RestAssured;


public class testData {
	
	public  pojo.google addPlaceAPI_payload(String name, String language, String address)
	{
		pojo.google g = new pojo.google();
		
		pojo.location loc = new pojo.location();
		loc.setLat(38.383494);
		loc.setLan(33.427362);
		g.setLocation(loc);
		
		
		g.setAccuracy(50);
		g.setName("Frontline house");
		g.setPhone_number("(+91) 983 893 3937");
		g.setAddress("70 Summer walk, USA");
		
		ArrayList<String> list = new ArrayList<>();
		list.add("shoe park");
		list.add("shop");
		g.setTypes(list);
		
		g.setWebsite("http://google.com");
		g.setLanguage("French-IN");
		
		return g;

	}
	
	public static String post_data(String name, String language, String address)
	{
		return "{\r\n"
				+ "    \"location\": {\r\n"
				+ "        \"lat\": -38.383494,\r\n"
				+ "        \"lng\": 33.427362\r\n"
				+ "    },\r\n"
				+ "    \"accuracy\": 50,\r\n"
				+ "    \"name\": \""+name+"\",\r\n"
				+ "    \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "    \"address\": \""+address+"\",\r\n"
				+ "    \"types\": [\r\n"
				+ "        \"shoe park\",\r\n"
				+ "        \"shop\"\r\n"
				+ "    ],\r\n"
				+ "    \"website\": \"http://google.com\",\r\n"
				+ "    \"language\": \""+language+"\"\r\n"
				+ "}";
	}

	public static String delete_data(String place_id_generated)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+place_id_generated+"\"\r\n"
				+ "}\r\n"
				+ "";
		
	}
	
	public static String update_data(String place_id_generated, String address)
	{
		return "{\r\n"
				+ "\"place_id\":\""+place_id_generated+"\",\r\n"
				+ "\"address\":\""+address+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}";
		
	}
	
	
}

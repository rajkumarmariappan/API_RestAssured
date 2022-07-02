package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class utils {
	
	static RequestSpecification req;
	
	public static RequestSpecification requestSpec() throws IOException
	{
		
		if(req==null) {
		FileOutputStream fso = new FileOutputStream("log.txt");		
		PrintStream log = new PrintStream(fso);
		
		 req= new RequestSpecBuilder()
				 .setBaseUri(getGlobalValue("baseUri"))
				 .addQueryParam("key", "qaclick123")
				 .addFilter(RequestLoggingFilter.logRequestTo(log))  //to add logs forinput respponse
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
				 .setContentType(ContentType.JSON)
				 .build();
		 return req;
		}
		return req;
	}
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fsi = new FileInputStream("D:\\Softwares\\API_Projects_Workpsace\\API_Framework\\src\\test\\java\\Utilities\\global.properties");
		prop.load(fsi);
		String value = prop.getProperty(key);
		return value;
	}
	
	public static String getJsonPath(Response response, String key)
	{
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		
		return js.get(key).toString();
		
	}

}

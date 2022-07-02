package Utilities;

public enum enum_Resources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json"),
	updatePlaceAPI("/maps/api/place/update/json");
	
	private String resource;
	enum_Resources(String resource) {
		this.resource = resource;
	}
	
	public String getResource()
	{
		return resource;
	}

}

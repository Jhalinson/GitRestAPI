package resources;

import java.util.ArrayList;

import pojos.*;

public class TestDataBuild {
	private AddPlace aP;
	private Location l;

	public AddPlace AddPlacePayload(String name, String language, String address) {

		aP = new AddPlace();
		l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		aP.setLocation(l);
		aP.setAccuracy(50);
		aP.setName(name);
		aP.setPhone_number("809 352 1929");
		aP.setAddress(address);
		ArrayList<String> typesList = new ArrayList<>();
		aP.setTypes(typesList);
		typesList.add("shoe park");
		typesList.add("shop");
		aP.setWebsite("https://mvnrepository.com/");
		aP.setLanguage(language);
		return aP;
	}
	public String DeletePlacePayload(String placeId) {
		return "{\r\n" + 
				"    \"place_id\":\""+placeId+"\"\r\n" + 
				"}\r\n" + 
				"";
	}
	
}

package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification requestSpecification1;
	private PrintStream stream;
	static FileInputStream fIS;
	static File file;
	static Properties prop;

	public RequestSpecification requestSpecification() throws IOException {
		if (requestSpecification1 == null) {
			stream = new PrintStream(new FileOutputStream("logging.txt"));
			requestSpecification1 = new RequestSpecBuilder().setBaseUri(GetGlobalProperties("baseUrl").toString())
					.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(stream))
					.addFilter(ResponseLoggingFilter.logResponseTo(stream)).setContentType(ContentType.JSON).build();
			return requestSpecification1;
		}
		return requestSpecification1;
	}

	public static String GetGlobalProperties(String key) throws IOException {
		file = new File(
				"C:\\Users\\jhali\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		fIS = new FileInputStream(file);
		prop = new Properties();
		prop.load(fIS);
		return prop.getProperty(key);

	}
	public String GetJsonPath(Response response, String key) {
		// Write code here that turns the phrase above into concrete actions
				String resp = response.asString();
				JsonPath jP = new JsonPath(resp);
				return jP.get(key).toString();
				
	}

}

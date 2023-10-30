package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoint2 {

	static ResourceBundle getUrl() {
	
		ResourceBundle routes = ResourceBundle.getBundle("routes");//routes is the properties file name stored in resources folder passed as parameter to getBundle method
		return routes;
	
	}
	
	public static Response createUser(User payload)
	{
		String post_url = getUrl().getString("post_url");
		
		Response response = given()
								.contentType("application/json")
								.accept("application/json")
								.body(payload)
							.when()
								.post(post_url);
		
		return response;
	}
	
	public static Response getUser(String userName)
	{
		String get_url = getUrl().getString("get_url");
		
		System.out.println("UserName: "+ userName);
		//System.out.println("URL: "+Routes.get_user);
		
		Response response = given()
								.accept("application/json")
								.pathParam("username", userName)
							.when()
								.get(get_url);
								//.get("https://petstore.swagger.io/v2/user/{username}");
		
		return response;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		String update_url = getUrl().getString("update_url");
		
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.body(payload)
								.pathParam("username", userName)
							.when()
								.put(update_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		String delete_url = getUrl().getString("delete_url");
		
		Response response = given()
								.accept("application/json")
								.pathParam("username", userName)
							.when()
								.delete(delete_url);
								//.delete("https://petstore.swagger.io/v2/user/{username}");
		
		return response;
	}
}

package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoint {

	public static Response createUser(User payload)
	{
		System.out.println("Id "+payload.getId());
		System.out.println("Username "+payload.getUsername());
		System.out.println("FirstName "+payload.getFirstName());
		System.out.println("LastName "+payload.getLastName());
		System.out.println("Email "+payload.getEmail());
		System.out.println("Password "+payload.getPassword());
		System.out.println("Phone "+payload.getPhone());
		System.out.println("Status: "+payload.getUserStatus());
		
		Response response = given()
								.contentType("application/json")
								.accept("application/json")
								.body(payload)
							.when()
								.post(Routes.post_user);
		
		return response;
	}
	
	public static Response getUser(String userName)
	{
		System.out.println("UserName: "+ userName);
		//System.out.println("URL: "+Routes.get_user);
		
		Response response = given()
								.accept("application/json")
								.pathParam("username", userName)
							.when()
								.get(Routes.get_user);
								//.get("https://petstore.swagger.io/v2/user/{username}");
		
		return response;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.body(payload)
								.pathParam("username", userName)
							.when()
								.put(Routes.update_user);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response = given()
								.accept("application/json")
								.pathParam("username", userName)
							.when()
								.delete(Routes.delete_user);
								//.delete("https://petstore.swagger.io/v2/user/{username}");
		
		return response;
	}
}

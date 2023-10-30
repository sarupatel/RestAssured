package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoint;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setUp() {
		
		faker = new Faker();
		userPayload=new User();
				
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());	
		
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testCreateUser()
	{
		logger.info("******* Creating User *********");
		Response response = UserEndpoint.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******* User created successfully *********");
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("******* Reading User *********");
		Response response = UserEndpoint.getUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******* User details fetched successfully *********");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("******* Updating User *********");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndpoint.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******* User updated successfully *********");
	}
	
	@Test(priority=4) 
	public void testDeleteUserByName()
	{
		logger.info("******* Deleting User *********");
		Response response = UserEndpoint.deleteUser(this.userPayload.getUsername());
		//Response response = UserEndpoint.deleteUser("cleo.bednar");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******* User deleted successfully *********");
	}
}

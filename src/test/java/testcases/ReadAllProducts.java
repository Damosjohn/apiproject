package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReadAllProducts {
	@Test
	public void readallproducts() {
		SoftAssert softassert = new SoftAssert();
		Response response = 
				
			given()
		        //.log().all()

				.baseUri("https://techfios.com/api-prod/api/product/")
				.header("Content-Type", "application/json; charset=UTF-8")
				.auth().preemptive().basic("demo@techfios.com", "abc123").
				
			when()
			    //.log().all()
			    .get("read.php").
			then()
			    //.log().all()
				.extract().response();
		int actualstatuscode = response.getStatusCode();
		System.out.println("actualstatuscode:" + actualstatuscode);
		Assert.assertEquals(actualstatuscode, 200);
		//GET ACTUAL HEADER
		String actualHeader = response.getHeader("Content-Type");
        System.out.println("actualHeader:"+actualHeader);
        Assert.assertEquals(actualHeader,"application/json; charset=UTF-8");
	   String actualresponsebody = response.getBody().asString();
      System.out.println("actualresponsebody:"+actualresponsebody);
        //to convert json and String
        JsonPath jp = new JsonPath(actualresponsebody);
       String firstproductid = jp.get("records[0].id");
        System.out.println("firstproductid:"+firstproductid);
        if(firstproductid!=null) {
        	System.out.println("record are not null");
        	
        	
        } else {
        	System.out.println("record are null");
        }
         
	}

}

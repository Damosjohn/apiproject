package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ReadOneProduct {
	SoftAssert softassert;
	
	public ReadOneProduct() {
		softassert = new SoftAssert(); 
	}
	@Test
	public void readoneproduct() {
		Response response =
		given()
		   .baseUri("https://techfios.com/api-prod/api/product")
		   .auth().preemptive().basic("demo@techfios.com","abc123")
		   .header("Content-Type","application/json").queryParam("id","3769").
		when().get("read_one.php").
		then().extract().response();
		int actualstatuscode = response.getStatusCode();
		System.out.println("actualstatuscode :"+actualstatuscode );
		softassert.assertEquals(actualstatuscode,200,"statuscode not found!!!");
		String actualheader = response.getHeader("Content-Type");
		System.out.println("actualheader:"+actualheader);
		softassert.assertEquals(actualheader,"application/json","Header not founded!!!");
		String actualbody = response.getBody().asString();
		System.out.println("actualbody:"+actualbody);
		JsonPath jp = new JsonPath(actualbody);
		String actualbodyid = jp.get("id");
		System.out.println("actualbodyid:"+actualbodyid);
		softassert.assertEquals(actualbodyid,"3777","bodyids are not founded!!!");
		String actualbodyname = jp.get("name");
		System.out.println("actualbodyname:"+actualbodyname);
		softassert.assertEquals(actualbodyid,"Md's Pillow 3.0","bodyname are not founded!!!");
		String actualbodydescription = jp.get("description");
		System.out.println("actualbodydescription:"+actualbodydescription);
		softassert.assertEquals(actualbodydescription,"3769","bodydescription are not founded!!!");
		softassert.assertAll();
		
	}
	
	

}

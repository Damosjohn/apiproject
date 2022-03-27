package testcases;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeleteOneProduct {
	
		SoftAssert softassert ;
		public DeleteOneProduct() {
			softassert = new SoftAssert();
		}
		@Test(priority=1)
		public void deleteoneproduct() {
			Response response =
			given()
			   .baseUri("https://techfios.com/api-prod/api/product")
			   .auth().preemptive().basic("demo@techfios.com","abc123")
			   .header("Content-Type","application/json; charset=UTF-8")
			   .body(new File("src\\main\\java\\data\\createpayload.json")).
			when().delete("delete.php").
			then().extract().response();
			int actualstatuscode = response.getStatusCode();
			System.out.println("actualstatuscode :"+actualstatuscode );
			softassert.assertEquals(actualstatuscode,200,"statuscode not found!!!");
			String actualheader = response.getHeader("Content-Type");
			System.out.println("actualheader:"+actualheader);
			softassert.assertEquals(actualheader,"application/json; charset=UTF-8","Headers are not founded!!!");
			String actualbody = response.getBody().asString();
			System.out.println("actualbody:"+actualbody);
			JsonPath jp = new JsonPath(actualbody);
			String actualproductmessage = jp.get("message");
			System.out.println("actualproductmessage:"+actualproductmessage);
			softassert.assertEquals(actualproductmessage,"Product was deleted","product messages are not matching!!");
			
	}
		@Test(priority=2)
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
				   .header("Content-Type","application/json").queryParam("id","3733").
				when().get("read_one.php").
				then().extract().response();
				int actualstatuscode = response.getStatusCode();
				System.out.println("actualstatuscode :"+actualstatuscode );
				softassert.assertEquals(actualstatuscode,404,"statuscode not found!!!");
				String actualheader = response.getHeader("Content-Type");
				System.out.println("actualheader:"+actualheader);
				softassert.assertEquals(actualheader,"application/json","Header not founded!!!");
				String actualbody = response.getBody().asString();
				System.out.println("actualbody:"+actualbody);
				JsonPath jp = new JsonPath(actualbody);
				String actualbodymessage = jp.get("message");
				System.out.println("actualbodymessage:"+actualbodymessage);
				softassert.assertEquals(actualbodymessage,"Product does not exist","bodyids are not founded!!!");
				
				
			}
		}
}
			
			


	




package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.File;
public class CreateOneProduct {
	SoftAssert softassert ;
	public CreateOneProduct() {
		softassert = new SoftAssert();
	}
	@Test(priority=1)
	public void createoneproduct() {
		Response response =
		given()
		   .baseUri("https://techfios.com/api-prod/api/product")
		   .auth().preemptive().basic("demo@techfios.com","abc123")
		   .header("Content-Type","application/json; charset=UTF-8")
		   .body(new File("src\\main\\java\\data\\createpayload.json")).
		when().post("create.php").
		then().extract().response();
		int actualstatuscode = response.getStatusCode();
		System.out.println("actualstatuscode :"+actualstatuscode );
		softassert.assertEquals(actualstatuscode,201,"statuscode not found!!!");
		String actualheader = response.getHeader("Content-Type");
		System.out.println("actualheader:"+actualheader);
		softassert.assertEquals(actualheader,"application/json; charset=UTF-8","Headers are not founded!!!");
		String actualbody = response.getBody().asString();
		System.out.println("actualbody:"+actualbody);
		JsonPath jp = new JsonPath(actualbody);
		String actualproductmessage = jp.get("message");
		System.out.println("actualproductmessage:"+actualproductmessage);
		softassert.assertEquals(actualproductmessage,"Product was created","product messages are not matching!!");
		
	}
	@Test(priority=2)
	public void readoneproduct() {
		Response response =
		given()
		   .baseUri("https://techfios.com/api-prod/api/product")
		   .auth().preemptive().basic("demo@techfios.com","abc123")
		   .header("Content-Type","application/json").queryParam("id","3774").
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
		softassert.assertEquals(actualbodyid,"Md's Pillow 2.0","bodyname are not founded!!!");
		String actualbodydescription = jp.get("description");
		System.out.println("actualbodydescription:"+actualbodydescription);
		softassert.assertEquals(actualbodydescription,"3769","bodydescription are not founded!!!");
		
		
	}
	
	
			
				                                            
	}



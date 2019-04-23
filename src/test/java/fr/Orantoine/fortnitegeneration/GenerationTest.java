package fr.Orantoine.fortnitegeneration;

import io.restassured.RestAssured;
import org.junit.Test;

public class GenerationTest extends atos.amf.fft.BaseTestCase {

    @Test
    public void healthCheck(){
        System.out.println("Health Check");
        RestAssured.given().when().log().all().get("health").then().assertThat().statusCode(200);
    }
}

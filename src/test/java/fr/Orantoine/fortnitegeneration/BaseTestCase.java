package atos.amf.fft;

import io.restassured.RestAssured;

import org.junit.jupiter.api.BeforeAll;

public class BaseTestCase {


    @BeforeAll
    static void setUp() {

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;

        System.out.println("Contexte des tests :" + RestAssured.baseURI + ":" + RestAssured.port + RestAssured.basePath );
    }

}

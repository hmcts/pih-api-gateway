package uk.gov.hmcts.futurehearings.pip.unit.testing.utils;

import io.restassured.response.Response;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.gov.hmcts.futurehearings.pip.unit.testing.utils.TestReporter.getObjStep;

public class PublicationsResponseVerifier {

    public static void thenValidateResponseForGetPublications(Response response){
        try{
            Map<String, String> responseMap = response.getBody().jsonPath().getMap("$");
            assertEquals(200, response.getStatusCode(),"Status Code Validation:");
            getObjStep().pass("Got the expected status code: 200");
        }
        catch (AssertionError e){
            getObjStep().fail("Exception in "+e.getMessage());
            throw e;
        }
        catch (Exception e){
            getObjStep().fail("Exception: "+e.getClass());
            throw e;
        }
    }

    public static void thenValidateResponseForPostPublications(Response response){
        try{
            Map<String, String> responseMap = response.getBody().jsonPath().getMap("$");
            assertEquals(201, response.getStatusCode(),"Status Code Validation:");
            getObjStep().pass("Got the expected status code: 201");
        }
        catch (AssertionError e){
            getObjStep().fail("Exception in "+e.getMessage());
            throw e;
        }
        catch (Exception e){
            getObjStep().fail("Exception: "+e.getClass());
            throw e;
        }
    }

}

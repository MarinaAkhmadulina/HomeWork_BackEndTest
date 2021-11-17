package tests.ImageTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static io.restassured.RestAssured.given;
import static tests.Endpoints.DELETE_IMAGE;

public class DeleteImageTests extends BaseTest {

    @BeforeEach
    void uploadImage() {
        uploadImage(requestSpecificationWithAuthAndImage)
                .then()
                .statusCode(200);
    }

    @Test
    void deletionImageTest() {
        given(requestSpecificationAuth, positiveResponseSpecification)
                .delete(DELETE_IMAGE, imageDeleteHash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
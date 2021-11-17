package tests.ImageTests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static io.restassured.RestAssured.given;
import static tests.Endpoints.DELETE_ACCOUNT_IMAGE;
import static tests.Endpoints.UPLOAD_IMAGE;

@Story("Image API Tests")
public class UploadImageTests extends BaseTest {

    @AfterEach
    void comeBack() {
        given(requestSpecificationAuth)
                .when()
                .delete(DELETE_ACCOUNT_IMAGE,
                        username, imageDeleteHash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void uploadImageTest() {
        uploadImage(requestSpecificationWithAuthAndImage)
                .then()
                .statusCode(200);
    }

    @Test
    void uploadWrongFormatImageTest() {
        given(requestSpecificationWithAuthAndWrongFormatImage)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .statusCode(417);
    }

    @Test
    void uploadWithoutImageTest() {
        given(requestSpecificationAuth, negativeResponseSpecification)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .statusCode(400);
    }
}
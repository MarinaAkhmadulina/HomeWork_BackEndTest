package tests.ImageTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static io.restassured.RestAssured.given;
import static tests.Endpoints.*;

public class ChangeImage extends BaseTest {

    @BeforeEach
    void uploadImage() {
        uploadImage(requestSpecificationWithAuthAndImage)
                .then()
                .statusCode(200);
    }

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
    void favouriteAnImageTest() {
        given(requestSpecificationAuth, positiveResponseSpecification)
                .post(FAVORITE_IMAGE, imageHash)
                .prettyPeek();
    }

    @Test
    void getImageInfoTest() {
        given(requestSpecificationAuth, positiveResponseSpecification)
                .get(GET_IMAGE, imageHash)
                .prettyPeek();
    }

    @Test
    void getDeleteImageInfoTest() {
        deletionImage();
        given(requestSpecificationAuth)
                .get(GET_IMAGE, imageHash)
                .prettyPeek()
                .then()
                .statusCode(404);
    }

    @Test
    void favouriteAnDeleteImageTest() {
        deletionImage();
        given(requestSpecificationAuth)
                .post(FAVORITE_IMAGE, imageHash)
                .prettyPeek()
                .then()
                .statusCode(404);
    }
}

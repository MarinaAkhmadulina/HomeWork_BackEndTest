package tests.AlbumsTests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static io.restassured.RestAssured.given;
import static tests.Endpoints.DELETE_ACCOUNT_ALBUM;

@Story("Album API Tests")
public class CreateAlbumTests extends BaseTest {

    @AfterEach
    void comeBack() {
        given(requestSpecificationAuth())
                .when()
                .delete(DELETE_ACCOUNT_ALBUM,
                        username, albumHash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void creationAlbumTest() {
        creationAlbum(NAMEALBUM)
                .then()
                .statusCode(200);
    }
}
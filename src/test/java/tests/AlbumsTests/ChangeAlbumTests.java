package tests.AlbumsTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;
import static io.restassured.RestAssured.given;
import static tests.Endpoints.*;

public class ChangeAlbumTests extends BaseTest {

    @BeforeEach
    void creationAlbumTest() {
        creationAlbum(NAMEALBUM)
                .then()
                .statusCode(200);
    }

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
    void favouriteDeletedAlbumTest() {
        deletionAlbum();
        given(requestSpecificationAuth())
                .post(FAVORITE_ALBUM, albumHash)
                .prettyPeek()
                .then()
                .statusCode(404);
    }

    @Test
    void getAlbumInfoTest() {
        given(requestSpecificationAuth(), positiveResponseSpecification)
                .get(GET_ALBUM, albumHash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
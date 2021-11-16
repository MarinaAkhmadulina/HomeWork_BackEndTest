package tests;

import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

@Story("Image API Tests")
public class ImageTests extends BaseTest {

    private final String IMAGE = "src/test/java/resources/image.jpg";
    private final String BOOK = "src/test/java/resources/book.fb2";
    static String imageDeleteHash;
    static String imageHash;
    static Response value;

    private static Response uploadImage(String path) {
        value = given()
                .headers("Authorization", token)
                .multiPart("image", new File(path))
                .expect()
                .body("success", is(true))
                .body("data.id", is(notNullValue()))
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek();
        imageHash = value
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.id");
        imageDeleteHash = value
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");
        return value;
    }

    private static Response deletionImage() {
        return given()
                .headers("Authorization", token)
                .expect()
                .body("success", is(true))
                .when()
                .delete("https://api.imgur.com/3/image/{imageDeleteHash}", imageDeleteHash)
                .prettyPeek();
    }

    @AfterEach
    void comeBack() {
        given()
                .headers("Authorization", token)
                .when()
                .delete("https://api.imgur.com/3/account/{username}/image/{deleteHash}",
                        username, imageDeleteHash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void uploadFileTest() {
        uploadImage(IMAGE)
                .then()
                .statusCode(200);
    }

    @Test
    void uploadWrongFormatImageTest() {
        given()
                .headers("Authorization", token)
                .multiPart("image", new File(BOOK))
                .expect()
                .body("success", is(false))
                .body("data.id", is(nullValue()))
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek()
                .then()
                .statusCode(417);
    }

    @Test
    void uploadWithoutImageTest() {
        given()
                .headers("Authorization", token)
                .multiPart("image", "")
                .expect()
                .body("success", is(false))
                .body("data.id", is(nullValue()))
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek()
                .then()
                .statusCode(400);
    }

    @Test
    void favouriteAnImageTest() {
        uploadImage(IMAGE);
        given()
                .headers("Authorization", token)
                .multiPart("image", IMAGE)
                .expect()
                .body("success", is(true))
                .when()
                .post("https://api.imgur.com/3/image/{imageHash}/favorite", imageHash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getImageInfoTest() {
        uploadImage(IMAGE);
        given()
                .headers("Authorization", token)
                .multiPart("image", IMAGE)
                .expect()
                .body("success", is(true))
                .when()
                .get("https://api.imgur.com/3/image/{imageHash}", imageHash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void deletionImageTest() {
        uploadImage(IMAGE);
        deletionImage().then()
                .statusCode(200);
    }

    @Test
    void getDeleteImageInfoTest() {
        uploadImage(IMAGE);
        deletionImage();
        given()
                .headers("Authorization", token)
                .get("https://api.imgur.com/3/image/{imageHash}", imageHash)
                .prettyPeek()
                .then()
                .statusCode(404);
    }

    @Test
    void favouriteAnDeleteImageTest() {
        uploadImage(IMAGE);
        deletionImage();
        given()
                .headers("Authorization", token)
                .post("https://api.imgur.com/3/image/{imageHash}/favorite", imageHash)
                .prettyPeek()
                .then()
                .statusCode(404);
    }
}
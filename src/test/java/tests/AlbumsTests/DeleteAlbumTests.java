package tests.AlbumsTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

public class DeleteAlbumTests extends BaseTest {

    @BeforeEach
    void creationAlbumTest() {
        creationAlbum(NAMEALBUM)
                .then()
                .statusCode(200);
    }

    @Test
    void deletionAlbumTest() {
        deletionAlbum()
                .then()
                .statusCode(200);
    }
}

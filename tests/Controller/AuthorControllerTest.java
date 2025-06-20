package Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorControllerTest {

    @Test
    void authorsearch() {
        System.out.println(AuthorController.searchAuthor("george orwell"));
    }

}
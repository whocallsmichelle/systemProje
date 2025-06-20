package Controller;

import Model.Book.Book;
import Model.Book.BookOps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookControllerTest {

    private Book testBook;

    @BeforeEach
    void setUp() {
        testBook = new Book(1, 1, "Test Book", 2023, 100, "cover.jpg", "about book", new Date());
    }

    @Test
    void addBook_withValidAdminSession_shouldSucceed() {
        // Assume sessionID 1 is an admin in your mock SessionService
        Result result = BookController.addBook(6, testBook);
        assertEquals("success", result.status);
    }

    @Test
    void addBook_withInvalidSession_shouldFail() {
        // Assume sessionID -1 is not an admin
        Result result = BookController.addBook(7, testBook);
        assertEquals("error", result.status);
    }

    @Test
    void deleteBook_withValidAdminSession_shouldSucceed() {
        // First add a book
        BookController.addBook(7, testBook);
        Result result = BookController.deleteBook(6, 4);
        assertEquals("success", result.status);
    }

    @Test
    void deleteBook_withInvalidSession_shouldFail() {
        Result result = BookController.deleteBook(-1, 1);
        assertEquals("error", result.status);
    }

    @Test
    void displayBook_shouldReturnCorrectBook() {
        Result result = BookController.displayBook(7, 5);
        System.out.println(result.getData());
    }

    @Test
    void displayAllBooks_shouldReturnList() {
        Result result = BookController.displayAllBooks();
        System.out.println(result.getData());
    }
    @Test
    void updateBook_withValidSessionAndExistingBook_shouldSucceed() {
        // Setup test book with ID 5 and modified details
        Book updatedBook = new Book(
                5,            // existing book ID
                2,            // new author ID
                "Updated Title",
                2024,
                350,
                "updated_cover.jpg",
                "Updated about text.",
                new Date()
        );

        // Act: Call update logic via BookOps directly or through controller (if wrapped)
        boolean success = BookOps.updateBook(updatedBook);

        // Assert: should return true and print success message
        assertTrue(success, "Book should be updated successfully");
    }
}

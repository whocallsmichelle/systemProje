package Controller;

import Model.Book.Book;
import Model.Book.BookOps;

public class BookController {
    public static Result addBook(int sessionID ,Book book) {
        Result res = SessionService.yetkiKontrol(sessionID,"admin");
        if (res.status.equals("failure")) {
            return res;
        }
        if (BookOps.addBook(book)) {
            return new Result("success", "Book added successfully", sessionID);
        } else {
            return new Result("failure", "Failed to add book", -1);
        }
    }
    public static Result deleteBook(int sessionID, int bookId) {
        Result res = SessionService.yetkiKontrol(sessionID,"admin");
        if (res.status.equals("failure")) {
            return res;
        }
        if (BookOps.removeBook(bookId)) {
            return new Result("success", "Book deleted successfully", sessionID);
        } else {
            return new Result("failure", "Failed to delete book", -1);
        }
    }
    public static Result displayBook(int sessionID, int bookId) {

        Book book = BookOps.displayBook(bookId);
        if (book != null) {
            Result result = new Result("success", "Book retrieved successfully", sessionID);
            result.setData(book);
            return result;
        } else {
            return new Result("failure", "Failed to retrieve book", -1);
        }
    }
    public static Result displayAllBooks() {

        Result result = new Result("success", "All books retrieved successfully", -1);
        result.setData(BookOps.listAllBooks());
        return result;
    }
    public static Result updateBook(int sessionID, Book book) {
        Result res = SessionService.yetkiKontrol(sessionID,"admin");
        if (res.status.equals("failure")) {
            return res;
        }
        if (BookOps.updateBook(book)) {
            return new Result("success", "Book updated successfully", sessionID);
        } else {
            return new Result("failure", "Failed to update book", -1);
        }
    }
}

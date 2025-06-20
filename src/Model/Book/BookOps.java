package Model.Book;

import Config.DBconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookOps {
    public static boolean addBook(Book book) {
        String query = "INSERT INTO books (authorId, title, year,numberOfPages,cover,about,releaseDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, book.getAuthorId());
            stmt.setString(2, book.getTitle());
            stmt.setInt(3, book.getYear());
            stmt.setInt(4, book.getNumberOfPages());
            stmt.setString(5, book.getCover());
            stmt.setString(6, book.getAbout());
            stmt.setDate(7, new java.sql.Date(book.getReleaseDate().getTime()));


            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book created successfully.");
                return true;
            } else {
                System.out.println("Failed to create Book.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error in addBook: " + e.getMessage());
        }
        return false;
    }
    public static boolean removeBook(int bookID) {
        String query = "DELETE FROM books WHERE bookId = ?";
        try (Connection conn = DBconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookID);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book removed successfully.");
                return true;
            } else {
                System.out.println("Failed to remove Book.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error in removeBook: " + e.getMessage());
        }

        return true; // Placeholder return value
    }
    public static Book displayBook(int bookID) {
        String query = "SELECT * FROM `books` RIGHT JOIN authors ON authors.authorId = books.authorId WHERE bookId = ?";
        try (Connection conn = DBconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setAuthorID(rs.getInt("authorId"));
                book.setTitle(rs.getString("title"));
                book.setYear(rs.getInt("year"));
                book.setNumberOfPages(rs.getInt("numberOfPages"));
                book.setCover(rs.getString("cover"));
                book.setAbout(rs.getString("about"));
                book.setReleaseDate(rs.getDate("releaseDate"));

                book.setAuthorName(rs.getString("Name"));
                book.setAuthorSurname(rs.getString("Surname"));
                book.setAuthorWebsite(rs.getString("Website"));




                return book;
            }

        } catch (SQLException e) {
            System.out.println("Error in addBook: " + e.getMessage());
        }
        return null;

    }
    public static ArrayList<Book> listAllBooks(){
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM `books` RIGHT JOIN authors ON authors.authorId = books.authorId";
        try (Connection conn = DBconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setAuthorID(rs.getInt("authorId"));
                book.setTitle(rs.getString("title"));
                book.setYear(rs.getInt("year"));
                book.setNumberOfPages(rs.getInt("numberOfPages"));
                book.setCover(rs.getString("cover"));
                book.setAbout(rs.getString("about"));
                book.setReleaseDate(rs.getDate("releaseDate"));

                book.setAuthorName(rs.getString("Name"));
                book.setAuthorSurname(rs.getString("Surname"));
                book.setAuthorWebsite(rs.getString("Website"));

                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Error in listAllBooks: " + e.getMessage());
        }
        return books;
    }

    public static boolean updateBook(Book book) {
        String query = "UPDATE books SET authorId = ?, title = ?, year = ?, numberOfPages = ?, cover = ?, about = ?, releaseDate = ? WHERE bookId = ?";
        try (Connection conn = DBconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, book.getAuthorId());
            stmt.setString(2, book.getTitle());
            stmt.setInt(3, book.getYear());
            stmt.setInt(4, book.getNumberOfPages());
            stmt.setString(5, book.getCover());
            stmt.setString(6, book.getAbout());
            stmt.setDate(7, new java.sql.Date(book.getReleaseDate().getTime()));
            stmt.setInt(8, book.getBookID());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book updated successfully.");
                return true;
            } else {
                System.out.println("Failed to update Book.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error in updateBook: " + e.getMessage());
        }
        return false;
    }
}

package Model.Book;

import Config.DBconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookOps {
    public boolean addBook(Book book) {
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
    public boolean removeBook(int bookID) {
        // Logic to remove a book
        return true; // Placeholder return value
    }
    public Book displayBook(int bookID) {
        String query = "SELECT * FROM `books` RIGHT JOIN authors ON authors.authorId = books.authorId WHERE bookId = ?";
        try (Connection conn = DBconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setAuthorId(rs.getInt("authorId"));
                book.setTitle(rs.getString("title"));
                book.setYear(rs.getInt("year"));
                book.setNumberOfPages(rs.getInt("numberOfPages"));
                book.setCover(rs.getString("cover"));
                book.setAbout(rs.getString("about"));
                book.setReleaseDate(rs.getDate("releaseDate"));

                book.setAuthorName(rs.getString("authorName"));
                book.setAuthorSurname(rs.getString("authorSurname"));
                book.setAuthorWebsite(rs.getString("authorWebsite"));




                return book;
            }




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
    public ArrayList<Book> listAllBooks(){
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM `books` RIGHT JOIN authors ON authors.authorId = books.authorId";
        try (Connection conn = DBconn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setAuthorId(rs.getInt("authorId"));
                book.setTitle(rs.getString("title"));
                book.setYear(rs.getInt("year"));
                book.setNumberOfPages(rs.getInt("numberOfPages"));
                book.setCover(rs.getString("cover"));
                book.setAbout(rs.getString("about"));
                book.setReleaseDate(rs.getDate("releaseDate"));

                book.setAuthorName(rs.getString("authorName"));
                book.setAuthorSurname(rs.getString("authorSurname"));
                book.setAuthorWebsite(rs.getString("authorWebsite"));

                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Error in listAllBooks: " + e.getMessage());
        }
        return books;
    }

}

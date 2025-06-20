package Model.Book;

import java.util.Date;

public class Book {
private int bookID;
private int authorID;
private String title;
private int year;
private int numberOfPages;
private String cover;
private String about;
private Date releaseDate;

private String authorName;
private String authorSurname;
private String authorWebsite;


public Book(int bookID, int authorID, String title, int year, int numberOfPages, String cover, String about, Date releaseDate) {
    this.bookID = bookID;
    this.authorID = authorID;
    this.title = title;
    this.year = year;
    this.numberOfPages = numberOfPages;
    this.cover = cover;
    this.about = about;
    this.releaseDate = releaseDate;
}
public Book() {
    this.bookID = 0;
    this.authorID = 0;
    this.title = "";
    this.year = 0;
    this.numberOfPages = 0;
    this.cover = "";
    this.about = "";
    this.releaseDate = new Date();
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
    public String getAuthorWebsite() {
        return authorWebsite;
    }

    public void setAuthorWebsite(String authorWebsite) {
        this.authorWebsite = authorWebsite;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }



    public int getAuthorId() {
    return authorID;
    }

    public void setBookId(int bookId) {
    this.bookID = bookId;
    }

    @Override
    public String toString() {

        return "Book{" +
                "bookID=" + bookID +
                ", authorID=" + authorID +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", numberOfPages=" + numberOfPages +
                ", cover='" + cover + '\'' +
                ", about='" + about + '\'' +
                ", releaseDate=" + releaseDate +
                ", authorName='" + authorName + '\'' +
                ", authorSurname='" + authorSurname + '\'' +
                ", authorWebsite='" + authorWebsite + '\'' +
                '}';
}
}

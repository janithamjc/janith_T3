package sample04;

public class Book {

    int bookId;
    String bookName;
    String publication;
    String author;

    public Book(){

    }

    public Book(int bookId, String bookName, String publication, String author) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.publication = publication;
        this.author = author;
    }

    public Book(  String bookName, String publication, String author) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.publication = publication;
        this.author = author;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

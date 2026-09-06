import java.time.LocalDate;

public class Book {

    //fields: container for the data that'll be filled in a book(object)
    private String title;
    private String author;
    private int totalPages;
    private int currentPage;
    private BookStatus status;
    private LocalDate dateAdded;

    //constructor
    public Book(String title, String author, int totalPages) {
        this.title = title; //required
        this.author = author; //required
        this.totalPages = totalPages; //required
        currentPage = 0; //default
        status = BookStatus.WANT_TO_READ; //default
        dateAdded = LocalDate.now(); //default
    }

    @Override
    public String toString(){
        return "Title: " + title + "\nAuthor: " + author + "\nTotal Pages: " + totalPages + "\nCurrent Page: "
                + currentPage + "\nStatus: " + status + "\nDate Added: " + dateAdded;
    }
}
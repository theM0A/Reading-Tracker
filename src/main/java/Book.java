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

    public String getTitle() {
        return title;
    }

    // Getters so Main can read the ceiling for validation
    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public BookStatus getStatus() {
        return status;
    }

    // Setters so Main can apply the changes
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return "Title: " + title + "\nAuthor: " + author + "\nTotal Pages: " + totalPages + "\nCurrent Page: "
                + currentPage + "\nStatus: " + status + "\nDate Added: " + dateAdded;
    }
}
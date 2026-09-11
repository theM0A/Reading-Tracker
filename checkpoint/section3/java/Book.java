import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private int totalPages;
    private int currentPage;
    private BookStatus status;
    private LocalDate dateAdd;

    public Book(String title, String author, int totalPages){
        this.title = title;
        this.author = author;
        this.totalPages = totalPages;
        this.currentPage = 0;
        this.status = BookStatus.WANT_TO_READ;
        this.dateAdd = LocalDate.now();
    }

    public int getTotalPages(){
        return totalPages;
    }

    public int getCurrentPage(){
        return currentPage;
    }

    public BookStatus getStatus(){
        return status;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setStatus(BookStatus status){
        this.status = status;
    }

    @Override
    public String toString(){
        return "Title: " + this.title + "\nAuthor: " + this.author + "\nTotal Pages: " + this.totalPages +
                "\nCurrent Page: " + this.currentPage + "\nStatus: " + this.status + "\nDate: " + this.dateAdd;
    }
}
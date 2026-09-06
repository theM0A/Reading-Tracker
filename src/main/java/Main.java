import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Book> book = new ArrayList<>();

        book.add(new Book("Red Rising", "Pierce Brown", 355));
        book.add(new Book("Count de Monte Cristo", "Alexander Dumas", 1086));
        book.add(new Book("Crime and Punishment", "Fyodor Dostoevsky", 685));

        System.out.println(book);

    }
}
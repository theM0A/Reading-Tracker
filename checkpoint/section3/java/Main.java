import java.util.ArrayList;



public class Main {

    public static void main(String[] args){

        // CRUD: Add, print/view all, Update page/status, Delete

        ArrayList<Book> book = new ArrayList<>();

        // ADD
        book.add(new Book("Count de Monte Cristo", "Alexandre Dumas", 1086));
        book.add(new Book("Morning Star", "Pierce Brown", 519));
        book.add(new Book("Crime and Punishment", "Fyodor Dostoevsky", 685));

        System.out.println(book);

        // Print
        for(int i = 0; i < book.size(); i++){
            System.out.println((i + 1) + "." + book.get(i));
            System.out.println();
        }

        // Update currentPage not using user input (hardcoded)
        book.get(0).setCurrentPage(60);
        System.out.println(book.get(0));

        System.out.println();

        // Upate status hardCoded
        int max = book.get(1).getTotalPages();
        book.get(1).setCurrentPage(max);
        book.get(1).setStatus(BookStatus.FINISHED);
        System.out.println(book.get(1));

        System.out.println();

        // Delete
        System.out.println("Deleting book:\n" + book.get(2));
        book.remove(2);

        System.out.println();

        System.out.println(book);


    }
}
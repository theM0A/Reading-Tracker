import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Book> book = new ArrayList<>();

        book.add(new Book("Red Rising", "Pierce Brown", 355));
        book.add(new Book("Count de Monte Cristo", "Alexander Dumas", 1086));
        book.add(new Book("Crime and Punishment", "Fyodor Dostoevsky", 685));

        System.out.println(book);

        Scanner scanner = new Scanner(System.in);
        int number = 0;

        while (number != 6) {
            System.out.println("Menu:\n 1.View \n 2.Add \n 3.Update Page \n 4.Change Status \n 5.Delete \n 6.Exit");
            System.out.print("Enter a number to choose an option: ");
            number = scanner.nextInt();
            scanner.nextLine(); // consumes the leftover \n so next .nextLine() method doesnt pick up empty entry

            if (number == 1){
                //call the .isEmpty() method
                if (book.isEmpty()) {
                    System.out.println("No books in library yet.");
                } else {
                    for (int i = 0; i < book.size(); i++) {
                        System.out.println((i + 1) + ". " + book.get(i));
                    }
                }
            } else if (number == 2) {

                String title = "";
                String author = "";
                int totalPages = 0;

                while(title.isEmpty()) {
                    System.out.print("Enter book title:");
                    title = scanner.nextLine();
                }

                while(author.isEmpty()) {
                    System.out.print("Enter book author:");
                    author = scanner.nextLine();
                }
                while(totalPages <= 0) {
                    System.out.print("Enter total pages:");
                    totalPages = scanner.nextInt();
                }

                 book.add(new Book(title, author, totalPages));

            } else if (number == 3) {

                int bookList = 0;
                int newPage = 0;

                // Check if library is empty
                if(book.isEmpty()) {
                    System.out.println("No bookes in library yet.");
                } else {
                    // Show list of books in library
                    for (int i = 0; i < book.size(); i++){
                        System.out.println((i + 1) + "." + book.get(i).getTitle());
                    }

                    // Prompt to choose a book that'll be updated
                    System.out.print("Enter the book you want to update from the list (1,2,3):");
                    bookList = scanner.nextInt();

                    // Validate bookList choosen is in range of library list
                    while(bookList <= 0 || bookList > book.size()) {
                        System.out.print("Invalid choice: ");
                        bookList = scanner.nextInt();
                    }

                    // Show the current page
                    System.out.println(book.get(bookList - 1).getTitle() + "\n" + "Current page: " +
                            book.get(bookList - 1).getCurrentPage());

                    // Prompt for a new page
                    System.out.print("Add a new page number: ");
                    newPage = scanner.nextInt();

                    // Validate it aginst 0 and getTotalPages() method
                    while(newPage < 0 || newPage > book.get(bookList - 1).getTotalPages()) {
                        System.out.print("Invalid input: ");
                        newPage = scanner.nextInt();
                    }

                    // Call setCurrentPage() method
                    book.get(bookList -1 ).setCurrentPage(newPage);

                    // Print updated book
                    System.out.println(book.get(bookList - 1));

                }

            }
        }
        System.out.println("Goodbye!");
    }
}
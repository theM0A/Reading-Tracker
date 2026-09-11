import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {

        ArrayList<Book> book = new ArrayList<>();


        // Load Logic
        try {
            Scanner fileScanner = new Scanner(new File("books.txt"));

            while (fileScanner.hasNextLine()) {
                String title  = fileScanner.nextLine();
                String author = fileScanner.nextLine();
                String totalPages = fileScanner.nextLine();
                String currentPage = fileScanner.nextLine();
                String status = fileScanner.nextLine();
                String dateAdded = fileScanner.nextLine();
                fileScanner.nextLine();

                // Create new book object then add it to the ArrayList

                // Builds book object and stores to newBook (using the constructor)
                Book newBook = new Book(title, author, Integer.parseInt(totalPages));

                // restore field values
                newBook.setCurrentPage(Integer.parseInt(currentPage));
                newBook.setStatus(BookStatus.valueOf(status));
                newBook.setDateAdded(LocalDate.parse(dateAdded));

                // Adds newBook to book list
                book.add(newBook);



            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }


        Scanner scanner = new Scanner(System.in);
        int number = 0;

        while(number != 6) {

            number = 0;
            System.out.println("Menu:\n 1.View \n 2.Add \n 3.Update Page \n 4.Change Status \n 5.Delete \n 6.Exit");

            System.out.print("Enter a number to choose an option:");
            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("That was not a valid number");
                scanner.nextInt();
            }

            while(number <= 0 || number > 6) {
                System.out.print("Enter a menu option:");
                try {
                    number = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("That was not a valid number");
                    scanner.nextLine();
                }
            }
            scanner.nextLine(); // consumes the leftover \n so next .nextLine() method doesnt pick up empty entry

            // View library
            if(number == 1){
                //call the .isEmpty() method
                if (book.isEmpty()) {
                    System.out.println("No books in library yet.");
                } else {
                    for (int i = 0; i < book.size(); i++) {
                        System.out.println((i + 1) + ". " + book.get(i));
                    }
                }
            }  // Add book
            else if(number == 2) {

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
                    try {
                        totalPages = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("That was not a valid number");
                        scanner.nextLine();
                    }
                }

                Book newBook = new Book(title, author, totalPages);
                String statusCreate = "";
                int statusOption = 0;

                scanner.nextLine();
                System.out.print("Do you want to set book status (y/n):");
                statusCreate = scanner.nextLine();

                if(statusCreate.equals("y")){
                    System.out.println("Status options:");
                    for(int i = 0; i < BookStatus.values().length; i++){
                        System.out.println((i + 1) + "." + BookStatus.values()[i]);
                    }
                    System.out.print("Choose a status option:");
                    try {
                        statusOption = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("That was not a valid number");
                        scanner.nextLine();
                    }

                    while(statusOption <= 0 || statusOption > BookStatus.values().length){
                        System.out.println("Invalid choice:");
                        try {
                            statusOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("That was not a valid number");
                            scanner.nextLine();
                        }
                    }

                    newBook.setStatus(BookStatus.values()[statusOption - 1]);
                }

                System.out.println("\nNew Book Added"+ "\n" + "Title:" + newBook.getTitle() + "\n" + "Author:" + newBook.getAuthor() +
                        "\n" + "Total Page:" + newBook.getTotalPages()+ "\n" + "Status:" + newBook.getStatus() + "\n");

                 book.add(newBook);

            } // Update current page
            else if(number == 3) {

                int bookList = 0;
                int currentPage = 0;

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
                    try {
                        bookList= scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("That was not a valid number");
                        scanner.nextLine();
                    }

                    // Validate bookList choosen is in range of library list
                    while(bookList <= 0 || bookList > book.size()) {
                        System.out.print("Invalid choice: ");
                        try {
                            bookList= scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("That was not a valid number");
                            scanner.nextLine();
                        }
                    }

                    // Show the current page
                    System.out.println(book.get(bookList - 1).getTitle() + "\n" + "Current page: " +
                            book.get(bookList - 1).getCurrentPage());

                    // Prompt for a new page
                    System.out.print("Add a new page number: ");
                    try {
                        currentPage = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("That was not a valid number");
                        scanner.nextLine();
                    }

                    // Validate it aginst 0 and getTotalPages() method
                    while(currentPage < 0 || currentPage > book.get(bookList - 1).getTotalPages()) {
                        System.out.print("Invalid input: ");
                        try {
                            currentPage= scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("That was not a valid number");
                            scanner.nextLine();
                        }
                    }

                    // Call setCurrentPage() method
                    book.get(bookList -1 ).setCurrentPage(currentPage);

                    // Print updated book
                    System.out.println(book.get(bookList - 1));

                }
            } // Change book status
            else if(number == 4) {

                int choice = 0;

                // Book selection for status change
                System.out.println("Library:\n");
                for(int i = 0; i < book.size(); i++) {
                    //System.out.println((i + 1) + "." + book.get(i));
                    System.out.println((i + 1) + "." + book.get(i).getTitle() + "\n" + "Status:" +
                            book.get(i).getStatus() + "\n");
                }

                System.out.print("Choose book whose status you want to change (1,2,3):");
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("That was not a valid number");
                    scanner.nextLine();
                }
                scanner.nextLine();

                // Validate against book size (library)
                while(choice <= 0 || choice > book.size()) {
                    for(int i = 0; i < book.size(); i++) {
                        System.out.println((i + 1) + "." + book.get(i).getTitle() + "\n" + "Status:" +
                                book.get(i).getStatus() + "\n");
                    }
                    System.out.println();
                    System.out.print("Choose a book in your library:");
                    try {
                        choice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("That was not a valid number");
                        scanner.nextLine();
                    }
                }

                System.out.println("Title:" + book.get(choice - 1).getTitle() + "\n" + "Status:" + book.get(choice - 1).getStatus());

                System.out.println();


                // Prompt to choose status
                int statusChoice = 0;
                System.out.println("Choose a new status for book:");
                for(int i = 0; i < BookStatus.values().length; i++) {
                    System.out.println((i + 1) + "." + BookStatus.values()[i]);
                }

                System.out.print("New book status:");
                try {
                    statusChoice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("That was not a valid number");
                    scanner.nextLine();
                }

                // Validate it against BookStatus length
                while(statusChoice <= 0 || statusChoice > BookStatus.values().length) {
                    for(int i = 0; i < BookStatus.values().length; i++) {
                        System.out.println((i + 1) + "." + BookStatus.values()[i]);
                    }
                    System.out.println();
                    System.out.print("Choose a status from menu:");
                    try {
                        statusChoice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("That was not a valid number");
                        scanner.nextLine();
                    }

                }

                System.out.println();

                // Validate book status change rules
                // want to read -> currently reading
                // currently reading <-> Finished, dropped, on hold
                // on hold <-> dropped
                // no status can change to want to read
                // dropped cannot change to finish
                // on hold cannot cange to finish

                // Checking want to read status rules
                if(statusChoice == 1){
                        System.out.println("Invalid status change");
                } // Checking Currently reading status rules
                else if (statusChoice == 2) {
                    if(book.get(choice - 1).getStatus() == BookStatus.WANT_TO_READ) {
                        book.get(choice - 1).setStatus(BookStatus.CURRENTLY_READING);

                        System.out.println("Title:" + book.get(choice - 1).getTitle() + "\n" + "New Status:" +
                                book.get(choice - 1).getStatus());

                    } else if (book.get(choice - 1).getStatus() == BookStatus.FINISHED) {
                        scanner.nextLine();
                        System.out.print("Do you want to reread (y/n):");
                        String answer = scanner.nextLine();
                        if(answer.equals("y")){
                            book.get(choice - 1).setStatus(BookStatus.CURRENTLY_READING);

                            System.out.println("Title:" + book.get(choice - 1).getTitle() + "\n" + "New Status:" +
                                    book.get(choice - 1).getStatus());
                        }
                    } else if (book.get(choice - 1).getStatus() == BookStatus.DROPPED){
                        book.get(choice - 1).setStatus(BookStatus.CURRENTLY_READING);

                        System.out.println("Title:" + book.get(choice - 1).getTitle() + "\n" + "New Status:" +
                                book.get(choice - 1).getStatus());
                    } else {
                        System.out.println("Invalid status change");
                    }
                } // Checking Finished status rules
                else if(statusChoice == 3) {
                    if(book.get(choice - 1).getStatus() == BookStatus.CURRENTLY_READING){
                        book.get(choice - 1).setStatus(BookStatus.FINISHED);

                        System.out.println("Title:" + book.get(choice - 1).getTitle() + "\n" + "New status:" +
                                book.get(choice - 1).getStatus());

                    } else {
                        System.out.println("Invalid status change");
                    }

                } // Checking Dropped status rules
                else if (statusChoice == 4) {
                    if(book.get(choice - 1).getStatus() == BookStatus.CURRENTLY_READING) {
                        book.get(choice - 1).setStatus(BookStatus.DROPPED);

                        System.out.println("Title:" + book.get(choice - 1).getTitle() + "\n" + "New status:" +
                                book.get(choice - 1).getStatus());

                    } else {
                        System.out.println("Invalid status change");
                    }
                }

            } // Delete book
            else if(number == 5) {

                int deleteChoice = 0;
                if(book.isEmpty()){
                    System.out.println("No books in library.");
                }
                else {
                    System.out.println("Library:\n");
                    for(int i = 0; i < book.size(); i++) {
                        System.out.println((i + 1) + "." + book.get(i).getTitle());
                    }

                    System.out.print("Choose a book to delete (enter number):");
                    try {
                         deleteChoice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("That was not a valid number");
                        scanner.nextLine();
                    }

                    while(deleteChoice <= 0 || deleteChoice > book.size()) {
                        System.out.print("Invalid choice:");
                        try {
                            deleteChoice = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("That was not a valid number");
                            scanner.nextLine();
                        }
                    }

                    System.out.println("\nYou want to delete: " + book.get(deleteChoice - 1).getTitle());
                    System.out.print("\nAre you sure you want to delete this book (y/n):");
                    scanner.nextLine();
                    String deleteConfirm = scanner.nextLine();

                    if(deleteConfirm.equals("y")) {
                        System.out.println("\nYou deleted: " + book.get(deleteChoice - 1).getTitle() + " from your library.");
                        book.remove(deleteChoice - 1);
                    }
                }
            }
        }

        // Save Logic
        try {
            PrintWriter writer = new PrintWriter("books.txt");

            for(int i = 0; i < book.size(); i++) {
                writer.println(book.get(i).getTitle() + "\n" + book.get(i).getAuthor() + "\n" +
                        book.get(i).getTotalPages() + "\n" + book.get(i).getCurrentPage() + "\n" +
                        book.get(i).getStatus() + "\n" + book.get(i).getDateAdded());
                writer.println();
            }

            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't create file");
        }


        System.out.println("Goodbye!");
    }
}
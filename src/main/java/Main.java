import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Book> book = new ArrayList<>();

        book.add(new Book("Red Rising", "Pierce Brown", 355));
        book.add(new Book("Count de Monte Cristo", "Alexander Dumas", 1086));
        book.add(new Book("Crime and Punishment", "Fyodor Dostoevsky", 685));

        System.out.println(book);

        Scanner input = new Scanner(System.in);
        int number = 0;

        while (number != 6) {
            System.out.println("Menu:\n 1.View \n 2.Add \n 3.Update Page \n 4.Change Status \n 5.Delete \n 6.Exit");
            System.out.print("Enter a number to choose an option: ");
            number = input.nextInt();
        }
        System.out.println("Goodbye!");
    }
}
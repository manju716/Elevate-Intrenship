package Notes;

import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== NOTES APP =====");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Clear All Notes");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addNote(scanner);
                    break;

                case 2:
                    viewNotes();
                    break;

                case 3:
                    clearNotes();
                    break;

                case 4:
                    System.out.println("Exiting... Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option. Try again!");
            }
        }
    }

    private static void addNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) { // append mode
            writer.write(note + System.lineSeparator());
            System.out.println("Note saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }


    private static void viewNotes() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No notes found.");
            return;
        }

        System.out.println("\n--- Saved Notes ---");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
            }

        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }

 
    private static void clearNotes() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) { 
            writer.write("");
            System.out.println("All notes cleared.");
        } catch (IOException e) {
            System.out.println("Error clearing notes: " + e.getMessage());
        }
    }
}

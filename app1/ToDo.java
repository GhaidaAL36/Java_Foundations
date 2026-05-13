package app1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class ToDo {

    Scanner scanner;

    // I created task class to manage the task
    static class Task {
        String name;
        boolean completed;

        Task(String name) {
            this.name = name;
            this.completed = false;
        }
    }

    // constructor to pass the scanner
    public ToDo(Scanner scanner) {
        this.scanner = scanner;
    }

    // add task - first it checks if it's already exists
    // if not it will be added successfully
    public void addTask(ArrayList<Task> list) {
        System.out.println("Enter task:");
        String name = scanner.nextLine();

        for (Task t : list) {
            if (t.name.equalsIgnoreCase(name)) {
                System.out.println("Task already exists");
                return;
            }
        }

        list.add(new Task(name));
        System.out.println(name + " added to the list");
    }

    // it will check completed to put mark it was completed
    public void showList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println("Your list is empty");
            return;
        }

        for (Task t : list) {
            if (t.completed) {
                System.out.println("✓ " + t.name);
            } else {
                System.out.println(t.name);
            }
        }
    }

    public void markTask(ArrayList<Task> list) {
        System.out.println("Enter task to mark:");
        String name = scanner.nextLine();

        for (Task t : list) {
            if (t.name.equalsIgnoreCase(name)) {
                t.completed = true;
                System.out.println("Task marked as completed");
                return;
            }
        }

        System.out.println("Item not found");
    }

    public void deleteTask(ArrayList<Task> list) {
        System.out.println("Enter task to delete:");
        String name = scanner.nextLine();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).name.equalsIgnoreCase(name)) {
                list.remove(i);
                System.out.println(name + " was deleted from the list");
                return;
            }
        }

        System.out.println("Item not found");
    }

    public void showMenu(ArrayList<Task> list) {
        while (true) {
            System.out.println("\nSelect from the menu:");
            System.out.println("1- Show list");
            System.out.println("2- Add task");
            System.out.println("3- Mark task as complete");
            System.out.println("4- Delete task");
            System.out.println("5- Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> showList(list);
                case "2" -> addTask(list);
                case "3" -> markTask(list);
                case "4" -> deleteTask(list);
                case "5" -> {
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }


    public void saveToFile(ArrayList<Task> list) {
        try (FileWriter writer = new FileWriter("todo.txt")) {

            for (Task t : list) {
                writer.write(t.name + "|" + t.completed + "\n");
            }

            System.out.println("List saved to file");

        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

    public void loadFromFile(ArrayList<Task> list) {
        try {
            File file = new File("todo.txt");
            if (!file.exists()) return;

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length != 2) continue;

                Task t = new Task(parts[0]);
                t.completed = Boolean.parseBoolean(parts[1]);

                list.add(t);
            }

            fileScanner.close();

        } catch (IOException e) {
            System.out.println("Error loading file");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> toDoList = new ArrayList<>();

        ToDo todo = new ToDo(scanner);

        todo.loadFromFile(toDoList);
        todo.showMenu(toDoList);
        todo.saveToFile(toDoList);
    }
}
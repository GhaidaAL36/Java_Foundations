package app1;

import java.util.Scanner;
import java.util.ArrayList;

public class ToDo {

    Scanner scanner;

    static class Task {
        String name;
        boolean completed;

        Task(String name) {
            this.name = name;
            this.completed = false;
        }
    }

    public ToDo(Scanner scanner) {
        this.scanner = scanner;
    }

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> toDoList = new ArrayList<>();

        ToDo todo = new ToDo(scanner);
        todo.showMenu(toDoList);
    }
}
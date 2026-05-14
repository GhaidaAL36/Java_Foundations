package extra_exercises;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CountEvenAndOdd {
    Scanner scanner;

    public CountEvenAndOdd(Scanner scanner) {
        this.scanner = scanner;
    }

    public int setSize() {
        int size;
        while (true) {
            try {
                System.out.println("Enter size for the array");
                size = scanner.nextInt();

                if (size < 1 || size > 20) {
                    System.out.println("Enter size between 1 and 20");
                    continue;
                }
                break;

            } catch (InputMismatchException e) {
                System.out.println("Enter valid number");
                scanner.next();
                continue;
            }
        }
        return size;
    }

    public int[] createArr(int size) {
        int[] arr = new int[size];
        System.out.println("Created array with size of " + size);
        return arr;
    }

    public void fillArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Enter element " + (i + 1));
            try {
                arr[i] = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter valid number");
                scanner.next();
                i--;
            }
        }
    }

    public int countEven(int[] arr) {
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public int countOdd(int[] arr) {
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CountEvenAndOdd evenAndOdd = new CountEvenAndOdd(scanner);

        int size = evenAndOdd.setSize();
        int[] arr = evenAndOdd.createArr(size);
        evenAndOdd.fillArray(arr);
        System.out.println("Number of even numbers is: " + evenAndOdd.countEven(arr));
        System.out.println("Number of odd numbers is: " + evenAndOdd.countOdd(arr));


    }
}
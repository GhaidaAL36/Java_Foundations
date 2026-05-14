package extra_exercises;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MaxAndMin {
    Scanner scanner;

    public MaxAndMin(Scanner scanner) {
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

    public int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max <= arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }

    public int findMin(int[] arr) {
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (min >= arr[i]) {
                min = arr[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MaxAndMin maxAndmin = new MaxAndMin(scanner);

        int size = maxAndmin.setSize();
        int[] arr = maxAndmin.createArr(size);
        maxAndmin.fillArray(arr);

        System.out.println("Maximum value is: " + maxAndmin.findMax(arr));
        System.out.println("Minimum value is: " + maxAndmin.findMin(arr));

    }
}
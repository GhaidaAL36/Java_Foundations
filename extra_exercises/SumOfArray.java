package extra_exercises;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SumOfArray {
    static Scanner scann = new Scanner(System.in);

    public int setSize() {
        int size;
        while (true) {
            try {
                System.out.println("Enter size for the array");
                size = scann.nextInt();

                if (size < 1 || size > 20) {
                    System.out.println("Enter size between 1 and 20");
                    continue;
                }
                break;

            } catch (InputMismatchException e) {
                System.out.println("Enter valid number");
                scann.next();
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

    public int[] fillArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Enter element " + i);
            try {
                arr[i] = scann.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter valid number");
                scann.next();
                i--;
            }
        }
        return arr;
    }

    public int arraySum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        SumOfArray obj = new SumOfArray();
        int[] arr;

        int size = obj.setSize();
        arr = obj.createArr(size);
        obj.fillArray(arr);
        System.out.println("your array: " + Arrays.toString(arr));
        System.out.println("Sum of your array: " + obj.arraySum(arr));

    }

}



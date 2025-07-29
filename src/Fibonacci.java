import java.util.Scanner;

public class Fibonacci {


    public static void fibonacciNonRecursive(int n) {
        int a = 0, b = 1;
        System.out.print(a + " ");
        if (n > 1) System.out.print(b + " ");

        for (int i = 2; i < n; i++) {
            int c = a + b;
            System.out.print(c + " ");
            a = b;
            b = c;
        }
        System.out.println();
    }

    // Standard Recursive Method
    public static int fibonacciRecursive(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of Fibonacci numbers: ");
        int n = sc.nextInt();

        System.out.println("\n--- Non-Recursive Fibonacci ---");
        fibonacciNonRecursive(n);

        System.out.println("\n--- Recursive Fibonacci ---");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciRecursive(i) + " ");
        }

        sc.close();
    }
}

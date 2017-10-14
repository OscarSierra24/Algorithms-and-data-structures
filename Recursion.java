public class Recursion {
    static int n1 = 0, n2 = 1, n3 = 0;
    static int count1 = 0;
     
    static void printFibo(int count) {
        if (count > 0) {
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
            System.out.print(" " + n3);
            printFibo(count - 1);
        }
    }
 
    static int printFactorial(int n) {
        if (n == 1)
            return 1;
        else
            return (n * printFactorial(n - 1));
    }
 
 
 
    static void printCount() {
        count1++;
        if (count1 <= 5) {
            System.out.println("hello " + count1);
            printCount();
        }
    }
 
    public static void main(String[] args) {
        // FIBONACCI
        int count = 15;
        System.out.print(n1 + " " + n2);// printing 0 and 1
        printFibo(count - 2);// n-2 because 2 numbers are already printed
 
        // FACTORIAL
        int fac = 5;
        System.out.println("\nFactorial of " + fac + " is: " + printFactorial(fac));
 
        // FINITE RECURSION
        //uses declared count1
        printCount();
    }
}
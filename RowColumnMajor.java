public class RowColumnMajor{
    static int[][] arrA = new int[3][3];
    static int[][][] arrB = new int[3][3][3];
 
    public static void main(String[] args) {
        int[][] arrA = { 
                { 1, 2, 3 }, 
                { 4, 5, 6 }, 
                { 7, 8, 9 } 
                };
        int[][][] arrB = { 
                {
                    { 1, 2, 3 }, 
                    { 4, 5, 6 }, 
                    { 7, 8, 9 } 
                },
                {
                    { 1, 2, 3 }, 
                    { 4, 5, 6 }, 
                    { 7, 8, 9 } 
                },
                {
                    { 1, 2, 3 }, 
                    { 4, 5, 6 }, 
                    { 7, 8, 9 } 
                },
                 
                };
        rowMayor(arrA);
        columnMayor(arrA);
        rowMayor(arrB);
        columnMayor(arrB);
    }
 
    public static void rowMayor(int[][] a) {
        System.out.println("\nRow Mayor");
        for (int r = 0; r < a.length; r++) {
            for (int c = 0; c < a[0].length; c++) {
                System.out.print(a[r][c]+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }
 
    public static void columnMayor(int[][] a) {
        System.out.println("\nColumn Mayor");
        for (int r = 0; r < a[0].length; r++) {
            for (int c = 0; c < a.length; c++) {
                System.out.print(a[c][r]+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }
     
    public static void rowMayor(int[][][] a) {
        System.out.println("\nRow Mayor");
        for (int r = 0; r < a[0].length; r++) {
            for (int c = 0; c < a[1].length; c++) {
                for (int d = 0; d < a.length; d++) {
                    System.out.print(a[c][r][d] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();
    }
 
    public static void columnMayor(int[][][] a) {
        System.out.println("\nColumn Mayor");
        for (int r = 0; r < a[0].length; r++) {
            for (int c = 0; c < a[1].length; c++) {
                for (int d = 0; d < a.length; d++) {
                    System.out.print(a[d][c][r] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();
    }
}
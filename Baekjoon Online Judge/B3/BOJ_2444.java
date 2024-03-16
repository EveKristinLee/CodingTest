//BOJ B3 2444 별 찔기 - 7

import java.util.Scanner;

public class BOJ_2444 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int end = (N * 2) - 1;
        int star = 1;
        int blank = N-1;
        boolean minus = true;
        for(int i=0; i<end; i++) {
            for(int j=0; j<blank; j++) {
                System.out.print(" ");
            }
            for(int j=0; j<star; j++) {
                System.out.print("*");
            }
            if(blank > 0 && minus) {
                star += 2;
                blank--;
            }
            else {
                minus = false;
                star -= 2;
                blank++;
            }
            System.out.println();
        }
    }
}

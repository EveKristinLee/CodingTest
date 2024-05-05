//BOJ B3 2445 별 찍기 - 8

import java.util.Scanner;

public class BOJ_2445 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();
        int star = 1;
        int blank = (N*2)-2;
        for(int i=0; i<(N*2)-1; i++) {
            for(int j=0; j<star; j++) {
                sb.append("*");
            }
            for(int j=0; j<blank; j++) {
               sb.append(" ");
            }
            for(int j=0; j<star; j++) {
                sb.append("*");
            }
            sb.append("\n");
            if(i < N-1) {
                star += 1;
                blank -= 2;
            }
            else {
                star -= 1;
                blank += 2;
            }
        }
        System.out.println(sb);
    }
}

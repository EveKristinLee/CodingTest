//BOJ B3 2446 별 찍기 - 9

import java.util.Scanner;

public class BOJ_2446 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();
        int star = (N*2)-1;
        int blank = 0;
        for(int i=0; i<(N*2)-1; i++) {
            for(int j=0; j<blank; j++) {
                sb.append(" ");
            }
            for(int j=0; j<star; j++) {
                sb.append("*");
            }
            sb.append("\n");
            if(i < N-1) {
                blank+=1;
                star-=2;
            }
            else {
                blank-=1;
                star+=2;
            }
        }
        System.out.println(sb);
    }
}

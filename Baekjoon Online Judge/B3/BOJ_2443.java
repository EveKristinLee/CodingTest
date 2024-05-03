//BOJ B3 2443 별 찍기 - 6

import java.util.Scanner;

public class BOJ_2443 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();
        int blank = 0;
        int cnt = (N*2)-1;
        for(int i=0; i<N; i++) {
            for(int j=0; j<blank; j++) {
                sb.append(" ");
            }
            for(int j=0; j<cnt; j++) {
                sb.append("*");
            }
            sb.append("\n");
            blank++;
            cnt-=2;
        }
        System.out.println(sb);
    }
}

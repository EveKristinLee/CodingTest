package solving.solve_1028;
//BOJ G4 2448 별 찍기 - 11

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2448 {
    static char star[][];
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        N = sc.nextInt();
        star = new char[N+1][(N*2)+1];
        for(int i=0; i<=N; i++) {
            Arrays.fill(star[i], '0');
        }
        drawStar(1, N, N);
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N*2; j++) {
                if(star[i][j] == '0') {
                    sb.append(" ");
                }
                else {
                    sb.append("*");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void drawStar(int x, int y, int depth) {
        if(depth == 3) {
            star[x+0][y] = '*';
            star[x+1][y-1] = '*';
            star[x+1][y+1] = '*';
            int ny = y-2;
            for(int i=0; i<5; i++) {
                star[x+2][ny+i] = '*';
            }
            return;
        }

        int now = depth/2;
        drawStar(x, y, now); //제일 위
        drawStar(x+now, y+now, now);
        drawStar(x+now, y-now, now);
    }
}

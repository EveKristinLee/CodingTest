package algo_0822;
//BOJ S2 1780 종이의 개수

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1780 {
    static int N;
    static int[][] map;
    static int cntMinus;
    static int cntZero;
    static int cntOne;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cntMinus = 0;
        cntZero = 0;
        cntOne = 0;

        calcCnt(1, 1, N, N);
        System.out.println(cntMinus);
        System.out.println(cntZero);
        System.out.println(cntOne);
    }

    private static void calcCnt(int sx, int sy, int ex, int ey) {
        if(isOneNum(map[sx][sy], sx, sy, ex, ey)) { //하나의 숫자라면
            if(map[sx][sy] == -1) {
                cntMinus++;
            }
            else if(map[sx][sy] == 0) {
                cntZero++;
            }
            else if(map[sx][sy] == 1) {
                cntOne++;
            }
            return;
        }

        int divCnt = (ex - sx + 1)/3;
        int endCnt = divCnt-1;
        int nowSx = sx;
        int nowSy = sy;
        int nowEx = sx+endCnt;
        int nowEy = sy+endCnt;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                calcCnt(nowSx, nowSy, nowEx, nowEy);
                nowSy += divCnt;
                nowEy += divCnt;
            }
            nowSx += divCnt;
            nowEx += divCnt;
            nowSy = sy;
            nowEy = sy+endCnt;
        }
    }

    private static boolean isOneNum(int num, int sx, int sy, int ex, int ey) {
        for(int i=sx; i<=ex; i++) {
            for(int j=sy; j<=ey; j++) {
                if(map[i][j] != num) {
                    return false;
                }
            }
        }
        return true;
    }
}

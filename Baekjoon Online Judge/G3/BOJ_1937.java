package solving.solve_0925;
//BOJ G3 1937 욕심쟁이 판다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1937 {
    static int N;
    static int[][] map;
    static int[][] dp;
    static int res;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    private static boolean isInside(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=N) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                go(i, j);
            }
        }
        System.out.println(res + 1);
    }

    private static int go(int x, int y) {
        if(dp[x][y] != 0) {
            return dp[x][y];
        }

        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(!isInside(nx, ny)){
                continue;
            }
            if(map[nx][ny] > map[x][y]) {
                dp[x][y] = Math.max(dp[x][y] , go(nx, ny) + 1);
            }
        }

        res = Math.max(res, dp[x][y]);
        return dp[x][y];
    }
}

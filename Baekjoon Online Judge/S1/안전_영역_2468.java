package algo_0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 안전_영역_2468 {
    static int[][] map;
    static int N;
    static boolean[][] visit;
    static int maxH;
    static int res;
    static int cnt;

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
        maxH = Integer.MIN_VALUE;
        res = Integer.MIN_VALUE;

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxH = Math.max(maxH, map[i][j]);
            }
        }

        while(maxH-- >= 0) {
            cnt = 0;
            visit = new boolean[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] > maxH && !visit[i][j]) {
                        cnt++;
                        dfs(i, j);
                    }
                }
            }
            res = Math.max(res, cnt);
        }
        System.out.println(res);
    }

    private static void dfs(int x, int y) {
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(isInside(nx, ny)) {
                if(map[nx][ny] > maxH && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    dfs(nx, ny);
                }
            }
        }
    }
}

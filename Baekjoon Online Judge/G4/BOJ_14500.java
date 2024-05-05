package solving.solve_1028;
//BOJ G4 14500 테트로미노

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {

    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visit;
    static int res;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1}; //상, 하, 좌, 우
    static int[][][] five = {
            {{-1, 0}, {1, 0}, {0, 1}},  //ㅏ
            {{0, -1}, {0, 1}, {1, 0}},  //ㅜ
            {{0, -1}, {-1, 0}, {1, 0}}, //ㅏ
            {{0, -1}, {-1, 0}, {0, 1}}  //ㅗ
    };

    private static boolean isInside(int x, int y) {
        if(x<0||y<0||x>=N||y>=M) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        res = Integer.MIN_VALUE;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                visit[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visit[i][j] = false;

                chkFive(i, j);
            }
        }

        System.out.println(res);
    }

    private static void dfs(int x, int y, int cnt, int sum) {
        if(cnt == 4) {
            res = Math.max(res, sum);
            return;
        }

        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(isInside(nx, ny) && !visit[nx][ny]) {
                visit[nx][ny] = true;
                dfs(nx, ny, cnt+1, sum+map[nx][ny]);
                visit[nx][ny] = false;
            }
        }
    }

    private static void chkFive(int x, int y) {
        for(int i=0; i<4; i++) {
            int sum = map[x][y];
            boolean flag = true;
            for(int j=0; j<3; j++) {
                int nx = x + five[i][j][0];
                int ny = y + five[i][j][1];
                if(isInside(nx, ny)) {
                    sum += map[nx][ny];
                }
                else {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                res = Math.max(sum, res);
            }
        }
    }
}

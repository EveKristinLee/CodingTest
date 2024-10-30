package solving.solve_1028;
//BOJ G4 2665 미로만들기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ_2665 {
    private static class Pos {
        int x;
        int y;
        int cnt;
        public Pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static int N;
    static int[][] map; //0은 검은방, 1은 흰방
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int res;

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
        for(int i=0; i<N; i++) {
            String s = br.readLine();
            for(int j=0; j<N; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        res = Integer.MAX_VALUE;


        bfs();
        System.out.println(res);
    }

    private static void bfs() {
        Queue<Pos> q = new ArrayDeque<>();
        int[][] visit = new int[N][N];
        for(int i=0; i<N; i++) {
            Arrays.fill(visit[i], 987654321);
        }
        q.offer(new Pos(0, 0, 0));
        visit[0][0] = 0;

        while(!q.isEmpty()) {
            Pos now = q.poll();
//            System.out.println("now.x = " + now.x + " now.y = " + now.y + " now.cnt = " +now.cnt);
            if(now.x == N-1 && now.y == N-1) {
                res = Math.min(res, visit[now.x][now.y]);
            }

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(!isInside(nx, ny)) {
                    continue;
                }

                if(map[nx][ny] == 1) { // 흰방
                    if(now.cnt < visit[nx][ny]) {
                        visit[nx][ny] = now.cnt;
                        q.offer(new Pos(nx, ny, now.cnt));
                    }
                }
                if(map[nx][ny] == 0) { //검은방
                    if(now.cnt + 1 < visit[nx][ny]) {
                        visit[nx][ny] = now.cnt+1;
                        q.offer(new Pos(nx, ny, now.cnt+1));
                    }
                }
            }
        }
    }
}

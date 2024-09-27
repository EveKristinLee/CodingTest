package solving.solve_1028;
//BOJ G4 14923 미로 탈출

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14923 {
    static class Pos {
        int x;
        int y;
        int sec;
        boolean use;

        public Pos(int x, int y, int sec, boolean use) {
            this.x = x;
            this.y = y;
            this.sec = sec;
            this.use = use;
        }
    }
    static int N;
    static int M;
    static int[][] map;
    static int Hx;
    static int Hy;
    static int Ex;
    static int Ey;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    private static boolean isInside(int x, int y) {
        if(x<=0 || y<=0 || x>N || y>M) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Hx = Integer.parseInt(st.nextToken());
        Hy = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Ex = Integer.parseInt(st.nextToken());
        Ey = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Pos> q = new ArrayDeque<>();
        boolean[][][] visit = new boolean[N+1][M+1][2];
        q.offer(new Pos(Hx, Hy, 0, false));
        visit[Hx][Hy][0] = true;

        while(!q.isEmpty()) {
            Pos now = q.poll();
//            System.out.println(now.x + " : " + now.y + " : " + now.use);
            if(now.x == Ex && now.y == Ey) {
                return now.sec;
            }

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(!isInside(nx, ny)) {
                    continue;
                }
                if(now.use) {
                    if(visit[nx][ny][1]) {
                        continue;
                    }
                }
                else {
                    if(visit[nx][ny][0]) {
                        continue;
                    }
                }

                if(map[nx][ny] == 0) {
                    if(now.use) {
                        visit[nx][ny][1] = true;
                        q.offer(new Pos(nx, ny, now.sec+1, now.use));
                    }
                    else {
                        visit[nx][ny][0] = true;
                        q.offer(new Pos(nx, ny, now.sec+1, now.use));
                    }
                }

                if(map[nx][ny] == 1) {
                    if(!now.use) {
                        visit[nx][ny][1] = true;
                        q.offer(new Pos(nx, ny, now.sec+1, true));
                    }
                }

            }
        }

        return -1;
    }
}

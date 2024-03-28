package solving;
//BOJ S1 14940 쉬운 최단거리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14940 {
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
    static int M;
    static int[][] dist;
    static int[][] map;
    static boolean[][] visit;
    static Queue<Pos> q;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    private static boolean isInside(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=M) {
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
        dist = new int[N][M];
        visit = new boolean[N][M];
        for(int i=0; i<N; i++) {
            Arrays.fill(dist[i], -1);
        }
        q = new ArrayDeque<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    q.offer(new Pos(i, j, 0));
                    dist[i][j] = 0;
                    visit[i][j] = true;
                }
                else if(map[i][j] == 0) {
                    dist[i][j] = 0;
                }
            }
        }

        bfs();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void bfs() {
        while(!q.isEmpty()) {
            Pos now = q.poll();

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(!isInside(nx, ny) || visit[nx][ny]) {
                    continue;
                }
                visit[nx][ny] = true;
                if(map[nx][ny] == 0) {
                    dist[nx][ny] = 0;
                }
                else if(map[nx][ny] == 1) {
                    dist[nx][ny] = now.cnt + 1;
                    q.offer(new Pos(nx, ny, now.cnt + 1));
                }
            }
        }
    }
}

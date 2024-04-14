package solving.solve_0914;
//BOJ G5 7569 토마토

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569 {
    private static class Pos {
        int x;
        int y;
        int z;

        public Pos(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    static int M, N; //가로, 세로
    static int H;
    static int[][][] map;
    static boolean[][][] visit;
    static Queue<Pos> q;
    static int remain;
    static int[] dx = {0, 0, 1, -1, 0, 0};
    static int[] dy = {1, -1, 0, 0, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    private static boolean isInside(int x, int y, int z) {
        if(x<0 || y<0 || z<0 || x>=N || y>=M || z>=H) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[N][M][H];
        visit = new boolean[N][M][H];
        q = new ArrayDeque<>();
        remain = M * N * H;
        for(int h=0; h<H; h++) {
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++) {
                    map[i][j][h] = Integer.parseInt(st.nextToken());
                    if(map[i][j][h] == 1) {
                        q.offer(new Pos(i, j, h));
                        visit[i][j][h] = true;
                        remain--;
                    }
                    if(map[i][j][h] == -1) {
                        visit[i][j][h] = true;
                        remain--;
                    }
                }
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        int day = 0;
        while(!q.isEmpty()) {
            if(isAll()) {
                return day;
            }
            int size = q.size();
            for(int i=0; i<size; i++) {
                Pos now = q.poll();
                for(int d=0; d<6; d++) {
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];
                    int nz = now.z + dz[d];
                    if(!isInside(nx, ny, nz)) {
                        continue;
                    }

                    if(!visit[nx][ny][nz] && map[nx][ny][nz] == 0) {
                        remain--;
                        visit[nx][ny][nz] = true;
                        q.offer(new Pos(nx, ny, nz));
                    }
                }
            }
            day++;
        }
        if(isAll()) {
            return day;
        }
        return -1;
    }

    private static boolean isAll() {
        if(remain > 0) {
            return false;
        }
        return true;
    }
}

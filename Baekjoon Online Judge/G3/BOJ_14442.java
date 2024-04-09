package solving.solve_0925;
//BOJ G3 14442 벽 부수고 이동하기 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14442 {
    private static class Pos {
        int x;
        int y;
        int cnt;
        int k;

        public Pos(int x, int y, int cnt, int k) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.k = k;
        }
    }
    static int N; //세로
    static int M; //가로
    static int K; //부술 수 있는 벽의 개수
    static char[][] map;
    static boolean[][][] visit;
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
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visit = new boolean[N][M][K+1];
        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Pos> q = new ArrayDeque<>();
        if(map[0][0] == '1') {
            if(K>0) {
                q.offer(new Pos(0, 0, 1, K-1));
                visit[0][0][K-1] = true;
            }
        }else {
            q.offer(new Pos(0, 0, 1, K));
            visit[0][0][K] = true;
        }

        while(!q.isEmpty()) {
            Pos now = q.poll();
            if(now.x == N-1 && now.y == M-1) {
                return now.cnt;
            }

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(!isInside(nx, ny)) {
                    continue;
                }
                if(map[nx][ny] == '0') {
                    if(!visit[nx][ny][now.k]) {
                        visit[nx][ny][now.k] = true;
                        q.offer(new Pos(nx, ny, now.cnt+1, now.k));
                    }
                }
                else if(map[nx][ny] == '1') {
                    if(now.k > 0) {
                        if(!visit[nx][ny][now.k-1]) {
                            visit[nx][ny][now.k-1] = true;
                            q.offer(new Pos(nx, ny, now.cnt+1, now.k-1));
                        }
                    }
                }
            }
        }

        return -1;
    }
}

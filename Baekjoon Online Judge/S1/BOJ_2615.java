package solving;
//BOJ S1 2615 오목

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2615 {
    private static class Pos implements Comparable<Pos>{
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Pos p) {
            if(this.y == p.y) {
                return this.x - p.x;
            }
            return this.y - p.y;
        }
    }
    static final int N = 19;
    static int[][] map;
    static boolean[][][] visit;
    static boolean black;
    static boolean white;
    static PriorityQueue<Pos> pq;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, 1, -1}; //상, 하, 좌, 우, 좌상, 우상, 우하, 좌하

    private static boolean isInside(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=N) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[N][N];
        visit = new boolean[N][N][8]; //x, y, dir
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        black = false;
        white = false;
        pq = new PriorityQueue<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(int dir=0; dir<8; dir++) {
                    if(black || white) {
                        break;
                    }
                    if(!visit[i][j][dir] && map[i][j] != 0) {
                        pq.clear();
                        chk(i, j, dir, 1);
                    }
                }
            }
        }

        if(black) {
            System.out.println(1);
            Pos now = pq.poll();
            System.out.println((now.x + 1) + " " + (now.y + 1));
        }
        else if(white) {
            System.out.println(2);
            Pos now = pq.poll();
            System.out.println((now.x + 1) + " " + (now.y + 1));
        }
        else {
            System.out.println(0);
        }

    }

    private static void chk(int x, int y, int dir, int cnt) {
        pq.offer(new Pos(x, y));
        if(cnt >= 6) { //여섯 알 이상이 연속적으로 놓인 경우 이긴 것이 아님
            if(map[x][y] == 1) {
                black = false;
            }
            if(map[x][y] == 2) {
                white = false;
            }
            pq.clear();
            return;
        }

        if(cnt == 5) {
            if(map[x][y] == 1) {
                black = true;
            }
            if(map[x][y] == 2) {
                white = true;

            }
        }

        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if(!isInside(nx, ny)) {
            return;
        }
        if(visit[nx][ny][dir]) {
            return;
        }
        if(map[nx][ny] != map[x][y]) {
            return;
        }

        visit[nx][ny][dir] = true;
        chk(nx, ny, dir, cnt+1);
    }
}

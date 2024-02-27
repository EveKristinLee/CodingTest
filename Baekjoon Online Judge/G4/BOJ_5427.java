package solving.solve_1028;
//BOJ G4 5427 불

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5427 {
    private static class Pos{
        int x;
        int y;
        int cnt;
        public Pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    static int W;
    static int H;
    static char[][] map;
    static boolean[][] visit;
    static Queue<Pos> fire;
    static Queue<Pos> q;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    private static boolean isInside(int x, int y) {
        if(x<0 || y<0 || x>=H || y>=W) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            visit = new boolean[H][W];
            q = new ArrayDeque<>();
            fire = new ArrayDeque<>();
            for(int i=0; i<H; i++) {
                String s = br.readLine();
                map[i] = s.toCharArray();
                for(int j=0; j<W; j++) {
                    if(map[i][j] == '@'){
                        q.offer(new Pos(i, j, 1));
                    }
                    if(map[i][j] == '*') {
                        fire.offer(new Pos(i, j, -1));
                    }
                }
            }
            findPath();
        }
    }

    private static void findPath() {
        while(!q.isEmpty()) {
            int fSize = fire.size();
            for(int i=0; i<fSize; i++) {
                Pos nowF = fire.poll();
                for(int d=0; d<4; d++) {
                    int nx = nowF.x + dx[d];
                    int ny = nowF.y + dy[d];
                    if(!isInside(nx, ny)) {
                        continue;
                    }

                    if(!visit[nx][ny] && map[nx][ny] != '#') {
                        visit[nx][ny] = true;
                        map[nx][ny] = '*';
                        fire.offer(new Pos(nx, ny, -1));
                    }
                }
            }

            int sSize = q.size();
            for(int i=0; i<sSize; i++) {
                Pos now = q.poll();
                for(int d=0; d<4; d++) {
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];
                    if(!isInside(nx, ny)) {
                        if(now.cnt > 0) {
                            System.out.println(now.cnt);
                            return;
                        }
                        continue;
                    }if(!visit[nx][ny] && map[nx][ny] == '.') {
                        visit[nx][ny] = true;
                        map[nx][ny] = '@';
                        q.offer(new Pos(nx, ny, now.cnt+1));
                    }
                }
            }

        }
        System.out.println("IMPOSSIBLE");
    }
}

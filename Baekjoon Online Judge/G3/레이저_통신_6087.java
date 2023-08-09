//BOJ G3
package solving.solve_0925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 레이저_통신_6087 {
    static int W; //가로
    static int H; //세로
    static char[][] map;
    static int sx;
    static int sy;
    static int ex;
    static int ey;
    static int res;
    static int[][] visit;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1}; //상, 하, 좌, 우

    private static boolean isInside(int x, int y) {
        if(x<0 || y<0 || x>=H || y>=W) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        visit = new int[H][W];
        for(int i=0; i<H; i++) {
            Arrays.fill(visit[i], Integer.MAX_VALUE);
        }
        boolean existStart = false;
        for(int i=0; i<H; i++) {
            String s = br.readLine();
            map[i] = s.toCharArray();
            for(int j=0; j<W; j++) {
                if(!existStart && map[i][j] == 'C'){
                    sx = i;
                    sy = j;
                    existStart = true;
                }
                else if(existStart && map[i][j] == 'C') {
                    ex = i;
                    ey = j;
                }
            }
        }
        res = Integer.MAX_VALUE;
        bfs(sx, sy);
        System.out.println(res-1);
    }

    private static void bfs(int x, int y) {
        PriorityQueue<Integer[]> q = new PriorityQueue<>((o1, o2) -> {
            int cnt1 = o1[2];
            int cnt2 = o2[2];

            return cnt1 - cnt2;
        });

        q.offer(new Integer[] {x, y, 0, -1});

        visit[x][y] = 0;

        while (!q.isEmpty()) {
            Integer[] top = q.poll();
            x = top[0];
            y = top[1];
            int cnt = top[2];
            int dir = top[3];

            if(x == ex && y == ey) {
                res = cnt;
                return;
            }

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nc = cnt;
                int nd = i;
                if(!isInside(nx, ny) || map[nx][ny] == '*') {
                    continue;
                }
                if(nd != -1 && nd != dir) { //시작점이 아니고, 방향이 꺾이면
                    if(visit[nx][ny] >= nc + 1) {
                        visit[nx][ny] = nc + 1;
                        q.offer(new Integer[] {nx, ny, nc + 1, nd});
                    }
                }
                else { //방향이 같으면
                    if(visit[nx][ny] >= nc) {
                        visit[nx][ny] = nc;
                        q.offer(new Integer[] {nx, ny, nc, nd});
                    }
                }
            }
        }
    }
}

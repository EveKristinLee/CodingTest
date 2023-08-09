//BOJ G3
package solving.solve_0925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 레이저_통신_6087 {
    static class L implements Comparable<L>{
        int x;
        int y;
        int cnt;
        int dir;

        public L(int x, int y, int cnt, int dir) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dir = dir;
        }

        @Override
        public int compareTo(L o) {
            return this.cnt - o.cnt;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int W; //가로
    static int H; //세로
    static char[][] map;
    static int sx, sy;
    static int INF = 100 * 100 + 1;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1}; //상, 하, 좌, 우

    private static boolean isInside(int x, int y) {
        if(x<0 || y<0 || x>=H || y>=W) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        boolean existStart = false;
        for(int i=0; i<H; i++) {
            String s = br.readLine();
            for(int j=0; j<W; j++) {
                map[i][j] = s.charAt(j);
                if(!existStart && map[i][j] == 'C'){
                    sx = i;
                    sy = j;
                    map[i][j] = 'S';
                    existStart = true;
                }
            }
        }
        bfs();
    }

    private static void bfs() {
        Queue<L> q = new PriorityQueue<>();
        //visit배열에 x,y지점에서 거울의 최소 개수를 저장하는데,
        //2차원 배열일 경우에는 해당 자리에 들어올 때 어느 방향에서 들어오는지 모르기 때문에 같거나 크면 q에 다시 넣어줘야헸다
        //하지만 3차원 배열을 통해서 어느 방향에서 들어올 때 최소 방향인지를 잡아주면
        //같은 경우를 배제할 수 있기 때문에 q에 넣어주는 작업을 하지 않아도 된다.
        int[][][] visit = new int[4][H][W];
        for(int d=0; d<4; d++) {
            for(int i=0; i<H; i++) {
                Arrays.fill(visit[d][i], INF);
            }
        }
        q.offer(new L(sx, sy, 0, -1));

        while (!q.isEmpty()) {
            L top = q.poll();

            if(map[top.x][top.y] == 'C') {
                System.out.println(top.cnt-1);
                return;
            }

            for(int i=0; i<4; i++) {
                int nx = top.x + dx[i];
                int ny = top.y + dy[i];
                if(!isInside(nx, ny) || map[nx][ny] == '*') {
                    continue;
                }
                L next = new L(nx, ny, top.cnt, i);
                if(i != top.dir) {
                    next.cnt++;
                }

                if(visit[i][nx][ny] > next.cnt) {
                    visit[i][nx][ny] = next.cnt;
                    q.offer(next);
                }
            }
        }
    }
}

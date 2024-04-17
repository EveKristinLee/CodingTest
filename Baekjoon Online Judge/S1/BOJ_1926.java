package solving;
//BOJ S1 1926 그림

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1926 {
    private static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n;
    static int m;
    static int[][] paint;
    static boolean[][] visit;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    private static boolean isInside(int x, int y) {
        if(x<0|| y<0 || x>=n || y>=m) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        paint = new int[n][m];
        visit = new boolean[n][m];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                paint[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        int maxSize = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(!visit[i][j] && paint[i][j] == 1) {
                    visit[i][j] = true;
                    cnt++;
                    maxSize = Math.max(maxSize, bfs(i, j));
                }
            }
        }

        System.out.println(cnt);
        System.out.println(maxSize);
    }

    private static int bfs(int x, int y) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(x, y));
        int size = 1;

        while(!q.isEmpty()) {
            Pos now = q.poll();
            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(!isInside(nx, ny)) {
                    continue;
                }
                if(!visit[nx][ny] && paint[nx][ny] == 1) {
                    visit[nx][ny] = true;
                    size++;
                    q.offer(new Pos(nx, ny));
                }
            }
        }
        return size;
    }
}
